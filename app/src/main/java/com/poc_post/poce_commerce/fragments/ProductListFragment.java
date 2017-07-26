package com.poc_post.poce_commerce.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.poc_post.poce_commerce.R;
import com.poc_post.poce_commerce.adapters.ProductRecyclerViewAdapter;
import com.poc_post.poce_commerce.screen_contracts.ProductListContract;
import com.poc_post.poce_commerce.di.components.DaggerProductComponent;
import com.poc_post.poce_commerce.di.modules.ProductModule;
import com.poc_post.poce_commerce.entities.Product;
import com.poc_post.poce_commerce.presenters.ProductListPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductListFragment extends BaseFragment implements ProductListContract.View {

    @Inject ProductListPresenter presenter;

    private List<Product> products;
    private ProductRecyclerViewAdapter adapter;

    @BindView(R.id.products_recycler_view) RecyclerView productRecyclerView;
    @BindView(R.id.product_list_swipeRefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.productName_search_input) EditText productNameSearchInput;
    @BindView(R.id.products_list_sort_spinner) Spinner sortBySpinner;
    @BindView(R.id.orders_overview) TextView ordersOverview;

    Map<Product, Integer> orders = new HashMap<>();

    @Override
    protected void viewCreatedHook(Bundle savedInstanceState) {
        super.viewCreatedHook(savedInstanceState);
        setupSwipeRefresh(getContext());
        setupSortBySpinner();

        DaggerProductComponent.builder()
                .applicationComponent(getApplicationComponent())
                .productModule(new ProductModule(this))
                .build().inject(this);
    }

    private void setupSortBySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.product_sortBy, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortBySpinner.setAdapter(adapter);
        sortBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortProducts(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void sortProducts(Comparator<Product> comparator) {
        if (products != null) {
            Collections.sort(products, comparator);
        }
        displayProducts(products);
    }

    private void sortProducts(String selectedOption) {
        if (selectedOption.equals("Price")) {
            sortProducts(sortyByPrice());
        } else if (selectedOption.equals("Name")) {
            sortProducts(sortyByName());
        }
    }

    private Comparator<Product> sortyByName() {
        return new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };
    }

    private Comparator<Product> sortyByPrice() {
        return new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        };
    }

    public void addProduct(Product product, int quantity) {
        Integer alreadyOrdered = orders.get(product);
        Integer temp = 0;
        if (alreadyOrdered != null) {
            temp += alreadyOrdered;
        }
        orders.put(product, temp + quantity);
    }

    public double total(Map<Product, Integer> orders) {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            total += entry.getValue() * entry.getKey().getPrice();
        }
        return total;
    }

    private void setupSwipeRefresh(final Context context) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                computeFreshData(context);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    @Override
    protected int getContentView() {
        return R.layout.products_list_fragment;
    }

    @Override
    public String getFragmentAppBarTitle() {
        return "Products List";
    }

    @Override
    protected void displayContent(Context context) {
        if (products != null) {
            displayProducts(products);
        }
        updateOverview(orders);
    }

    @Override
    protected void restoreData(Context context, Bundle savedInstanceState) {
        products = (List<Product>) savedInstanceState.getSerializable("products");
        orders = (Map<Product, Integer>) savedInstanceState.getSerializable("orders");
    }

    @Override
    protected void computeFreshData(Context context) {
        presenter.findAllProducts();
    }

    @Override
    protected void saveInstanceData(Bundle outState) {
        outState.putSerializable("products", (Serializable) products);
        outState.putSerializable("orders", (Serializable) orders);
    }

    @Override
    public void showProducts(List<Product> products) {
        if (products != null) {
            this.products = products;
            displayProducts(products);
        }
    }

    @Override
    public void showProduct(Product product) {
        if (product != null) {
            displayProduct(product);
        }
    }

    @Override
    public void addProductToCart(Product product) {
        showProductQuantitySpinner(product);
    }

    private void showProductQuantitySpinner(final Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);

        final NumberPicker picker = new NumberPicker(getActivity());
        picker.setMinValue(0);
        picker.setMaxValue(5);
        final FrameLayout parent = new FrameLayout(getActivity());
        parent.addView(picker, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER));
        builder.setView(parent);
        builder.setPositiveButton("Add To Cart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showToast(product.getName() + " : " + picker.getValue());
                addProduct(product, picker.getValue());
                showToast("total: " + total(orders));
                updateOverview(orders);
            }
        });
        builder.setNegativeButton("Cancel", null);

        Dialog dialog = builder.create();
        dialog.show();
    }

    @OnClick(R.id.product_search_btn)
    public void onSearchProductByNameButtonClicked() {
        String productName = productNameSearchInput.getText().toString();
        if (productName.trim().length() > 0) {
            presenter.findProductByName(productName);
        } else {
            showToast("fill in the product name");
        }
    }

    private void displayProducts(@NonNull List<Product> products) {
        if (products != null) {
            adapter = new ProductRecyclerViewAdapter(products, getContext(), this);
            productRecyclerView.setLayoutManager(new LinearLayoutManager(productRecyclerView.getContext()));
            productRecyclerView.setAdapter(adapter);
        }
    }

    private void displayProduct(@NonNull Product product) {
        if (adapter == null) {
            adapter = new ProductRecyclerViewAdapter(products, getContext(), this);
            productRecyclerView.setLayoutManager(new LinearLayoutManager(productRecyclerView.getContext()));
            productRecyclerView.setAdapter(adapter);
        }
        if (products == null) {
            products = new ArrayList<>();
        }
        this.products.add(product);
        adapter.addProductToDisplay(product);
    }

    public int countProductsOrdered(Map<Product, Integer> orders) {
        int out = 0;
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            out += entry.getValue();
        }
        return out;
    }

    public void updateOverview(Map<Product, Integer> orders) {
        if (orders != null) {
            int countProductsOrdered = countProductsOrdered(orders);
            double totalOrdered = total(orders);
            ordersOverview.setText("products ordered: " + countProductsOrdered + " , total = " + totalOrdered + " DH");
        }
    }
}

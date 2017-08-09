package com.poc_post.poce_commerce.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poc_post.poce_commerce.R;
import com.poc_post.poce_commerce.ui.screen_contracts.ProductListContract;
import com.poc_post.poce_commerce.entities.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private List<Product> products;
    private Context context;
    private ProductListContract.View listener;

    public ProductRecyclerViewAdapter(List<Product> products, Context context, ProductListContract.View listener) {
        this.products = products;
        this.context = context;
        this.listener = listener;
    }

    public void addProductToDisplay(Product product){
        products.add(product);
        notifyDataSetChanged();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.setText(holder.productName,product.getName());
        holder.setText(holder.productPrice,product.getPrice()+" DH");
        if(listener!= null){
            holder.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.addProductToCart(product);
                }
            });
        }
        Glide.with(context)
                .load(product.getPicture_url())
                .into(holder.productPicture);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        @BindView(R.id.product_item_name) TextView productName;
        @BindView(R.id.product_item_price) TextView productPrice;
        @BindView(R.id.product_item_icon) ImageView productPicture;
        @BindView(R.id.add_to_cart) ImageView addToCart;

        ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            ButterKnife.bind(this,mView);
        }

        void setText(TextView textView, String content){
            textView.setText((content == null) ? "" : content);
        }
    }
}

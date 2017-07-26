package com.poc_post.poce_commerce.fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.poc_post.poce_commerce.R;

import butterknife.BindView;

public class OrderedProductListFragment extends BaseFragment {

    @BindView(R.id.order_total) TextView ordersTotal;
    private double total;

    @Override
    protected int getContentView() {
        return R.layout.ordered_products_fragment;
    }

    @Override
    public String getFragmentAppBarTitle() {
        return "Orders";
    }

    private void displayOrderTotal(double total){
        ordersTotal.setText("Total : "+total+" DH");
    }

    @Override
    protected void displayContent(Context context) {

    }

    @Override
    protected void restoreData(Context context, Bundle savedInstanceState) {
        total = savedInstanceState.getDouble("total");
    }

    @Override
    protected void computeFreshData(Context context) {

    }

    @Override
    protected void saveInstanceData(Bundle outState) {
        outState.putDouble("total",total);
    }
}

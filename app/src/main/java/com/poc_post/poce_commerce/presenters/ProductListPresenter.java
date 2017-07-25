package com.poc_post.poce_commerce.presenters;

import android.util.Log;

import com.poc_post.poce_commerce.contracts.ProductListContract;
import com.poc_post.poce_commerce.entities.Product;
import com.poc_post.poce_commerce.repositories.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListPresenter implements ProductListContract.UserActionsListener {

    private final String TAG = "POC-Presenter";

    @Inject ProductRepository productRepository;
    @Inject ProductListContract.View mView;

    public ProductListPresenter(ProductRepository productRepository,ProductListContract.View view){
        this.productRepository = productRepository;
        this.mView = view;
    }

    @Override
    public void findAllProducts() {
        Call<List<Product>> products = productRepository.findAll();

        products.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mView.showProducts(response.body());
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void findProductByName(String productName) {
        Call<List<Product>> products = productRepository.findByName(productName);

        products.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mView.showProducts(response.body());
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}

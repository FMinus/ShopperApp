package com.poc_post.poce_commerce.presenters;

import android.util.Log;

import com.poc_post.poce_commerce.screen_contracts.ProductListContract;
import com.poc_post.poce_commerce.entities.Product;
import com.poc_post.poce_commerce.repositories.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

public class ProductListPresenter extends BasePresenter implements ProductListContract.UserActionsListener , Observer<List<Product>> {

    private final String TAG = "POC-Presenter";

    @Inject ProductRepository productRepository;
    @Inject ProductListContract.View mView;

    public ProductListPresenter(ProductRepository productRepository,ProductListContract.View view){
        this.productRepository = productRepository;
        this.mView = view;
    }

    @Override
    public void findAllProducts() {
        Observable<List<Product>> products = productRepository.findAll();
        subscribe(products,this);
    }

    @Override
    public void findProductByName(String productName) {
        Observable<List<Product>> productsByName = productRepository.findByName(productName);
        subscribe(productsByName,this);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onFailure: ", e);
    }

    @Override
    public void onNext(List<Product> products) {
       mView.showProducts(products);
    }
}

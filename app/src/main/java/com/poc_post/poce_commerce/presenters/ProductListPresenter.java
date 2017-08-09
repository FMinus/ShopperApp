package com.poc_post.poce_commerce.presenters;

import android.util.Log;

import com.poc_post.poce_commerce.entities.Product;
import com.poc_post.poce_commerce.repositories.ProductRepository;
import com.poc_post.poce_commerce.ui.screen_contracts.ProductListContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class ProductListPresenter extends BasePresenter implements ProductListContract.UserActionsListener, Observer<List<Product>> {

    private final String TAG = "POC-Presenter";

    @Inject ProductRepository productRepository;
    @Inject ProductListContract.View mView;

    public ProductListPresenter(ProductRepository productRepository, ProductListContract.View view) {
        this.productRepository = productRepository;
        this.mView = view;
    }

    @Override
    public void findAllProducts() {
        Observable<List<Product>> products = productRepository.findAll();
        subscribe(products, this);
    }

    public void findAllProducts(Scheduler subscribeOn, Scheduler observeOn) {
        Observable<List<Product>> products = productRepository.findAll();
        subscribe(products, this, subscribeOn, observeOn);
    }

    @Override
    public void findProductByName(String productName) {
        Observable<List<Product>> productsByName = productRepository.findByName(productName);
        subscribe(productsByName, this);
    }

    public void findProductByName(String productName, Scheduler subscribeOn, Scheduler observeOn) {
        Observable<List<Product>> productsByName = productRepository.findByName(productName);
        subscribe(productsByName, this);
    }


    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onFailure: ", e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(List<Product> products) {
        mView.showProducts(products);
        mView.toggleProgressBar(false);
    }
}

package com.poc_post.poce_commerce.di.modules;

import com.poc_post.poce_commerce.contracts.ProductListContract;
import com.poc_post.poce_commerce.di.scopes.PerActivity;
import com.poc_post.poce_commerce.presenters.ProductListPresenter;
import com.poc_post.poce_commerce.repositories.ProductRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ProductModule {

    ProductListContract.View mView;

    public ProductModule(ProductListContract.View view){
        this.mView = view;
    }

    @Provides
    @PerActivity
    ProductRepository provideApiService(Retrofit retrofit) {
        return retrofit.create(ProductRepository.class);
    }

    @Provides
    @PerActivity
    ProductListPresenter providePrsenter(ProductRepository productRepository,ProductListContract.View view) {
        return new ProductListPresenter(productRepository,view);
    }

    @PerActivity
    @Provides
    ProductListContract.View provideView() {
        return mView;
    }
}

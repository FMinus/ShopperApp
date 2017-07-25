package com.poc_post.poce_commerce.di.components;

import com.poc_post.poce_commerce.di.modules.ProductModule;
import com.poc_post.poce_commerce.di.scopes.PerActivity;
import com.poc_post.poce_commerce.fragments.ProductListFragment;

import dagger.Component;

@PerActivity
@Component(modules = ProductModule.class, dependencies = ApplicationComponent.class)
public interface ProductComponent {

    void inject(ProductListFragment view);
}

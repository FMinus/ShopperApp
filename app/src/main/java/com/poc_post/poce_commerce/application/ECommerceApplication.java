package com.poc_post.poce_commerce.application;

import android.app.Application;

import com.poc_post.poce_commerce.di.components.ApplicationComponent;
import com.poc_post.poce_commerce.di.components.DaggerApplicationComponent;
import com.poc_post.poce_commerce.di.modules.ApplicationModule;


public class ECommerceApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, "https://poppost.mybluemix.net/api/"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

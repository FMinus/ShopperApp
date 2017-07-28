package com.poc_post.poce_commerce.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.poc_post.poce_commerce.application.ECommerceApplication;
import com.poc_post.poce_commerce.di.components.ApplicationComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    public abstract int getContentViewLayout();

    @IdRes
    public abstract int getFrameLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        ButterKnife.bind(this);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void setAppBarTitle(String title) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public boolean isNewlyCreated(Bundle savedState) {
        return savedState == null;
    }

    public void startActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(this, activity));
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((ECommerceApplication) getApplication()).getApplicationComponent();
    }
}

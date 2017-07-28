package com.poc_post.poce_commerce.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.poc_post.poce_commerce.R;
import com.poc_post.poce_commerce.activities.BaseActivity;
import com.poc_post.poce_commerce.fragments.ProductListFragment;

public class MainActivity extends BaseActivity {


    @Override
    public int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public int getFrameLayoutId() {
        return R.id.main_frame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            showFragment(ProductListFragment.newInstance());
        }
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(getFrameLayoutId(), fragment).addToBackStack(null).commit();

    }
}

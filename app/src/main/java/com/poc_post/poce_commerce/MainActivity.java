package com.poc_post.poce_commerce;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.poc_post.poce_commerce.activities.BaseActivity;
import com.poc_post.poce_commerce.adapters.ViewPagerAdapter;
import com.poc_post.poce_commerce.entities.Product;
import com.poc_post.poce_commerce.fragments.ProductListFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

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

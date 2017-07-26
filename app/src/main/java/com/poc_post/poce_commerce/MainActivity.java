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

//    @BindView(R.id.orders_viewPager) protected ViewPager viewPager;
//    @BindView(R.id.orders_tabLayout) protected TabLayout tabLayout;

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
//        setupViewPagerLayout();
    }

//    private void setupViewPagerLayout() {
//        viewPager.setAdapter(createViewPagerAdapter());
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//        setAppBarTitle("Shopping app");
//    }
//
//    private ViewPagerAdapter createViewPagerAdapter() {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment("Products List", ProductListFragment.newInstance());
//        return adapter;
//    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(getFrameLayoutId(), fragment).addToBackStack(null).commit();

    }
}

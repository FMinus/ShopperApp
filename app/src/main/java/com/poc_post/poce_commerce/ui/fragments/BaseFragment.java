package com.poc_post.poce_commerce.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.poc_post.poce_commerce.ui.activities.BaseActivity;
import com.poc_post.poce_commerce.application.ShoppingApplication;
import com.poc_post.poce_commerce.di.components.ApplicationComponent;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private Context context;

    protected abstract int getContentView();

    public abstract String getFragmentAppBarTitle();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, rootView);
        viewCreatedHook(savedInstanceState);
        hideKeyBoard();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    protected void viewCreatedHook(Bundle savedInstanceState) {
        //child classes should override this to perform additional init logic
    }



    @Override //4
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setAppbarTitle(getFragmentAppBarTitle());

        if(savedInstanceState==null){
            computeFreshData(context);
        }else{
            restoreData(context,savedInstanceState);
        }
        displayContent(context);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveInstanceData(outState);
    }

    //handles data restoration lifecycle
    protected abstract void displayContent(Context context);
    protected abstract void restoreData(Context context, Bundle savedInstanceState);
    protected abstract void computeFreshData(Context context);
    protected abstract void saveInstanceData(Bundle outState);


    protected void hideKeyBoard() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    protected void showToast(@Nullable String message) {
        if (message != null) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    public void setAppbarTitle(String title) {
        ((BaseActivity) getActivity()).setAppBarTitle(title);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((ShoppingApplication) getActivity().getApplication()).getApplicationComponent();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

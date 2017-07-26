package com.poc_post.poce_commerce.presenters;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class BasePresenter {

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

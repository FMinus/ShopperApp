package com.poc_post.poce_commerce.presenters;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {


    <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    <T> void subscribe(Observable<T> observable, Observer<T> observer, Scheduler subscribeOn,Scheduler observeOn) {
        observable.subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(observer);
    }
}

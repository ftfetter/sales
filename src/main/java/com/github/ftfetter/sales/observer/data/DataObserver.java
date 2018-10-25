package com.github.ftfetter.sales.observer.data;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.List;

public abstract class DataObserver implements Observer<List<String>> {

    @Override
    public void onSubscribe(Disposable s) {
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("ERROR: " + t.getMessage());
    }

    @Override
    public void onComplete() {

    }
}

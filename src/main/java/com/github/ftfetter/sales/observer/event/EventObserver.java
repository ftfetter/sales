package com.github.ftfetter.sales.observer.event;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.nio.file.WatchEvent;

public abstract class EventObserver implements Observer<WatchEvent> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("ERROR: " + e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}

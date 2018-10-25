package com.github.ftfetter.sales.observer;

import com.github.ftfetter.sales.observer.data.DataObserver;
import com.github.ftfetter.sales.utils.FileUtils;
import io.reactivex.observables.ConnectableObservable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProcessor {

    private List<DataObserver> observers;

    private final String LINE_SPLITTER = "ç";

    public DataProcessor() {
        this.observers = new ArrayList<>();
    }

    public void observe(File file) {
        ConnectableObservable<List<String>> observable = FileUtils.read(file, 10)
                .map(line -> Arrays.asList(line.split(LINE_SPLITTER)))
                .toObservable()
                .publish();
        observers.forEach(observable::subscribe);
        observable.connect();
    }

    public void addObserver(DataObserver observer) {
        this.observers.add(observer);
    }
}

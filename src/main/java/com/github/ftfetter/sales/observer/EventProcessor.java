package com.github.ftfetter.sales.observer;

import com.github.ftfetter.sales.observer.event.EventObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class EventProcessor {

    private final Path directory;
    private final WatchService watcher;
    private List<EventObserver> observers;

    public EventProcessor(String directoryPath) throws IOException {
        this.directory = Paths.get(directoryPath);
        this.watcher = directory.getFileSystem().newWatchService();
        this.observers = new ArrayList<>();
    }

    public void observe() {
        ConnectableObservable<WatchEvent> observable = Observable
                .create(this::onSubscribe)
                .publish();
        observers.forEach(observable::subscribe);
        observable.connect();
    }

    private void onSubscribe(ObservableEmitter<WatchEvent> emitter) {
        boolean error = false;
        try {
            directory.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (IOException e) {
            emitter.onError(e);
            error = true;
        }
        while (!error && !emitter.isDisposed()) {
            final WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                emitter.onError(e);
                error = true;
                break;
            }
            key.pollEvents().forEach(emitter::onNext);
            if (!key.reset()) {
                break;
            }
        }
        if (!error) {
            emitter.onComplete();
        }
    }

    public void addObserver(EventObserver observer) {
        this.observers.add(observer);
    }
}

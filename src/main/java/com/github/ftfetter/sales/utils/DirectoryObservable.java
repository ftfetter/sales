package com.github.ftfetter.sales.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class DirectoryObservable {

    private final Path directory;
    private final WatchService watcher;

    public DirectoryObservable(Path directory) throws IOException {
        this.directory = directory;
        this.watcher = directory.getFileSystem().newWatchService();
    }

    public Observable<WatchEvent<?>> create() {
        return Observable.create(this::emitter);
    }

    private void emitter(ObservableEmitter<WatchEvent<?>> subscriber) {
        boolean error = false;
        try {
            directory.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (IOException e) {
            subscriber.onError(e);
            error = true;
        }
        while (!error && !subscriber.isDisposed()) {
            final WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                subscriber.onError(e);
                error = true;
                break;
            }
            key.pollEvents().forEach(subscriber::onNext);
            if (!key.reset()) {
                break;
            }
        }
        if (!error) {
            subscriber.onComplete();
        }
    }
}

package com.github.ftfetter.sales.service;

import com.github.ftfetter.sales.factory.EventFactory;
import com.github.ftfetter.sales.utils.DirectoryObservable;

import java.io.IOException;
import java.nio.file.WatchEvent;

public class SalesService {

    private final DirectoryObservable observable;
    private final EventFactory eventFactory;

    public SalesService(String path) throws IOException {
        this.observable = new DirectoryObservable(path + "/in");
        this.eventFactory = new EventFactory();
    }

    public void generateMetrics() {
        System.out.println("Watching...");
        observable.create().subscribe(
                this::handleEvent,
                Throwable::printStackTrace,
                () -> System.out.println("DONE")
        );
    }

    private void handleEvent(WatchEvent event) {
        eventFactory.get()
                .filter(factory -> factory.isElegible(event))
                .findAny()
                .map(factory -> factory.execute(String.valueOf(event.context())));
    }
}

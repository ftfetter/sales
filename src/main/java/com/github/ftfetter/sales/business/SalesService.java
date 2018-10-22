package com.github.ftfetter.sales.business;

import com.github.ftfetter.sales.factory.EventFactory;
import com.github.ftfetter.sales.utils.DirectoryObservable;

import java.io.IOException;
import java.nio.file.WatchEvent;

public class SalesService {

    private final DirectoryObservable observable;
    private final EventFactory eventFactory;

    public SalesService(String path) throws IOException {
        this.observable = new DirectoryObservable(path + "/in");
        this.eventFactory = new EventFactory(path);
    }

    public void generateMetrics() {
        System.out.println("Watching...");
        observable.create().subscribe(
                this::handleEvent,
                Throwable::printStackTrace,
                () -> System.out.println("DONE")
        );
    }

    private void handleEvent(WatchEvent event) throws Exception {
        eventFactory.get()
                .filter(factory -> factory.isElegible(event))
                .findAny()
                .orElseThrow(() -> new Exception("Ilegal event."))
                .execute(String.valueOf(event.context()));
    }
}

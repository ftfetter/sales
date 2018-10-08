package com.github.ftfetter.sales.service;

import com.github.ftfetter.sales.utils.DirectoryObservable;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class SalesService {

    private final DirectoryObservable observable;

    public SalesService(String path) throws IOException {
        this.observable = new DirectoryObservable(Paths.get(path));
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
        if (event.kind().equals(ENTRY_MODIFY)) {
            System.out.println("MODIFICATION IN " + event.context());
        }
        if (event.kind().equals(ENTRY_DELETE)) {
            System.out.println(event.context() + " FILE DELETED");
        }
        if (event.kind().equals(ENTRY_CREATE)) {
            System.out.println(event.context() + " FILE CREATED");
        }
    }
}

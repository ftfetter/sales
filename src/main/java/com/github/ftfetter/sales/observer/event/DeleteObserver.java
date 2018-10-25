package com.github.ftfetter.sales.observer.event;

import com.github.ftfetter.sales.business.SalesService;

import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

public class DeleteObserver extends EventObserver {

    private SalesService salesService;

    public DeleteObserver(SalesService salesService) {
        this.salesService = salesService;
    }

    @Override
    public void onNext(WatchEvent event) {
        if (event.kind().equals(ENTRY_DELETE)) {
            System.out.println(event.context().toString() + " FILE DELETED.");
            salesService.deleteMetrics(event.context().toString());
        }
    }
}

package com.github.ftfetter.sales.observer.event;

import com.github.ftfetter.sales.business.SalesService;

import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class CreateObserver extends EventObserver {

    private SalesService salesService;

    public CreateObserver(SalesService salesService) {
        this.salesService = salesService;
    }

    @Override
    public void onNext(WatchEvent event) {
        if (event.kind().equals(ENTRY_CREATE)) {
            System.out.println(event.context().toString() + " FILE CREATED.");
            salesService.writeMetrics(event.context().toString());
        }
    }
}

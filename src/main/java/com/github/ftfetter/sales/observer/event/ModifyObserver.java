package com.github.ftfetter.sales.observer.event;

import com.github.ftfetter.sales.business.SalesService;

import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class ModifyObserver extends EventObserver {

    private SalesService salesService;

    public ModifyObserver(SalesService salesService) {
        this.salesService = salesService;
    }

    @Override
    public void onNext(WatchEvent event) {
        if (event.kind().equals(ENTRY_MODIFY)) {
            System.out.println(event.context().toString() + " FILE MODIFIED.");
            salesService.writeMetrics(event.context().toString());
        }
    }
}

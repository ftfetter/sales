package com.github.ftfetter.sales.factory.event;

import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class CreateEvent implements DirectoryEvent {

    @Override
    public Boolean isElegible(WatchEvent event) {
        return event.kind().equals(ENTRY_CREATE);
    }

    @Override
    public Boolean execute(String fileName) {
        System.out.println(fileName + " FILE CREATED");
        return true;
    }
}

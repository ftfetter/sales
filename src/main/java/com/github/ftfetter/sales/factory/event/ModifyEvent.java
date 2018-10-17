package com.github.ftfetter.sales.factory.event;

import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class ModifyEvent implements DirectoryEvent {

    @Override
    public Boolean isElegible(WatchEvent event) {
        return event.kind().equals(ENTRY_MODIFY);
    }

    @Override
    public Boolean execute(String fileName) {
        System.out.println("MODIFICATION IN " + fileName);
        return true;
    }
}

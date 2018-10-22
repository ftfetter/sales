package com.github.ftfetter.sales.factory.event;

import java.nio.file.WatchEvent;

public interface DirectoryEvent {

    Boolean isElegible(WatchEvent event);
    void execute(String fileName);
}

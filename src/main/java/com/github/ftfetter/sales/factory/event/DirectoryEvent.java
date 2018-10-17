package com.github.ftfetter.sales.factory.event;

import java.nio.file.WatchEvent;

public interface DirectoryEvent {

    Boolean isElegible(WatchEvent event);
    Boolean execute(String fileName);
}

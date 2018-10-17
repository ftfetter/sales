package com.github.ftfetter.sales.factory.event;

import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

public class DeleteEvent implements DirectoryEvent {

    private String inputPath;
    private String outputPath;

    public DeleteEvent(String directoryPath) {
        this.inputPath = directoryPath + "/in";
        this.outputPath = directoryPath + "/out";
    }

    @Override
    public Boolean isElegible(WatchEvent event) {
        return event.kind().equals(ENTRY_DELETE);
    }

    @Override
    public Boolean execute(String fileName) {
        System.out.println(fileName + " FILE DELETED");
        return true;
    }
}

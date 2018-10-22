package com.github.ftfetter.sales.factory.event;

import java.io.File;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

public class DeleteEvent implements DirectoryEvent {

    private String outputPath;

    public DeleteEvent(String directoryPath) {
        this.outputPath = directoryPath + "/out";
    }

    @Override
    public Boolean isElegible(WatchEvent event) {
        return event.kind().equals(ENTRY_DELETE);
    }

    @Override
    public void execute(String fileName) {
        File file = new File(String.format("%s/%s.done.dat", outputPath, fileName));
        if (!file.delete()) {
            System.out.println("Error deleting the " + fileName + ".done.dat file.");
        }
    }
}

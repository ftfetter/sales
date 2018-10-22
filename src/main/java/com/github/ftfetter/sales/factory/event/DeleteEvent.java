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
        System.out.println(fileName + " FILE DELETED");
        if (!fileName.endsWith(".dat")) return;

        fileName = fileName.replace(".dat","");
        File outputFile = new File(String.format("%s/%s.done.dat", outputPath, fileName));
        try {
            outputFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error deleting " + fileName + " file.");
        }
    }
}

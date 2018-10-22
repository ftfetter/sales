package com.github.ftfetter.sales.factory.event;

import com.github.ftfetter.sales.business.DataConverter;
import com.github.ftfetter.sales.pojos.SalesMetrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class ModifyEvent implements DirectoryEvent {

    private String inputPath;
    private String outputPath;
    private DataConverter dataConverter;

    public ModifyEvent(String directoryPath) {
        this.inputPath = directoryPath + "/in";
        this.outputPath = directoryPath + "/out";
        this.dataConverter = new DataConverter();
    }

    @Override
    public Boolean isElegible(WatchEvent event) {
        return event.kind().equals(ENTRY_MODIFY);
    }

    @Override
    public void execute(String fileName) {
        System.out.println(fileName + " FILE MODIFIED");
        if (!fileName.endsWith(".dat")) return;

        SalesMetrics metrics = dataConverter.generateMetric(inputPath, fileName);
        fileName = fileName.replace(".dat","");
        File outputFile = new File(String.format("%s/%s.done.dat", outputPath, fileName));
        try {
            outputFile.delete();
            BufferedWriter writer = Files.newBufferedWriter(outputFile.toPath());
            writer.write(metrics.toString());
        } catch (IOException e) {
            System.out.println("Error modifying metric for the " + fileName + " file.");
            e.printStackTrace();
        }
    }
}

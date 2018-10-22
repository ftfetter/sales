package com.github.ftfetter.sales.factory.event;

import com.github.ftfetter.sales.business.DataConverter;
import com.github.ftfetter.sales.pojos.SalesMetrics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class CreateEvent implements DirectoryEvent {

    private String inputPath;
    private String outputPath;
    private DataConverter dataConverter;

    public CreateEvent(String directoryPath) {
        this.inputPath = directoryPath + "/in";
        this.outputPath = directoryPath + "/out";
        this.dataConverter = new DataConverter();
    }

    @Override
    public Boolean isElegible(WatchEvent event) {
        return event.kind().equals(ENTRY_CREATE);
    }

    @Override
    public void execute(String fileName) {
        SalesMetrics metrics = dataConverter.generateMetric(inputPath, fileName);
        String newFileLocation = String.format("%s/%s.done.dat", outputPath, fileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFileLocation));
            writer.write(metrics.toString());
        } catch (IOException e) {
            System.out.println("Error writing metric for " + fileName + " file.");
            e.printStackTrace();
        }
    }
}

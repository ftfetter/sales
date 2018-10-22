package com.github.ftfetter.sales.factory.event;

import com.github.ftfetter.sales.business.DataConverter;
import com.github.ftfetter.sales.pojos.SalesMetrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        SalesMetrics metrics = dataConverter.generateMetric(inputPath, fileName);
        String metricsFileLocation = String.format("%s/%s.done.dat", outputPath, fileName);

        File file = new File(metricsFileLocation);
        if (!file.delete()) {
            System.out.println("Error deleting the " + fileName + ".done.dat file.");
            return;
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(metricsFileLocation));
            writer.write(metrics.toString());
        } catch (IOException e) {
            System.out.println("Error writing metric for the " + fileName + " file.");
            e.printStackTrace();
        }
    }
}

package com.github.ftfetter.sales.business;

import com.github.ftfetter.sales.observer.EventProcessor;
import com.github.ftfetter.sales.observer.DataProcessor;
import com.github.ftfetter.sales.observer.data.CustomerObserver;
import com.github.ftfetter.sales.observer.data.SalesObserver;
import com.github.ftfetter.sales.observer.data.SalesmanObserver;
import com.github.ftfetter.sales.observer.event.CreateObserver;
import com.github.ftfetter.sales.observer.event.DeleteObserver;
import com.github.ftfetter.sales.observer.event.ModifyObserver;
import com.github.ftfetter.sales.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class SalesService {

    private final EventProcessor observable;
    private final String inputPath;
    private final String outputPath;

    public SalesService(String path) throws IOException {
        this.inputPath = path + "/in";
        this.outputPath = path + "/out";
        this.observable = new EventProcessor(inputPath);
    }

    public void generateMetrics() {
        readExistentFiles();
        readFilesByEvent();
    }

    private void readExistentFiles() {
        File folder = new File(inputPath);
        Stream.of(folder.listFiles())
                .filter(File::isFile)
                .filter(file -> FileUtils.isValidExtension(file.getName()))
                .forEach(this::writeMetrics);
    }

    private void readFilesByEvent() {
        observable.addObserver(new CreateObserver(this));
        observable.addObserver(new ModifyObserver(this));
        observable.addObserver(new DeleteObserver(this));
        observable.observe();
    }

    public void writeMetrics(String fileName) {
        File file = FileUtils.toInputFile(fileName, inputPath);
        writeMetrics(file);
    }

    private void writeMetrics(File file) {
        DataProcessor dataProcessor = new DataProcessor();
        MetricsService metricsService = new MetricsService();

        dataProcessor.addObserver(new SalesmanObserver(metricsService));
        dataProcessor.addObserver(new CustomerObserver(metricsService));
        dataProcessor.addObserver(new SalesObserver(metricsService));
        dataProcessor.observe(file);

        try {
            FileUtils.write(FileUtils.toOutputFile(file.getName(), outputPath), metricsService.generateMetrics().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMetrics(String fileName) {
        FileUtils.toOutputFile(fileName, outputPath)
                .delete();
    }
}

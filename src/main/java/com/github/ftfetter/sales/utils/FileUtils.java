package com.github.ftfetter.sales.utils;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

public class FileUtils {

    private static final String FILE_OUTPUT_EXTENSION = ".done.dat";
    private static final String FILE_INPUT_EXTENSION = ".dat";

    public static Boolean isValidExtension(String fileName) {
        return fileName.endsWith(FILE_INPUT_EXTENSION);
    }

    public static File toOutputFile(String fileName, String directory) {
        if (isValidExtension(fileName)) {
            fileName = fileName.replace(FILE_INPUT_EXTENSION, "");
        }
        return new File(directory + "/" + fileName + FILE_OUTPUT_EXTENSION);
    }

    public static File toInputFile(String fileName, String directory) {
        if (isValidExtension(fileName)) {
            return new File(directory + "/" + fileName);
        }
        return new File(directory + "/" + fileName + FILE_INPUT_EXTENSION);
    }

    public static void write(File outputFile, String textToWrite) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(outputFile.toPath(), CREATE, WRITE, TRUNCATE_EXISTING);
        writer.write(textToWrite);
        writer.close();
    }

    public static Flowable<String> read(File inputFile, Integer backPressureCapacity) {
        return Flowable.using(
                () -> Files.newBufferedReader(inputFile.toPath()),
                reader -> Flowable.fromIterable(() -> reader.lines().iterator()),
                BufferedReader::close
        ).onBackpressureBuffer(backPressureCapacity);
    }
}

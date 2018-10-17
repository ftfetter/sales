package com.github.ftfetter.sales.utils;

import io.reactivex.Flowable;

import java.io.BufferedReader;
import java.io.FileReader;

public class RxFileReader {

    private final String directoryPath;

    public RxFileReader(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public Flowable<String> readLines(String fileName, Integer backPreassureCapacity) {
        return Flowable.using(
                () -> new BufferedReader(new FileReader(directoryPath + "/" + fileName)),
                reader -> Flowable.fromIterable(() -> reader.lines().iterator()),
                BufferedReader::close
        ).onBackpressureBuffer(backPreassureCapacity);
    }
}

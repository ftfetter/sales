package com.github.ftfetter.sales.type;

import java.util.Optional;
import java.util.stream.Stream;

public enum DataType {

    SALESMAN    ("001"),
    CUSTOMER    ("002"),
    SALES       ("003");

    private String dataId;

    DataType(String dataId) {
        this.dataId = dataId;
    }

    public String getDataId() {
        return dataId;
    }

    public static Optional<DataType> getById(String id) {
        return Stream.of(values())
                .filter(dataType -> dataType.getDataId().equals(id))
                .findAny();
    }
}

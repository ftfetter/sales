package com.github.ftfetter.sales.type;

import java.util.Arrays;
import java.util.Optional;

public enum DataType {

    SALESMAN    ("001"),
    CUSTOMER    ("002"),
    SALES       ("003");

    private String id;

    DataType(String id) {
    }

    public static Optional<DataType> getById(String id) {
        return Arrays.stream(DataType.values())
                .filter(dataType -> dataType.id.equals(id))
                .findFirst();
    }
}

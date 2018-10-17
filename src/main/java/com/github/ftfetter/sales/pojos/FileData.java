package com.github.ftfetter.sales.pojos;

import com.github.ftfetter.sales.type.DataType;

public abstract class FileData {

    private DataType type;

    public FileData(DataType type) {
        this.type = type;
    }
}

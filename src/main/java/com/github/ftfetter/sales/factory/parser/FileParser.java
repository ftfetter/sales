package com.github.ftfetter.sales.factory.parser;

import com.github.ftfetter.sales.pojos.FileData;

public interface FileParser {

    Boolean isElegible(String id);
    FileData parse(String line);
}

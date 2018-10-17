package com.github.ftfetter.sales.factory.parser;

public interface FileParser {

    Boolean isElegible(String id);
    Boolean parse(String line);
}

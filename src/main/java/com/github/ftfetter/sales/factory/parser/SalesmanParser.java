package com.github.ftfetter.sales.factory.parser;

public class SalesmanParser implements FileParser {

    private final String SALESMAN_ID = "001";

    @Override
    public Boolean isElegible(String id) {
        return id.equals(SALESMAN_ID);
    }

    @Override
    public Boolean parse(String line) {
        return null;
    }
}

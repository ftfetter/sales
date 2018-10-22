package com.github.ftfetter.sales.factory.parser;

import com.github.ftfetter.sales.pojos.SalesmanData;

public class SalesmanParser implements FileParser {

    private final String SALESMAN_ID = "001";

    @Override
    public Boolean isElegible(String id) {
        return id.equals(SALESMAN_ID);
    }

    @Override
    public SalesmanData parse(String line) {
        return null;
    }
}

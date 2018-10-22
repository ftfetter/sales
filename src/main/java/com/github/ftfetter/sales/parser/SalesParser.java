package com.github.ftfetter.sales.factory.parser;

import com.github.ftfetter.sales.pojos.SalesData;

public class SalesParser implements FileParser {

    private final String SALES_ID = "003";

    @Override
    public Boolean isElegible(String id) {
        return id.equals(SALES_ID);
    }

    @Override
    public SalesData parse(String line) {
        return null;
    }
}

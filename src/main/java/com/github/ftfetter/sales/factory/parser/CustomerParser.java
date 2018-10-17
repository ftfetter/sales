package com.github.ftfetter.sales.factory.parser;

import com.github.ftfetter.sales.pojos.CustomerData;

public class CustomerParser implements FileParser {

    private final String CUSTOMER_ID = "002";

    @Override
    public Boolean isElegible(String id) {
        return id.equals(CUSTOMER_ID);
    }

    @Override
    public CustomerData parse(String line) {
        return null;
    }
}

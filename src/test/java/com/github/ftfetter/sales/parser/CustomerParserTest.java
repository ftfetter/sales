package com.github.ftfetter.sales.parser;

import com.github.ftfetter.sales.pojos.CustomerData;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CustomerParserTest {

    @Test
    public void parseTest() {
        List<String> line = Arrays.asList("002", "11111111111111", "CUSTOMER1", "BUSINESS1");
        CustomerData customer = CustomerParser.parse(line);

        Assert.assertEquals("11111111111111", customer.getCnpj());
        Assert.assertEquals("CUSTOMER1", customer.getName());
        Assert.assertEquals("BUSINESS1", customer.getBusinessArea());
    }
}
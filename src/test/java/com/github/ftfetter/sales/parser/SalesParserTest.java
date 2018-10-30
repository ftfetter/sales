package com.github.ftfetter.sales.parser;

import com.github.ftfetter.sales.pojos.SalesData;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SalesParserTest {

    @Test
    public void parseTest() {
        List<String> line = Arrays.asList("003", "1111", "[11-5-100,12-10-200,13-8-150]", "SALESMAN1");
        SalesData salesData = SalesParser.parse(line);

        Assert.assertEquals("1111", salesData.getSaleId());
        Assert.assertEquals("11", salesData.getItems().get(0).getItemId());
        Assert.assertEquals(5, salesData.getItems().get(0).getQuantity().intValue());
        Assert.assertEquals(BigDecimal.valueOf(100.0), salesData.getItems().get(0).getPrice());
        Assert.assertEquals("12", salesData.getItems().get(1).getItemId());
        Assert.assertEquals(10, salesData.getItems().get(1).getQuantity().intValue());
        Assert.assertEquals(BigDecimal.valueOf(200.0), salesData.getItems().get(1).getPrice());
        Assert.assertEquals("13", salesData.getItems().get(2).getItemId());
        Assert.assertEquals(8, salesData.getItems().get(2).getQuantity().intValue());
        Assert.assertEquals(BigDecimal.valueOf(150.0), salesData.getItems().get(2).getPrice());
        Assert.assertEquals("SALESMAN1", salesData.getSalesmanName());
    }
}
package com.github.ftfetter.sales.parser;

import com.github.ftfetter.sales.pojos.SalesmanData;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SalesmanParserTest {

    @Test
    public void parseTest() {
        List<String> line = Arrays.asList("001", "11111111111", "SALESMAN1", "800.00");
        SalesmanData salesman = SalesmanParser.parse(line);

        Assert.assertEquals("11111111111", salesman.getCpf());
        Assert.assertEquals("SALESMAN1", salesman.getName());
        Assert.assertEquals(BigDecimal.valueOf(800.0), salesman.getSalary());
    }
}
package com.github.ftfetter.sales.business;

import com.github.ftfetter.sales.pojos.CustomerData;
import com.github.ftfetter.sales.pojos.ItemData;
import com.github.ftfetter.sales.pojos.SalesData;
import com.github.ftfetter.sales.pojos.SalesMetrics;
import com.github.ftfetter.sales.pojos.SalesmanData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class MetricsServiceTest {

    private MetricsService metricsService;

    @Before
    public void setUp() {
        metricsService = new MetricsService();
    }

    @Test
    public void generateMetricsTest() {
        fullScenario();
        SalesMetrics metrics = metricsService.generateMetrics();

        Assert.assertEquals(3, metrics.getTotalSalesman().intValue());
        Assert.assertEquals(3, metrics.getTotalClients().intValue());
        Assert.assertEquals("3333", metrics.getMostExpensiveSaleId());
        Assert.assertEquals("SALESMAN2", metrics.getWorstSalesmanName());
    }

    @Test
    public void generateMetricsNoneSaleTest() {
        noneSaleScenario();
        SalesMetrics metrics = metricsService.generateMetrics();

        Assert.assertEquals(3, metrics.getTotalSalesman().intValue());
        Assert.assertEquals(3, metrics.getTotalClients().intValue());
        Assert.assertEquals("NONE", metrics.getMostExpensiveSaleId());
        Assert.assertEquals("NONE", metrics.getWorstSalesmanName());
    }

    @Test
    public void generateMetricsNoneCustomerTest() {
        noneCustomerScenario();
        SalesMetrics metrics = metricsService.generateMetrics();

        Assert.assertEquals(3, metrics.getTotalSalesman().intValue());
        Assert.assertEquals(0, metrics.getTotalClients().intValue());
        Assert.assertEquals("3333", metrics.getMostExpensiveSaleId());
        Assert.assertEquals("SALESMAN2", metrics.getWorstSalesmanName());
    }

    @Test
    public void generateMetricsEmptyTest() {
        SalesMetrics metrics = metricsService.generateMetrics();

        Assert.assertEquals(0, metrics.getTotalSalesman().intValue());
        Assert.assertEquals(0, metrics.getTotalClients().intValue());
        Assert.assertEquals("NONE", metrics.getMostExpensiveSaleId());
        Assert.assertEquals("NONE", metrics.getWorstSalesmanName());
    }

    private void fullScenario() {
        metricsService.addSalesmanData(new SalesmanData("11111111111", "SALESMAN1", BigDecimal.valueOf(1111)));
        metricsService.addSalesmanData(new SalesmanData("22222222222", "SALESMAN2", BigDecimal.valueOf(2222)));
        metricsService.addSalesmanData(new SalesmanData("33333333333", "SALESMAN3", BigDecimal.valueOf(3333)));
        metricsService.addCustomerData(new CustomerData("11111111111111", "CUSTOMER1", "BUSINESS1"));
        metricsService.addCustomerData(new CustomerData("22222222222222", "CUSTOMER2", "BUSINESS2"));
        metricsService.addCustomerData(new CustomerData("33333333333333", "CUSTOMER3", "BUSINESS3"));
        ItemData item11 = new ItemData("11", 10, BigDecimal.valueOf(100));
        ItemData item12 = new ItemData("12", 10, BigDecimal.valueOf(100));
        ItemData item13 = new ItemData("13", 10, BigDecimal.valueOf(100));
        ItemData item14 = new ItemData("14", 10, BigDecimal.valueOf(100));
        ItemData item21 = new ItemData("21", 5, BigDecimal.valueOf(100));
        ItemData item22 = new ItemData("22", 10, BigDecimal.valueOf(50));
        ItemData item31 = new ItemData("31", 10, BigDecimal.valueOf(150));
        ItemData item32 = new ItemData("32", 10, BigDecimal.valueOf(150));
        ItemData item33 = new ItemData("33", 10, BigDecimal.valueOf(150));
        ItemData item41 = new ItemData("41", 10, BigDecimal.valueOf(200));
        metricsService.addSalesData(new SalesData("1111", Arrays.asList(item11,item12,item13,item14), "SALESMAN1"));
        metricsService.addSalesData(new SalesData("2222", Arrays.asList(item21, item22), "SALESMAN2"));
        metricsService.addSalesData(new SalesData("3333", Arrays.asList(item31, item32, item33), "SALESMAN3"));
        metricsService.addSalesData(new SalesData("4444", Arrays.asList(item41), "SALESMAN2"));
    }

    private void noneSaleScenario() {
        metricsService.addSalesmanData(new SalesmanData("11111111111", "SALESMAN1", BigDecimal.valueOf(1111)));
        metricsService.addSalesmanData(new SalesmanData("22222222222", "SALESMAN2", BigDecimal.valueOf(2222)));
        metricsService.addSalesmanData(new SalesmanData("33333333333", "SALESMAN3", BigDecimal.valueOf(3333)));
        metricsService.addCustomerData(new CustomerData("11111111111111", "CUSTOMER1", "BUSINESS1"));
        metricsService.addCustomerData(new CustomerData("22222222222222", "CUSTOMER2", "BUSINESS2"));
        metricsService.addCustomerData(new CustomerData("33333333333333", "CUSTOMER3", "BUSINESS3"));
    }

    private void noneCustomerScenario() {
        metricsService.addSalesmanData(new SalesmanData("11111111111", "SALESMAN1", BigDecimal.valueOf(1111)));
        metricsService.addSalesmanData(new SalesmanData("22222222222", "SALESMAN2", BigDecimal.valueOf(2222)));
        metricsService.addSalesmanData(new SalesmanData("33333333333", "SALESMAN3", BigDecimal.valueOf(3333)));
        ItemData item11 = new ItemData("11", 10, BigDecimal.valueOf(100));
        ItemData item12 = new ItemData("12", 10, BigDecimal.valueOf(100));
        ItemData item13 = new ItemData("13", 10, BigDecimal.valueOf(100));
        ItemData item14 = new ItemData("14", 10, BigDecimal.valueOf(100));
        ItemData item21 = new ItemData("21", 5, BigDecimal.valueOf(100));
        ItemData item22 = new ItemData("22", 10, BigDecimal.valueOf(50));
        ItemData item31 = new ItemData("31", 10, BigDecimal.valueOf(150));
        ItemData item32 = new ItemData("32", 10, BigDecimal.valueOf(150));
        ItemData item33 = new ItemData("33", 10, BigDecimal.valueOf(150));
        ItemData item41 = new ItemData("41", 10, BigDecimal.valueOf(200));
        metricsService.addSalesData(new SalesData("1111", Arrays.asList(item11,item12,item13,item14), "SALESMAN1"));
        metricsService.addSalesData(new SalesData("2222", Arrays.asList(item21, item22), "SALESMAN2"));
        metricsService.addSalesData(new SalesData("3333", Arrays.asList(item31, item32, item33), "SALESMAN3"));
        metricsService.addSalesData(new SalesData("4444", Arrays.asList(item41), "SALESMAN2"));
    }
}
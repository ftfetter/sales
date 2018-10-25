package com.github.ftfetter.sales.business;

import com.github.ftfetter.sales.pojos.CustomerData;
import com.github.ftfetter.sales.pojos.ItemData;
import com.github.ftfetter.sales.pojos.SalesData;
import com.github.ftfetter.sales.pojos.SalesMetrics;
import com.github.ftfetter.sales.pojos.SalesmanData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MetricsService {

    private List<SalesmanData> salesmanData;
    private List<CustomerData> customerData;
    private List<SalesData> salesData;

    public MetricsService() {
        clearLists();
    }

    public void clearLists() {
        this.salesmanData = new ArrayList<>();
        this.customerData = new ArrayList<>();
        this.salesData = new ArrayList<>();
    }

    public SalesMetrics generateMetrics() {
        return SalesMetrics.Builder.of()
                .totalSalesman(getTotalSalesman())
                .totalClients(getTotalCustomer())
                .mostExpensiveSaleId(getMostExpensiveSale())
                .worstSalesmanName(getWorstSalesman())
                .build();
    }

    public void addSalesmanData(SalesmanData salesmanData) {
        this.salesmanData.add(salesmanData);
    }

    public void addCustomerData(CustomerData customerData) {
        this.customerData.add(customerData);
    }

    public void addSalesData(SalesData salesData) {
        this.salesData.add(salesData);
    }

    private Integer getTotalSalesman() {
        return Long.valueOf(salesmanData.stream()
                .map(SalesmanData::getCpf)
                .distinct()
                .count())
                .intValue();
    }

    private Integer getTotalCustomer() {
        return Long.valueOf(customerData.stream()
                .map(CustomerData::getCnpj)
                .distinct()
                .count())
                .intValue();
    }

    private String getMostExpensiveSale() {
        return salesData.stream()
                .max(Comparator.comparing(sale -> totalValueSale(sale.getItems())))
                .map(SalesData::getSaleId)
                .orElse("NONE");
    }

    private String getWorstSalesman() {
        HashMap<BigDecimal, String> salesmenRent = new HashMap<>();
        salesData.stream()
                .map(SalesData::getSalesmanName)
                .distinct()
                .forEach(salesmanName -> salesmenRent.put(salesmanRent(salesmanName), salesmanName));
        BigDecimal higherValue = salesmenRent.keySet().stream()
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        return Optional.ofNullable(salesmenRent.get(higherValue))
                .orElse("NONE");
    }

    private BigDecimal salesmanRent(String salesmanName) {
        return salesData.stream()
                .filter(sale -> sale.getSalesmanName().equals(salesmanName))
                .map(sale -> totalValueSale(sale.getItems()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal totalValueSale(List<ItemData> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}

package com.github.ftfetter.sales.business;

import com.github.ftfetter.sales.parser.CustomerParser;
import com.github.ftfetter.sales.parser.SalesParser;
import com.github.ftfetter.sales.parser.SalesmanParser;
import com.github.ftfetter.sales.pojos.CustomerData;
import com.github.ftfetter.sales.pojos.ItemData;
import com.github.ftfetter.sales.pojos.SalesData;
import com.github.ftfetter.sales.pojos.SalesMetrics;
import com.github.ftfetter.sales.pojos.SalesmanData;
import com.github.ftfetter.sales.type.DataType;
import com.github.ftfetter.sales.utils.RxFileReader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {

    private final CustomerParser customerParser;
    private final SalesmanParser salesmanParser;
    private final SalesParser salesParser;
    private List<CustomerData> customerList;
    private List<SalesData> salesList;
    private List<SalesmanData> salesmanList;

    private final String LINE_SPLITTER = "ç";

    public DataConverter() {
        this.customerParser = new CustomerParser();
        this.salesmanParser = new SalesmanParser();
        this.salesParser = new SalesParser();
    }

    public SalesMetrics generateMetric(String inputPath, String fileName) {
        clearLists();
        RxFileReader reader = new RxFileReader(inputPath);
        reader.readLines(fileName, 10)
                .map(line -> Arrays.asList(line.split(LINE_SPLITTER)))
                .subscribe(this::parser);

        orderSalesByValue();
        return SalesMetrics.Builder.of()
                .totalClients(customerList.size())
                .totalSalesman(salesmanList.size())
                .mostExpensiveSaleId(salesList.get(0).getSaleId())
                .worstSalesmanName(salesList.get(salesList.size()-1).getSalesmanName())
                .build();
    }

    private void clearLists() {
        customerList = new ArrayList<>();
        salesmanList = new ArrayList<>();
        salesList = new ArrayList<>();
    }

    private void parser(List<String> line) throws Exception {
        DataType categoryId = DataType.getById(line.get(0))
                .orElseThrow(() -> new Exception("Invalid ID."));

        if (categoryId.equals(DataType.SALESMAN)) {
            salesmanList.add(salesmanParser.parse(line));
        } else if (categoryId.equals(DataType.CUSTOMER)) {
            customerList.add(customerParser.parse(line));
        } else if (categoryId.equals(DataType.SALES)) {
            salesList.add(salesParser.parse(line));
        }
    }

    private List<SalesData> orderSalesByValue() {
        return salesList.stream()
                .sorted(Comparator.comparing(sale -> totalValueSale(sale.getItems())))
                .collect(Collectors.toList());
    }

    private BigDecimal totalValueSale(List<ItemData> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}

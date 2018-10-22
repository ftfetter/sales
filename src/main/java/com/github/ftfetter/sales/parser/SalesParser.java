package com.github.ftfetter.sales.parser;

import com.github.ftfetter.sales.pojos.ItemData;
import com.github.ftfetter.sales.pojos.SalesData;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalesParser {

    private final String ITEM_SPLITTER = ",";
    private final String ITEM_ATTRIBUTE_SPLITTER = "-";

    public SalesData parse(List<String> line) {
        return SalesData.Builder.of()
                .saleId(line.get(1))
                .items(parseItems(line.get(2)))
                .salesmanName(line.get(3))
                .build();
    }

    private List<ItemData> parseItems(String items) {
        return Stream.of(
                items.replace("[","")
                        .replace("]","")
                        .split(ITEM_SPLITTER))
                .map(item -> Arrays.asList(item.split(ITEM_ATTRIBUTE_SPLITTER)))
                .map(item -> ItemData.Builder.of()
                        .itemId(item.get(0))
                        .quantity(Integer.valueOf(item.get(1)))
                        .price(BigDecimal.valueOf(Double.parseDouble(item.get(2))))
                        .build())
                .collect(Collectors.toList());
    }
}

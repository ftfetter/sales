package com.github.ftfetter.sales.parser;

import com.github.ftfetter.sales.pojos.CustomerData;

import java.util.List;

public class CustomerParser {

    public static CustomerData parse(List<String> line) {
        System.out.println("PARSING CUSTOMER");
        return CustomerData.Builder.of()
                .cnpj(line.get(1))
                .name(line.get(2))
                .businessArea(line.get(3))
                .build();
    }
}

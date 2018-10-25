package com.github.ftfetter.sales.parser;

import com.github.ftfetter.sales.pojos.SalesmanData;

import java.math.BigDecimal;
import java.util.List;

public class SalesmanParser {

    public static SalesmanData parse(List<String> line) {
        System.out.println("PARSING SALESMAN");
        return SalesmanData.Builder.of()
                .cpf(line.get(1))
                .name(line.get(2))
                .salary(BigDecimal.valueOf(Double.parseDouble(line.get(3))))
                .build();
    }
}

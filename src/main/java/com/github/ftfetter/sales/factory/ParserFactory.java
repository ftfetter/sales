package com.github.ftfetter.sales.factory;

import com.github.ftfetter.sales.factory.parser.CustomerParser;
import com.github.ftfetter.sales.factory.parser.FileParser;
import com.github.ftfetter.sales.factory.parser.SalesParser;
import com.github.ftfetter.sales.factory.parser.SalesmanParser;

import java.util.stream.Stream;

public class ParserFactory {

    private final CustomerParser customerParser;
    private final SalesParser salesParser;
    private final SalesmanParser salesmanParser;

    public ParserFactory() {
        this.customerParser = new CustomerParser();
        this.salesParser = new SalesParser();
        this.salesmanParser = new SalesmanParser();
    }

    public Stream<FileParser> get() {
        return Stream.of(customerParser, salesParser, salesmanParser);
    }
}

package com.github.ftfetter.sales.observer.data;

import com.github.ftfetter.sales.business.MetricsService;
import com.github.ftfetter.sales.parser.SalesParser;

import java.util.List;

public class SalesObserver extends DataObserver {

    private final String SALES_ID = "003";
    private MetricsService metricsService;

    public SalesObserver(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public void onNext(List<String> line) {
        if (line.get(0).equals(SALES_ID)) {
            metricsService.addSalesData(SalesParser.parse(line));
        }
    }
}

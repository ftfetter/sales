package com.github.ftfetter.sales.observer.data;

import com.github.ftfetter.sales.business.MetricsService;
import com.github.ftfetter.sales.parser.SalesmanParser;

import java.util.List;

public class SalesmanObserver extends DataObserver {

    private final String SALESMAN_ID = "001";
    private MetricsService metricsService;

    public SalesmanObserver(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public void onNext(List<String> line) {
        if (line.get(0).equals(SALESMAN_ID)) {
            metricsService.addSalesmanData(SalesmanParser.parse(line));
        }
    }
}

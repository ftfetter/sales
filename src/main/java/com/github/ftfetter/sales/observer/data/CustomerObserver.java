package com.github.ftfetter.sales.observer.data;

import com.github.ftfetter.sales.business.MetricsService;
import com.github.ftfetter.sales.parser.CustomerParser;

import java.util.List;

public class CustomerObserver extends DataObserver {

    private final String CUSTOMER_ID = "002";
    private MetricsService metricsService;

    public CustomerObserver(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public void onNext(List<String> line) {
        if (line.get(0).equals(CUSTOMER_ID)) {
            metricsService.addCustomerData(CustomerParser.parse(line));
        }
    }
}

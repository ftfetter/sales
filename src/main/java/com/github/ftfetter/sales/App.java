package com.github.ftfetter.sales;

import com.github.ftfetter.sales.service.SalesService;

public class App {

    public static void main(String[] args) {
        try {
            SalesService watcher = new SalesService("./in");
            watcher.generateMetrics();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

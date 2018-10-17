package com.github.ftfetter.sales;

import com.github.ftfetter.sales.business.SalesService;

public class App {

    public static void main(String[] args) {
        try {
            SalesService watcher = new SalesService(System.getenv("HOMEPATH") + "/data");
            watcher.generateMetrics();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

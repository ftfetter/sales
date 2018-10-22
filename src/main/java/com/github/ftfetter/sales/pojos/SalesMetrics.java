package com.github.ftfetter.sales.pojos;

public class SalesMetrics {

    private Integer totalClients;
    private Integer totalSalesman;
    private String mostExpensiveSaleId;
    private String worstSalesmanName;

    public SalesMetrics(Integer totalClients, Integer totalSalesman, String mostExpensiveSaleId, String worstSalesmanName) {
        this.totalClients = totalClients;
        this.totalSalesman = totalSalesman;
        this.mostExpensiveSaleId = mostExpensiveSaleId;
        this.worstSalesmanName = worstSalesmanName;
    }

    public Integer getTotalClients() {
        return totalClients;
    }

    public Integer getTotalSalesman() {
        return totalSalesman;
    }

    public String getMostExpensiveSaleId() {
        return mostExpensiveSaleId;
    }

    public String getWorstSalesmanName() {
        return worstSalesmanName;
    }

    public static final class Builder {

        private Integer totalClients;
        private Integer totalSalesman;
        private String mostExpensiveSaleId;
        private String worstSalesmanName;

        public static Builder of() {
            return new Builder();
        }

        public Builder totalClients(Integer totalClients) {
            this.totalClients = totalClients;
            return this;
        }

        public Builder totalSalesman(Integer totalSalesman) {
            this.totalSalesman = totalSalesman;
            return this;
        }

        public Builder mostExpensiveSaleId(String mostExpensiveSaleId) {
            this.mostExpensiveSaleId = mostExpensiveSaleId;
            return this;
        }

        public Builder worstSalesmanName(String worstSalesmanName) {
            this.worstSalesmanName = worstSalesmanName;
            return this;
        }

        public SalesMetrics build() {
            return new SalesMetrics(totalClients,totalSalesman,mostExpensiveSaleId,worstSalesmanName);
        }
    }

    @Override
    public String toString() {
        return "Total Clients: " + totalClients + "; \n" +
                "Total Salesman: " + totalSalesman + "; \n" +
                "Most expensive Sale: " + mostExpensiveSaleId + "; \n" +
                "Worst Salesman: " + worstSalesmanName + ";";
    }
}

package com.github.ftfetter.sales.pojos;

import java.util.List;

public class SalesData {

    private String saleId;
    private List<ItemData> items;
    private String salesmanName;

    public SalesData(String saleId, List<ItemData> items, String salesmanName) {
        this.saleId = saleId;
        this.items = items;
        this.salesmanName = salesmanName;
    }

    public String getSaleId() {
        return saleId;
    }

    public List<ItemData> getItems() {
        return items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public static final class Builder {

        private String saleId;
        private List<ItemData> items;
        private String salesmanName;

        public static Builder of() {
            return new Builder();
        }

        public Builder saleId(String saleId) {
            this.saleId = saleId;
            return this;
        }

        public Builder items(List<ItemData> items) {
            this.items = items;
            return this;
        }

        public Builder salesmanName(String salesmanName) {
            this.salesmanName = salesmanName;
            return this;
        }

        public SalesData build() {
            return new SalesData(saleId,items,salesmanName);
        }
    }
}

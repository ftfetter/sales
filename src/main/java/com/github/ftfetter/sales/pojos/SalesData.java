package com.github.ftfetter.sales.pojos;

import com.github.ftfetter.sales.type.DataType;

import java.util.List;

public class SalesData extends FileData {

    private String saleId;
    private List<ItemData> items;
    private String salesmanName;

    public SalesData(String saleId, List<ItemData> items, String salesmanName) {
        super(DataType.SALES);
        this.saleId = saleId;
        this.items = items;
        this.salesmanName = salesmanName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public List<ItemData> getItems() {
        return items;
    }

    public void setItems(List<ItemData> items) {
        this.items = items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }
}

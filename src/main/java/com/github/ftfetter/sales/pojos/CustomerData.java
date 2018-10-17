package com.github.ftfetter.sales.pojos;

import com.github.ftfetter.sales.type.DataType;

public class CustomerData extends FileData {

    private String cnpj;
    private String name;
    private String businessArea;

    public CustomerData(String cnpj, String name, String businessArea) {
        super(DataType.CUSTOMER);
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
}

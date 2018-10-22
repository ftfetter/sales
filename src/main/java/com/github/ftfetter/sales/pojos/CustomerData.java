package com.github.ftfetter.sales.pojos;

public class CustomerData {

    private String cnpj;
    private String name;
    private String businessArea;

    public CustomerData(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public static final class Builder {

        private String cnpj;
        private String name;
        private String businessArea;

        public static Builder of() {
            return new Builder();
        }

        public Builder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder businessArea(String businessArea) {
            this.businessArea = businessArea;
            return this;
        }

        public CustomerData build() {
            return new CustomerData(cnpj,name,businessArea);
        }
    }
}

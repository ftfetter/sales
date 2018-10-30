package com.github.ftfetter.sales.pojos;

import java.math.BigDecimal;

public class SalesmanData {

    private String cpf;
    private String name;
    private BigDecimal salary;

    public SalesmanData(String cpf, String name, BigDecimal salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public static final class Builder {

        private String cpf;
        private String name;
        private BigDecimal salary;

        public static Builder of() {
            return new Builder();
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public SalesmanData build() {
            return new SalesmanData(cpf,name,salary);
        }
    }
}

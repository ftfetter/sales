package com.github.ftfetter.sales.pojos;

import com.github.ftfetter.sales.type.DataType;

import java.math.BigDecimal;

public class SalesmanData extends FileData {

    private String cpf;
    private String name;
    private BigDecimal salary;

    public SalesmanData(String cpf, String name, BigDecimal salary) {
        super(DataType.SALESMAN);
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}

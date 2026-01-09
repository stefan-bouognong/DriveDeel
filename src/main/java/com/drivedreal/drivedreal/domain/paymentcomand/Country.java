package com.drivedreal.drivedreal.domain.paymentcomand;

public class Country {

    private Long id;
    private String name;
    private double taxRate;

    public Country(Long id, String name, double taxRate) {
        this.id = id;
        this.name = name;
        this.taxRate = taxRate;
    }

    public Long getId(){
        return id;
    }


    public String getName(){
        return name;
    }


    public double getTaxRate() {
        return taxRate;
    }
}

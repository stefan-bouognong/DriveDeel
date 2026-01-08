package com.drivedreal.drivedreal.domain.vehicle;

import java.math.BigDecimal;

public class ElectricAutomobile implements Vehicle {
    private final String brand;
    private final String model;
    private final BigDecimal basePrice;

    public ElectricAutomobile(String brand, String model, BigDecimal basePrice) {
        this.brand = brand;
        this.model = model;
        this.basePrice = basePrice;
    }

    @Override
    public String getType() {
        return "Automobile Électrique";
    }

    @Override
    public String getDescription() {
        return brand + " " + model + " (Électrique)";
    }

    @Override
    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
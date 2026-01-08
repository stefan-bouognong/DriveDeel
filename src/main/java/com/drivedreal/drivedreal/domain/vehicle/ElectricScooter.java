package com.drivedreal.drivedreal.domain.vehicle;

import java.math.BigDecimal;

public class ElectricScooter implements Vehicle {
    private final String brand;
    private final String model;
    private final BigDecimal basePrice;

    public ElectricScooter(String brand, String model, BigDecimal basePrice) {
        this.brand = brand;
        this.model = model;
        this.basePrice = basePrice;
    }

    @Override
    public String getType() {
        return "Scooter Électrique";
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
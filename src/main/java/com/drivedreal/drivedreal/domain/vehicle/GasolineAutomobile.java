// domain/GasolineAutomobile.java
package com.drivedreal.drivedreal.domain.vehicle;

import java.math.BigDecimal;

public class GasolineAutomobile implements Vehicle {
    private final String brand;
    private final String model;
    private final BigDecimal basePrice;

    public GasolineAutomobile(String brand, String model, BigDecimal basePrice) {
        this.brand = brand;
        this.model = model;
        this.basePrice = basePrice;
    }

    @Override
    public String getType() {
        return "Automobile Essence";
    }

    @Override
    public String getDescription() {
        return brand + " " + model + " (Essence)";
    }

    @Override
    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
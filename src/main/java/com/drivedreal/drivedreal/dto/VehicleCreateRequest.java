package com.drivedreal.drivedreal.dto;

import java.math.BigDecimal;

public class VehicleCreateRequest {

    private String brand;
    private String reference; 
    private String model;
    private BigDecimal basePrice;  

    
    public String getDescription() {
        return brand + " " + model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}

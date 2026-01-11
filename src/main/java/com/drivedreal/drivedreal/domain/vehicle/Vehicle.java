package com.drivedreal.drivedreal.domain.vehicle;

public class Vehicle {

    private String idVehicle;
    private String reference;
    private String brand;
    private String vehicleType;
    private double basePrice;
    private String description;
    private String stockEntryDate;
    private String stockStatus;

    public Vehicle(String idVehicle, String brand, double basePrice) {
        this.idVehicle = idVehicle;
        this.brand = brand;
        this.basePrice = basePrice;
        this.stockStatus = "IN_STOCK";
    }

    public double getPrice() {
        return basePrice;
    }

    public void applyDiscount(double rate) {
        basePrice = basePrice * (1 - rate);
    }

    public void setStockStatus(String status) {
        this.stockStatus = status;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    @Override
    public String toString() {
        return String.format("Vehicle[id=%s, brand=%s, price=%.2f, status=%s]", 
            idVehicle, brand, basePrice, stockStatus);
    }
}


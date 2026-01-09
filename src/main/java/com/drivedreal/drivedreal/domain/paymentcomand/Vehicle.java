package com.drivedreal.drivedreal.domain.paymentcomand;

public class Vehicle {

    private Long id;
    private String name;
    private double price;
    private boolean oldStock;
    public Vehicle(Long id, String name, double price, boolean oldStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.oldStock = oldStock;
    }

    public double getPrice() {
        return price;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public boolean isOldStock() {
        return oldStock;
    }
}

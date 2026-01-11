package com.drivedreal.drivedreal.services.sales.command;

import com.drivedreal.drivedreal.domain.vehicle.Vehicle;

public class SaleVehicleCommand implements ICommand {

    private Vehicle vehicle;
    private double discountRate;
    private double originalPrice;
    private boolean executed;

    public SaleVehicleCommand(Vehicle vehicle, double discountRate) {
        this.vehicle = vehicle;
        this.discountRate = discountRate;
        this.originalPrice = vehicle.getPrice();
        this.executed = false;
    }

    @Override
    public void execute() {
        if (!executed) {
            originalPrice = vehicle.getPrice();
            vehicle.applyDiscount(discountRate);
            vehicle.setStockStatus("ON_SALE");
            executed = true;
            System.out.println("Executed: " + getDescription());
        }
    }

    @Override
    public void undo() {
        if (executed) {
            vehicle.applyDiscount(-discountRate / (1 - discountRate)); // Restore original price
            vehicle.setStockStatus("IN_STOCK");
            executed = false;
            System.out.println("Undone: " + getDescription());
        }
    }

    @Override
    public String getDescription() {
        return String.format("SaleVehicleCommand[vehicle=%s, discount=%.2f%%]",
            vehicle.getIdVehicle(), discountRate * 100);
    }
}


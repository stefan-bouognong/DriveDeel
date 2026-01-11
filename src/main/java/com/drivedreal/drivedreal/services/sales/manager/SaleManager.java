package com.drivedreal.drivedreal.services.sales.manager;

import com.drivedreal.drivedreal.domain.vehicle.Vehicle;
import com.drivedreal.drivedreal.services.sales.command.SaleVehicleCommand;
import com.drivedreal.drivedreal.services.sales.invoker.CommandInvoker;
import java.util.ArrayList;
import java.util.List;

public class SaleManager {

    private CommandInvoker invoker;
    private int stockDurationThreshold = 30; // days in stock

    public SaleManager(CommandInvoker invoker) {
        this.invoker = invoker;
    }

    public List<Vehicle> findOldStockVehicles(List<Vehicle> vehicles) {
        // Simulate selection of vehicles in stock > threshold
        List<Vehicle> oldStock = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if ("IN_STOCK".equals(v.getStockStatus())) {
                oldStock.add(v);
            }
        }
        return oldStock;
    }

    public void applySaleToVehicle(Vehicle vehicle, double rate) {
        SaleVehicleCommand command = new SaleVehicleCommand(vehicle, rate);
        invoker.executeCommand(command);
    }

    public void undoLastSale() {
        invoker.undo();
    }
}

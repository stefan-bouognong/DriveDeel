package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.domain.vehicle.Vehicle;
import com.drivedreal.drivedreal.services.sales.invoker.CommandInvoker;
import com.drivedreal.drivedreal.services.sales.manager.SaleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SaleController {

    private CommandInvoker invoker = new CommandInvoker();
    private SaleManager manager = new SaleManager(invoker);

    private List<Vehicle> vehicles;

    public SaleController() {
        // Sample data
        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("V001", "Toyota", 10000));
        vehicles.add(new Vehicle("V002", "Honda", 12000));
        vehicles.add(new Vehicle("V003", "Ford", 9000));
    }

    @GetMapping("/api/sales/apply")
    public String applySales() {
        List<Vehicle> oldStock = manager.findOldStockVehicles(vehicles);
        for (Vehicle v : oldStock) {
            manager.applySaleToVehicle(v, 0.2); // 20% discount
        }
        return "Sales applied to old stock vehicles!";
    }

    @GetMapping("/api/sales/undo")
    public String undoLastSale() {
        manager.undoLastSale();
        return "Last sale undone!";
    }

    @GetMapping("/api/sales/list")
    public List<Vehicle> listVehicles() {
        return vehicles;
    }
}


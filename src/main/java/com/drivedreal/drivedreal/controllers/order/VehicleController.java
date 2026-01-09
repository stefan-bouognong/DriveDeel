package com.drivedreal.drivedreal.controllers.order;

import com.drivedreal.drivedreal.domain.paymentcomand.Vehicle;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final Map<Long, Vehicle> vehicles = new HashMap<>();

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicles.get(id);
    }

    @GetMapping
    public Collection<Vehicle> getAllVehicles() {
        return vehicles.values();
    }
}


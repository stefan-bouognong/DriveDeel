package com.drivedreal.drivedreal.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.entity.VehicleEntity;
import com.drivedreal.drivedreal.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // ===== CREATE =====
    @PostMapping("/automobile")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleEntity createAutomobile(@RequestBody VehicleCreateRequest request) {
        return vehicleService.createAutomobile(request);
    }

    @PostMapping("/scooter")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleEntity createScooter(@RequestBody VehicleCreateRequest request) {
        return vehicleService.createScooter(request);
    }

    // ===== READ =====
    @GetMapping
    public List<VehicleEntity> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public VehicleEntity getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicle(id);
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    public VehicleEntity updateVehicle(@PathVariable Long id,
                                       @RequestBody VehicleCreateRequest request) {
        return vehicleService.updateVehicle(id, request);
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}

package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.domain.vehicle.VehicleType;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleEntity createVehicle(@RequestBody VehicleCreateRequest request) {
        switch (request.getVehicleType()) {
            case AUTOMOBILE:
                return vehicleService.createAutomobile(request);
            case SCOOTER:
                return vehicleService.createScooter(request);
            default:
                throw new IllegalArgumentException("Type de véhicule non pris en charge.");
        }
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

package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.domain.vehicle.VehicleType;
import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.repository.KeywordRepository;
import com.drivedreal.drivedreal.repository.VehicleAnimationRepository;
import com.drivedreal.drivedreal.repository.VehicleOptionRepository;
import com.drivedreal.drivedreal.services.VehicleAnimationDecorator;
import com.drivedreal.drivedreal.services.VehicleKeywordDecorator;
import com.drivedreal.drivedreal.services.VehicleOptionDecorator;
import com.drivedreal.drivedreal.services.VehicleServiceImpl;
import com.drivedreal.drivedreal.dto.response.VehicleResponse;
import com.drivedreal.drivedreal.entity.VehicleEntity;
import com.drivedreal.drivedreal.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(
            VehicleServiceImpl baseService,
            VehicleOptionRepository optionRepository,
            VehicleAnimationRepository animationRepository,
            KeywordRepository keywordRepository
    ) {
        VehicleService service = baseService;

        // Decorator pattern (TP exigé)
        service = new VehicleOptionDecorator(service, optionRepository);
        service = new VehicleAnimationDecorator(service, animationRepository);
        service = new VehicleKeywordDecorator(service, keywordRepository);

        this.vehicleService = service;
    }

    // ===== CREATE =====
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse createVehicle(@RequestBody VehicleCreateRequest request) {

        return switch (request.getVehicleType()) {
            case AUTOMOBILE -> vehicleService.createAutomobile(request);
            case SCOOTER -> vehicleService.createScooter(request);
        };
    }

    // ===== READ (CATALOGUE) =====
    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    public VehicleResponse updateVehicle(
            @PathVariable Long id,
            @RequestBody VehicleCreateRequest request
    ) {
        return vehicleService.updateVehicle(id, request);
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}

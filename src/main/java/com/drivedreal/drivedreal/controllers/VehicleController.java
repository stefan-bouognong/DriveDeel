package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.dto.VehicleDTO;
import com.drivedreal.drivedreal.repository.KeywordRepository;
import com.drivedreal.drivedreal.repository.VehicleAnimationRepository;
import com.drivedreal.drivedreal.repository.VehicleOptionRepository;
import com.drivedreal.drivedreal.services.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
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

        service = new VehicleOptionDecorator(service, optionRepository);
        service = new VehicleAnimationDecorator(service, animationRepository);
        service = new VehicleKeywordDecorator(service, keywordRepository);

        this.vehicleService = service;
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }
}


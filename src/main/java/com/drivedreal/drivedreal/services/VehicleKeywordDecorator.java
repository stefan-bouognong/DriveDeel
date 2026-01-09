package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.dto.VehicleDTO;
import com.drivedreal.drivedreal.repository.KeywordRepository;

import java.util.List;

public class VehicleKeywordDecorator extends VehicleServiceDecorator {

    private final KeywordRepository keywordRepository;

    public VehicleKeywordDecorator(
            VehicleService vehicleService,
            KeywordRepository keywordRepository
    ) {
        super(vehicleService);
        this.keywordRepository = keywordRepository;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();

        vehicles.forEach(vehicle -> {
            vehicle.setKeywords(
                    keywordRepository.findByVehiclesId(vehicle.getId())
            );
        });

        return vehicles;
    }
}

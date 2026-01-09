package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.dto.VehicleDTO;
import com.drivedreal.drivedreal.repository.VehicleOptionRepository;

import java.util.List;

public class VehicleOptionDecorator extends VehicleServiceDecorator {

    private final VehicleOptionRepository optionRepository;

    public VehicleOptionDecorator(
            VehicleService vehicleService,
            VehicleOptionRepository optionRepository
    ) {
        super(vehicleService);
        this.optionRepository = optionRepository;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();

        vehicles.forEach(vehicle -> {
            vehicle.setOptions(
                    optionRepository.findByVehicleId(vehicle.getId())
            );
        });

        return vehicles;
    }
}

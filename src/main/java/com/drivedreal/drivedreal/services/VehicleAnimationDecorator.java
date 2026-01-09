package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.dto.VehicleDTO;
import com.drivedreal.drivedreal.repository.VehicleAnimationRepository;

import java.util.List;

public class VehicleAnimationDecorator extends VehicleServiceDecorator {

    private final VehicleAnimationRepository animationRepository;

    public VehicleAnimationDecorator(
            VehicleService vehicleService,
            VehicleAnimationRepository animationRepository
    ) {
        super(vehicleService);
        this.animationRepository = animationRepository;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();

        vehicles.forEach(vehicle -> {
            vehicle.setAnimations(
                    animationRepository.findByVehicleId(vehicle.getId())
            );
        });

        return vehicles;
    }
}

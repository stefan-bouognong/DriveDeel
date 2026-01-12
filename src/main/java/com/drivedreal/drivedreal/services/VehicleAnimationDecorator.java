package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.service.VehicleService;

import com.drivedreal.drivedreal.dto.response.VehicleResponse;
import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.repository.VehicleAnimationRepository;
import java.util.stream.Collectors;
import com.drivedreal.drivedreal.model.VehicleAnimation;

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
    public VehicleResponse createAutomobile(VehicleCreateRequest request) {
        return vehicleService.createAutomobile(request);
    }

    @Override
    public VehicleResponse createScooter(VehicleCreateRequest request) {
        return vehicleService.createScooter(request);
    }

    @Override
    public VehicleResponse getVehicle(Long id) {
        return vehicleService.getVehicle(id);
    }

    @Override
    public List<VehicleResponse> getAllVehicles() {
        List<VehicleResponse> vehicles = vehicleService.getAllVehicles();

        vehicles.forEach(vehicle -> {
            vehicle.setAnimations(animationRepository.findByVehicleId(vehicle.getId()).stream().map(VehicleAnimation::getAnimationType).collect(Collectors.toList()));
        });

        return vehicles;
    }

    @Override
    public VehicleResponse updateVehicle(Long id, VehicleCreateRequest request) {
        return vehicleService.updateVehicle(id, request);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleService.deleteVehicle(id);
    }
}

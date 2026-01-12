package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.service.VehicleService;

import com.drivedreal.drivedreal.dto.response.VehicleResponse;
import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.model.VehicleOption; // Added import
import com.drivedreal.drivedreal.repository.VehicleOptionRepository;

import java.util.List;
import java.util.stream.Collectors;

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
            vehicle.setOptions(optionRepository.findByVehicleId(vehicle.getId()).stream().map(VehicleOption::getOptionName).collect(Collectors.toList()));
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

package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.service.VehicleService;

import com.drivedreal.drivedreal.dto.response.VehicleResponse;
import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.repository.KeywordRepository;
import com.drivedreal.drivedreal.model.Keyword;
import java.util.List;
import java.util.stream.Collectors;

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
            vehicle.setKeywords(keywordRepository.findByVehiclesId(vehicle.getId()).stream().map(Keyword::getKeywordValue).collect(Collectors.toList()));
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

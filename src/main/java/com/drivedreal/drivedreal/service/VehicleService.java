package com.drivedreal.drivedreal.service;

import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.dto.response.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse createAutomobile(VehicleCreateRequest request);
    VehicleResponse createScooter(VehicleCreateRequest request);
    VehicleResponse getVehicle(Long id);
    List<VehicleResponse> getAllVehicles();
    VehicleResponse updateVehicle(Long id, VehicleCreateRequest request);
    void deleteVehicle(Long id);
}

package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.dto.VehicleDTO;
import com.drivedreal.drivedreal.model.Vehicle;

import com.drivedreal.drivedreal.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream().map(VehicleDTO::new).collect(Collectors.toList());
    }
}

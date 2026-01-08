package com.drivedreal.drivedreal.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.drivedreal.drivedreal.entity.VehicleEntity;
import com.drivedreal.drivedreal.domain.vehicle.Vehicle;
import com.drivedreal.drivedreal.domain.vehicle.factory.VehicleFactory;
import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.repository.VehicleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class VehicleService {

    private final VehicleFactory vehicleFactory;
    private final VehicleRepository vehicleRepository;

    public VehicleService(@Qualifier("electricVehicleFactory") VehicleFactory vehicleFactory,
                          VehicleRepository vehicleRepository) {
        this.vehicleFactory = vehicleFactory;
        this.vehicleRepository = vehicleRepository;
    }

    // ===== CREATE =====
    public VehicleEntity createAutomobile(VehicleCreateRequest request) {
        Vehicle vehicle = vehicleFactory.createAutomobile(
                request.getBrand(),
                request.getModel(),
                request.getBasePrice()
        );
        return mapToEntity(vehicle, request);
    }

    public VehicleEntity createScooter(VehicleCreateRequest request) {
        Vehicle vehicle = vehicleFactory.createScooter(
                request.getBrand(),
                request.getModel(),
                request.getBasePrice()
        );
        return mapToEntity(vehicle, request);
    }

    // Méthode privée pour mapper DTO + objet métier → Entity
    private VehicleEntity mapToEntity(Vehicle vehicle, VehicleCreateRequest request) {
        return VehicleEntity.builder()
                .brand(request.getBrand())
                .reference(request.getReference())
                .vehicleType(vehicle.getType())
                .description(request.getDescription())
                .basePrice(request.getBasePrice())
                .stockEntryDate(LocalDate.now())
                .stockStatus("IN_STOCK")
                .build();
    }

    // ===== READ =====
    public VehicleEntity getVehicle(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
    }

    public java.util.List<VehicleEntity> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // ===== UPDATE =====
    public VehicleEntity updateVehicle(Long id, VehicleCreateRequest request) {
        VehicleEntity entity = getVehicle(id);
        entity.setBrand(request.getBrand());
        entity.setReference(request.getReference());
        entity.setBasePrice(request.getBasePrice());
        entity.setDescription(request.getDescription());
        return vehicleRepository.save(entity);
    }

    // ===== DELETE =====
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}

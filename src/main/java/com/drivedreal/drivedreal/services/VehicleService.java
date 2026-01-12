package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.repository.CatalogueRepository;
import com.drivedreal.drivedreal.entity.Catalogue;
import java.io.FileWriter;
import java.io.IOException;

import com.drivedreal.drivedreal.domain.vehicle.PropulsionType;
import com.drivedreal.drivedreal.domain.vehicle.factory.ElectricVehicleFactory;
import com.drivedreal.drivedreal.domain.vehicle.factory.GasolineVehicleFactory;
import com.drivedreal.drivedreal.domain.vehicle.factory.VehicleFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.drivedreal.drivedreal.entity.VehicleEntity;
import com.drivedreal.drivedreal.domain.vehicle.Vehicle;
import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.repository.VehicleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final GasolineVehicleFactory gasolineVehicleFactory;
    private final ElectricVehicleFactory electricVehicleFactory;
    private final VehicleRepository vehicleRepository;
    private final CatalogueRepository catalogueRepository;

    // ===== CREATE =====
    public VehicleEntity createAutomobile(VehicleCreateRequest request) {
        Catalogue catalogue = catalogueRepository.findById(request.getCatalogueId())
                .orElseThrow(() -> new IllegalArgumentException("Catalogue non trouvé."));

        VehicleFactory factory;
        if (request.getPropulsionType() == PropulsionType.ELECTRIC) {
            factory = electricVehicleFactory;
        } else {
            factory = gasolineVehicleFactory;
        }
        Vehicle vehicle = factory.createAutomobile(
                request.getBrand(),
                request.getModel(),
                request.getBasePrice()
        );
        VehicleEntity savedVehicle = vehicleRepository.save(mapToEntity(vehicle, request, catalogue));
        catalogue.addVehicle(savedVehicle);
        catalogueRepository.save(catalogue);
        catalogue.notifyObservers("Nouveau véhicule ajouté au catalogue: " + savedVehicle.getBrand() + " " + savedVehicle.getModel());
        return savedVehicle;
    }

    public VehicleEntity createScooter(VehicleCreateRequest request) {
        Catalogue catalogue = catalogueRepository.findById(request.getCatalogueId())
                .orElseThrow(() -> new IllegalArgumentException("Catalogue non trouvé."));

        VehicleFactory factory;
        if (request.getPropulsionType() == PropulsionType.ELECTRIC) {
            factory = electricVehicleFactory;
        } else {
            factory = gasolineVehicleFactory;
        }
        Vehicle vehicle = factory.createScooter(
                request.getBrand(),
                request.getModel(),
                request.getBasePrice()
        );
        VehicleEntity savedVehicle = vehicleRepository.save(mapToEntity(vehicle, request, catalogue));
        catalogue.addVehicle(savedVehicle);
        catalogueRepository.save(catalogue);
        catalogue.notifyObservers("Nouveau véhicule ajouté au catalogue: " + savedVehicle.getBrand() + " " + savedVehicle.getModel());
        return savedVehicle;
    }

    // Méthode privée pour mapper DTO + objet métier → Entity
    private VehicleEntity mapToEntity(Vehicle vehicle, VehicleCreateRequest request, Catalogue catalogue) {
        return VehicleEntity.builder()
                .brand(request.getBrand())
                .reference(request.getReference())
                .vehicleType(vehicle.getVehicleType())
                .propulsionType(vehicle.getPropulsionType())
                .description(request.getDescription())
                .basePrice(request.getBasePrice())
                .stockEntryDate(LocalDate.now())
                .stockStatus("IN_STOCK")
                .catalogue(catalogue)
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

    // #region agent log
    private void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter("c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {
            fw.write(String.format("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"%s\",\"location\":\"VehicleService.java:%d\",\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}\n",
                     hypothesisId, new Throwable().getStackTrace()[1].getLineNumber(), message, data != null ? data.toString() : "null", System.currentTimeMillis()));
        } catch (IOException e) { /* ignore */ }
    }
    // #endregion
}

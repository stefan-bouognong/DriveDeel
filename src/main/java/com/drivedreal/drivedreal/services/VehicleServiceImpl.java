package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.dto.VehicleCreateRequest;
import com.drivedreal.drivedreal.dto.response.VehicleResponse;
import com.drivedreal.drivedreal.domain.vehicle.PropulsionType;
import com.drivedreal.drivedreal.domain.vehicle.Vehicle;
import com.drivedreal.drivedreal.domain.vehicle.factory.ElectricVehicleFactory;
import com.drivedreal.drivedreal.domain.vehicle.factory.GasolineVehicleFactory;
import com.drivedreal.drivedreal.domain.vehicle.factory.VehicleFactory;
import com.drivedreal.drivedreal.entity.Catalogue;
import com.drivedreal.drivedreal.entity.VehicleEntity;
import com.drivedreal.drivedreal.repository.CatalogueRepository;
import com.drivedreal.drivedreal.repository.VehicleRepository;
import com.drivedreal.drivedreal.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final GasolineVehicleFactory gasolineVehicleFactory;
    private final ElectricVehicleFactory electricVehicleFactory;
    private final VehicleRepository vehicleRepository;
    private final CatalogueRepository catalogueRepository;

    // ===== CREATE =====

    @Override
    public VehicleResponse createAutomobile(VehicleCreateRequest request) {
        Catalogue catalogue = catalogueRepository.findById(request.getCatalogueId())
                .orElseThrow(() -> new IllegalArgumentException("Catalogue non trouvé."));

        VehicleFactory factory = request.getPropulsionType() == PropulsionType.ELECTRIC
                ? electricVehicleFactory
                : gasolineVehicleFactory;

        Vehicle vehicle = factory.createAutomobile(
                request.getBrand(),
                request.getModel(),
                request.getBasePrice()
        );

        VehicleEntity savedVehicle = vehicleRepository.save(
                mapToEntity(vehicle, request, catalogue)
        );

        catalogue.addVehicle(savedVehicle);
        catalogueRepository.save(catalogue);
        catalogue.notifyObservers(
                "Nouveau véhicule ajouté : " + savedVehicle.getBrand() + " " + savedVehicle.getModel()
        );

        return mapToResponse(savedVehicle);
    }

    @Override
    public VehicleResponse createScooter(VehicleCreateRequest request) {
        Catalogue catalogue = catalogueRepository.findById(request.getCatalogueId())
                .orElseThrow(() -> new IllegalArgumentException("Catalogue non trouvé."));

        VehicleFactory factory = request.getPropulsionType() == PropulsionType.ELECTRIC
                ? electricVehicleFactory
                : gasolineVehicleFactory;

        Vehicle vehicle = factory.createScooter(
                request.getBrand(),
                request.getModel(),
                request.getBasePrice()
        );

        VehicleEntity savedVehicle = vehicleRepository.save(
                mapToEntity(vehicle, request, catalogue)
        );

        catalogue.addVehicle(savedVehicle);
        catalogueRepository.save(catalogue);
        catalogue.notifyObservers(
                "Nouveau véhicule ajouté : " + savedVehicle.getBrand() + " " + savedVehicle.getModel()
        );

        return mapToResponse(savedVehicle);
    }

    // ===== READ =====

    @Override
    public VehicleResponse getVehicle(Long id) {
        VehicleEntity vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        return mapToResponse(vehicle);
    }

    @Override
    public List<VehicleResponse> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ===== UPDATE =====

    @Override
    public VehicleResponse updateVehicle(Long id, VehicleCreateRequest request) {
        VehicleEntity entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        entity.setBrand(request.getBrand());
        entity.setReference(request.getReference());
        entity.setBasePrice(request.getBasePrice());
        entity.setDescription(request.getDescription());

        return mapToResponse(vehicleRepository.save(entity));
    }

    // ===== DELETE =====

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    // ===== MAPPERS =====

    private VehicleEntity mapToEntity(Vehicle vehicle, VehicleCreateRequest request, Catalogue catalogue) {
        return VehicleEntity.builder()
                .brand(request.getBrand())
                .model(request.getModel())
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

    private VehicleResponse mapToResponse(VehicleEntity vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .reference(vehicle.getReference())
                .basePrice(vehicle.getBasePrice())
                .description(vehicle.getDescription())
                .vehicleType(vehicle.getVehicleType())
                .propulsionType(vehicle.getPropulsionType())
                .stockEntryDate(vehicle.getStockEntryDate())
                .stockStatus(vehicle.getStockStatus())
                .catalogueId(vehicle.getCatalogue().getId())
                .catalogueName(vehicle.getCatalogue().getName())
                .build();
    }

    // ===== DEBUG LOG =====

    private void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter(
                "c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {

            fw.write(String.format(
                    "{\"hypothesisId\":\"%s\",\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}%n",
                    hypothesisId,
                    message,
                    data != null ? data.toString() : "null",
                    System.currentTimeMillis()
            ));
        } catch (IOException ignored) {
        }
    }
}

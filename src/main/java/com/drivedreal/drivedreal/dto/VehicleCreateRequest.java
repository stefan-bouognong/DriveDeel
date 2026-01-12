package com.drivedreal.drivedreal.dto;

import com.drivedreal.drivedreal.domain.vehicle.PropulsionType;
import com.drivedreal.drivedreal.domain.vehicle.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCreateRequest {

    private String brand;
    private String reference;
    private String model;
    private BigDecimal basePrice;
    private String description;
    private VehicleType vehicleType;
    private PropulsionType propulsionType;
    private Long catalogueId;
}

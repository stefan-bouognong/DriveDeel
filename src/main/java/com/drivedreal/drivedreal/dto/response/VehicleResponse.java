package com.drivedreal.drivedreal.dto.response;

import com.drivedreal.drivedreal.domain.vehicle.PropulsionType;
import com.drivedreal.drivedreal.domain.vehicle.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private Long id;
    private String brand;
    private String reference;
    private String model;
    private BigDecimal basePrice;
    private String description;
    private VehicleType vehicleType;
    private PropulsionType propulsionType;
    private LocalDate stockEntryDate;
    private String stockStatus;
    private Long catalogueId;
    private String catalogueName;
    private List<String> options;
    private List<String> keywords;
    private List<String> animations;
}
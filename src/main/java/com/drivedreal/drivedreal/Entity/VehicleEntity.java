package com.drivedreal.drivedreal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String reference;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    private String model;

    private String description;

    @Column(name = "stock_entry_date")
    private LocalDate stockEntryDate;

    @Column(name = "stock_status")
    private String stockStatus;
}

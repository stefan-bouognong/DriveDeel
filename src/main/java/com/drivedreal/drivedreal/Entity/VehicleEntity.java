package com.drivedreal.drivedreal.entity;

import com.drivedreal.drivedreal.domain.vehicle.PropulsionType;
import com.drivedreal.drivedreal.domain.vehicle.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private PropulsionType propulsionType;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    private String model;

    private String description;

    @Column(name = "stock_entry_date")
    private LocalDate stockEntryDate;

    @Column(name = "stock_status")
    private String stockStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogue_id", nullable = false)
    @JsonBackReference
    private Catalogue catalogue;
}

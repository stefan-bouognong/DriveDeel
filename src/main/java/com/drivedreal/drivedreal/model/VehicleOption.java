package com.drivedreal.drivedreal.model;

import jakarta.persistence.*;

@Entity
public class VehicleOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionName;
    private Double optionPrice;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Long getId() { return id; }
    public String getOptionName() { return optionName; }
    public Double getOptionPrice() { return optionPrice; }
}

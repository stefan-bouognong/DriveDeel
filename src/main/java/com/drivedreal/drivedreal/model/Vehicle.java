package com.drivedreal.drivedreal.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private String brand;
    private String model;
    private String vehicleType;
    private Double basePrice;
    private String description;
    private LocalDate stockEntryDate;
    private String stockStatus; // normal / clearance

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<VehicleOption> options;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<VehicleAnimation> animations;

    @ManyToMany
    @JoinTable(
        name = "vehicle_keywords",
        joinColumns = @JoinColumn(name = "vehicle_id"),
        inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private List<Keyword> keywords;

    // Getters
    public Long getId() { return id; }
    public String getReference() { return reference; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getVehicleType() { return vehicleType; }
    public Double getBasePrice() { return basePrice; }
    public String getDescription() { return description; }
    public List<VehicleOption> getOptions() { return options; }
    public List<VehicleAnimation> getAnimations() { return animations; }
    public List<Keyword> getKeywords() { return keywords; }
}

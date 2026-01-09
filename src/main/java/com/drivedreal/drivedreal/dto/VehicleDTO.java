package com.drivedreal.drivedreal.dto;

import com.drivedreal.drivedreal.model.Vehicle;
import com.drivedreal.drivedreal.model.VehicleOption;
import com.drivedreal.drivedreal.model.VehicleAnimation;
import com.drivedreal.drivedreal.model.Keyword;
import java.util.List;

public class VehicleDTO {

    private Long id;
    private String brand;
    private String model;
    private String vehicleType;
    private Double basePrice;
    private String description;
    private List<VehicleOption> options;
    private List<VehicleAnimation> animations;
    private List<Keyword> keywords;

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.vehicleType = vehicle.getVehicleType();
        this.basePrice = vehicle.getBasePrice();
        this.description = vehicle.getDescription();
    }

    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getVehicleType() { return vehicleType; }
    public Double getBasePrice() { return basePrice; }
    public String getDescription() { return description; }
    public List<VehicleOption> getOptions() { return options; }
    public void setOptions(List<VehicleOption> options) { this.options = options; }
    public List<VehicleAnimation> getAnimations() { return animations; }
    public void setAnimations(List<VehicleAnimation> animations) { this.animations = animations; }
    public List<Keyword> getKeywords() { return keywords; }
    public void setKeywords(List<Keyword> keywords) { this.keywords = keywords; }
}

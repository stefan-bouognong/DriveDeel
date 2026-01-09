package com.drivedreal.drivedreal.model;

import jakarta.persistence.*;

@Entity
public class VehicleAnimation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String animationType;
    private String animationUrl;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Long getId() { return id; }
    public String getAnimationType() { return animationType; }
    public String getAnimationUrl() { return animationUrl; }
}

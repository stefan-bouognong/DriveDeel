package com.drivedreal.drivedreal.services;

public abstract class VehicleServiceDecorator implements VehicleService {

    protected VehicleService vehicleService;

    public VehicleServiceDecorator(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
}

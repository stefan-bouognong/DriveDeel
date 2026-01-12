package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.service.VehicleService;

public abstract class VehicleServiceDecorator implements VehicleService {

    protected VehicleService vehicleService;

    public VehicleServiceDecorator(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
}

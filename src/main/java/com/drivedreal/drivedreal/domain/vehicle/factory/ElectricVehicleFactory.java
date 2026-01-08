package com.drivedreal.drivedreal.domain.vehicle.factory;

import org.springframework.stereotype.Component;

import com.drivedreal.drivedreal.domain.vehicle.*;
import com.drivedreal.drivedreal.domain.vehicle.ElectricAutomobile;
import com.drivedreal.drivedreal.domain.vehicle.ElectricScooter;

import java.math.BigDecimal;

@Component("electricVehicleFactory")
public class ElectricVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createAutomobile(String brand, String model, BigDecimal basePrice) {
        return new ElectricAutomobile(brand, model, basePrice);
    }

    @Override
    public Vehicle createScooter(String brand, String model, BigDecimal basePrice) {
        return new ElectricScooter(brand, model, basePrice);
    }
}
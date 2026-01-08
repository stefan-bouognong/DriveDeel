package com.drivedreal.drivedreal.domain.vehicle.factory;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import com.drivedreal.drivedreal.domain.vehicle.*;
import com.drivedreal.drivedreal.domain.vehicle.GasolineAutomobile;
import com.drivedreal.drivedreal.domain.vehicle.GasolineScooter;    

@Component("gasolineVehicleFactory")
public class GasolineVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createAutomobile(String brand, String model, BigDecimal basePrice) {
        return new GasolineAutomobile(brand, model, basePrice);
    }

    @Override
    public Vehicle createScooter(String brand, String model, BigDecimal basePrice) {
        return new GasolineScooter(brand, model, basePrice);
    }
}
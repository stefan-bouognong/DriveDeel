package com.drivedreal.drivedreal.domain.vehicle.factory;
import java.math.BigDecimal;

import com.drivedreal.drivedreal.domain.vehicle.Vehicle;

public interface VehicleFactory {
    Vehicle createAutomobile(String brand, String model, BigDecimal basePrice);
    Vehicle createScooter(String brand, String model, BigDecimal basePrice);
}
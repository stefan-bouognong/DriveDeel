package com.drivedreal.drivedreal.domain.vehicle;

import java.math.BigDecimal;

public interface Vehicle {

    String getDescription();
    BigDecimal getBasePrice();
    VehicleType getVehicleType();
    PropulsionType getPropulsionType();
    
}

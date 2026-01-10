package com.drivedreal.drivedreal.repository;

import com.drivedreal.drivedreal.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

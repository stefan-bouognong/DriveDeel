package com.drivedreal.drivedreal.repository;

import com.drivedreal.drivedreal.model.VehicleOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleOptionRepository extends JpaRepository<VehicleOption, Long> {
    List<VehicleOption> findByVehicleId(Long vehicleId);
}

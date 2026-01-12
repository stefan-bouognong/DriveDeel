package com.drivedreal.drivedreal.repository;

import com.drivedreal.drivedreal.model.VehicleAnimation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleAnimationRepository extends JpaRepository<VehicleAnimation, Long> {
    List<VehicleAnimation> findByVehicleId(Long vehicleId);
}

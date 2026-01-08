package com.drivedreal.drivedreal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drivedreal.drivedreal.entity.VehicleEntity;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity , Long> {
}
 
    


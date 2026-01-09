package com.drivedreal.drivedreal.repository;

import com.drivedreal.drivedreal.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByVehiclesId(Long vehicleId);
}

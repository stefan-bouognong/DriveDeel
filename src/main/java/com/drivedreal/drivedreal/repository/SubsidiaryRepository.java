package com.drivedreal.drivedreal.repository;

import com.drivedreal.drivedreal.entity.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Long> {
}

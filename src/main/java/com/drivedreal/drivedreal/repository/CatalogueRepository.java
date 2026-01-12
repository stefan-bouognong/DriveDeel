package com.drivedreal.drivedreal.repository;

import com.drivedreal.drivedreal.entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
}

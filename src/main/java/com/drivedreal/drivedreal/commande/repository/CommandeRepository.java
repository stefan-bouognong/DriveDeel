package com.drivedreal.drivedreal.commande.repository;

import com.drivedreal.drivedreal.commande.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}

package com.drivedreal.drivedreal.repository;

import com.drivedreal.drivedreal.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

package com.drivedreal.drivedreal.repository;

import com.drivedreal.drivedreal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

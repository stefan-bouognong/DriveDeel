// repository/OrderRepository.java
package com.drivedreal.drivedreal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivedreal.drivedreal.entity.Order;
import com.drivedreal.drivedreal.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ✅ commandes d’un utilisateur
    List<Order> findByUser(User user);
}

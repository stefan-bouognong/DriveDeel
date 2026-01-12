// services/OrderService.java
package com.drivedreal.drivedreal.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.drivedreal.drivedreal.dto.CreateOrderRequest;
import com.drivedreal.drivedreal.entity.Order;
import com.drivedreal.drivedreal.entity.OrderItem;
import com.drivedreal.drivedreal.entity.User;
import com.drivedreal.drivedreal.enums.OrderStatus;
import com.drivedreal.drivedreal.factories.FleetOrderFactory;
import com.drivedreal.drivedreal.factories.OrderFactory;
import com.drivedreal.drivedreal.factories.SimpleOrderFactory;
import com.drivedreal.drivedreal.model.Vehicle;
import com.drivedreal.drivedreal.repository.OrderRepository;
import com.drivedreal.drivedreal.repository.VehicleRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;

    public OrderService(OrderRepository orderRepository,
                        VehicleRepository vehicleRepository) {
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // ✅ CREATION AVEC USER
    public Order createOrder(CreateOrderRequest request) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        OrderFactory factory =
                request.getOrderType().name().equals("FLEET")
                        ? new FleetOrderFactory()
                        : new SimpleOrderFactory();

        Order order = factory.processOrder();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (CreateOrderRequest.Item item : request.getItems()) {
            Vehicle vehicle = vehicleRepository.findById(item.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setVehicleId(vehicle.getId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(vehicle.getBasePrice() * item.getQuantity());
            orderItem.setOrder(order);

            total += orderItem.getPrice();
            items.add(orderItem);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    // ✅ AVANCER LE STATUS (ADMIN ou propriétaire)
    public Order advanceStatus(Long orderId) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // 🔒 sécurité
        if (!order.getUser().getId().equals(user.getId())
                && !user.getRole().name().equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }

        switch (order.getStatus()) {
            case CREATED -> order.setStatus(OrderStatus.VALIDATED);
            case VALIDATED -> order.setStatus(OrderStatus.DELIVERED);
            case DELIVERED -> throw new RuntimeException("Order already delivered");
        }

        return orderRepository.save(order);
    }

    // ✅ COMMANDES DE L’UTILISATEUR CONNECTÉ
    public List<Order> getMyOrders() {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return orderRepository.findByUser(user);
    }
}

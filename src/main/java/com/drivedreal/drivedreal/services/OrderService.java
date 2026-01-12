    package com.drivedreal.drivedreal.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.drivedreal.drivedreal.dto.CreateOrderRequest;
import com.drivedreal.drivedreal.entity.Order;
import com.drivedreal.drivedreal.entity.OrderItem;
import com.drivedreal.drivedreal.entity.User;
import com.drivedreal.drivedreal.entity.VehicleEntity;
import com.drivedreal.drivedreal.enums.OrderStatus;
import com.drivedreal.drivedreal.factories.FleetOrderFactory;
import com.drivedreal.drivedreal.factories.OrderFactory;
import com.drivedreal.drivedreal.factories.SimpleOrderFactory;
import com.drivedreal.drivedreal.repository.OrderRepository;
import com.drivedreal.drivedreal.repository.UserRepository;
import com.drivedreal.drivedreal.repository.VehicleRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        VehicleRepository vehicleRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    // ✅ CREATION AVEC USER
    public Order createOrder(CreateOrderRequest request) {

        // Récupération de l'email du principal Spring Security
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Récupération de l'entité User en base
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + email));

        // Factory selon le type de commande
        OrderFactory factory = request.getOrderType().name().equals("FLEET")
                ? new FleetOrderFactory()
                : new SimpleOrderFactory();

        Order order = factory.processOrder();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CreateOrderRequest.Item item : request.getItems()) {
            VehicleEntity vehicle = vehicleRepository.findById(item.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));

            BigDecimal itemTotalPrice =
                    vehicle.getBasePrice().multiply(BigDecimal.valueOf(item.getQuantity()));

            OrderItem orderItem = new OrderItem();
            orderItem.setVehicleId(vehicle.getId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(itemTotalPrice.doubleValue());
            orderItem.setOrder(order);

            total = total.add(itemTotalPrice);
            items.add(orderItem);
        }

        order.setItems(items);
        order.setTotalAmount(total.doubleValue());

        return orderRepository.save(order);
    }

    // ✅ AVANCER LE STATUS (ADMIN ou propriétaire)
    public Order advanceStatus(Long orderId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + email));

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

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + email));

        return orderRepository.findByUser(user);
    }
}

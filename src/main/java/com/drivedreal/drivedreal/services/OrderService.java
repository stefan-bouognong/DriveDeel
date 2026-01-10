package com.drivedreal.drivedreal.services;

import com.drivedreal.drivedreal.dto.CreateOrderRequest;
import com.drivedreal.drivedreal.entity.*;
import com.drivedreal.drivedreal.enums.OrderStatus;
import com.drivedreal.drivedreal.factories.*;
import com.drivedreal.drivedreal.model.Vehicle;
import com.drivedreal.drivedreal.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;

    public OrderService(OrderRepository orderRepository, VehicleRepository vehicleRepository) {
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
    }
        public Order advanceStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Avancer le statut
        switch (order.getStatus()) {
            case CREATED -> order.setStatus(OrderStatus.VALIDATED);
            case VALIDATED -> order.setStatus(OrderStatus.DELIVERED);
            case DELIVERED -> throw new RuntimeException("Order already delivered");
            default -> throw new RuntimeException("Unknown status");
        }

        return orderRepository.save(order);
    }


    public Order createOrder(CreateOrderRequest request) {

        OrderFactory factory =
                request.getOrderType().name().equals("FLEET")
                        ? new FleetOrderFactory()
                        : new SimpleOrderFactory();

        Order order = factory.processOrder();

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
}

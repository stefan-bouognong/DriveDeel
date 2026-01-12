package com.drivedreal.drivedreal.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivedreal.drivedreal.dto.CreateOrderRequest;
import com.drivedreal.drivedreal.dto.OrderResponseDTO;
import com.drivedreal.drivedreal.entity.Order;
import com.drivedreal.drivedreal.repository.OrderRepository;
import com.drivedreal.drivedreal.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(request);

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.orderId = order.getId();
        dto.status = order.getStatus().name();
        dto.totalAmount = order.getTotalAmount();
        dto.orderDate = order.getOrderDate();

        return dto;
    }
    @PatchMapping("/{orderId}/advance")
    public OrderResponseDTO advanceOrderStatus(@PathVariable Long orderId) {
        Order order = orderService.advanceStatus(orderId);

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.orderId = order.getId();
        dto.status = order.getStatus().name();
        dto.totalAmount = order.getTotalAmount();
        dto.orderDate = order.getOrderDate();

        return dto;
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> {
            OrderResponseDTO dto = new OrderResponseDTO();
            dto.orderId = order.getId();
            dto.status = order.getStatus().name();
            dto.totalAmount = order.getTotalAmount();
            dto.orderDate = order.getOrderDate();
            return dto;
        }).toList();
    }

}

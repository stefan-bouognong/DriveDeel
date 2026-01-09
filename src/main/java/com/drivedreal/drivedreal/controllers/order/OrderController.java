package com.drivedreal.drivedreal.controllers.order;

import com.drivedreal.drivedreal.domain.paymentcomand.*;
import com.drivedreal.drivedreal.services.pricing.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final Map<Long, Order> orders = new HashMap<>();

    
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orders.get(id);
    }

    @GetMapping("/{id}/total")
    public double getOrderTotal(@PathVariable Long id) {
        Order order = orders.get(id);
        if (order == null) throw new RuntimeException("Order not found");

        OrderAmountCalculator calculator;

        if (order.getPayment().getType() == PaymentType.CASH) {
            calculator = new CashOrderAmountCalculator(order);
        } else {
            calculator = new CreditOrderAmountCalculator(order);
        }

        return calculator.calculateTotalAmount();
    }

    @GetMapping
    public Collection<Order> getAllOrders() {
        return orders.values();
    }
}

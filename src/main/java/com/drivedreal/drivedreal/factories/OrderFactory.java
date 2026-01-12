package com.drivedreal.drivedreal.factories;

import com.drivedreal.drivedreal.entity.Order;

public abstract class OrderFactory {

    public abstract Order createOrder();

    public Order processOrder() {
        Order order = createOrder();
        order.setStatus(com.drivedreal.drivedreal.enums.OrderStatus.CREATED);
        order.setOrderDate(java.time.LocalDate.now());
        return order;
    }
}

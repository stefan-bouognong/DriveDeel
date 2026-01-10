package com.drivedreal.drivedreal.factories;

import com.drivedreal.drivedreal.entity.Order;
import com.drivedreal.drivedreal.enums.OrderType;

public class SimpleOrderFactory extends OrderFactory {

    @Override
    public Order createOrder() {
        Order order = new Order();
        order.setType(OrderType.SIMPLE);
        return order;
    }
}

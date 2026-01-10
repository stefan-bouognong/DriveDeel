package com.drivedreal.drivedreal.dto;

import com.drivedreal.drivedreal.enums.OrderType;
import java.util.List;

public class CreateOrderRequest {

    private OrderType orderType;
    private List<Item> items;

    public static class Item {
        public Long vehicleId;
        public int quantity;
    }

    // getters & setters
}

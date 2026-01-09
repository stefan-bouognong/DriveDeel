package com.drivedreal.drivedreal.services.pricing;

import com.drivedreal.drivedreal.domain.paymentcomand.Order;

public class CreditOrderAmountCalculator extends OrderAmountCalculator {

    public CreditOrderAmountCalculator(Order order) {
        super(order);
    }

    @Override
    protected double calculatePaymentFees() {
        return order.getVehicle().getPrice() * 0.05;
    }
}


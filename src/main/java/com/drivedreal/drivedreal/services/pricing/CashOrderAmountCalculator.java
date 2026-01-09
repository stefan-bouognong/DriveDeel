package com.drivedreal.drivedreal.services.pricing;

import com.drivedreal.drivedreal.domain.paymentcomand.Order;

public class CashOrderAmountCalculator extends OrderAmountCalculator {

    public CashOrderAmountCalculator(Order order) {
        super(order);
    }

    @Override
    protected double calculatePaymentFees() {
        return 0;
    }
}

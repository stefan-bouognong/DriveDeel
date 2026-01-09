package com.drivedreal.drivedreal.services.pricing;

import com.drivedreal.drivedreal.domain.paymentcomand.Order;

public abstract class OrderAmountCalculator {

    protected Order order;

    public OrderAmountCalculator(Order order) {
        this.order = order;
    }

    public final double calculateTotalAmount() {

        double total = calculateVehiclePrice();
        total -= calculateDiscount();
        total += calculateTaxByCountry();
        total += calculatePaymentFees();

        return total;
    }

    protected double calculateVehiclePrice() {
        return order.getVehicle().getPrice();
    }

    protected double calculateTaxByCountry() {
        return order.getVehicle().getPrice()
             * order.getCountry().getTaxRate();
    }

    protected double calculateDiscount() {
        if (order.getVehicle().isOldStock()) {
            return order.getVehicle().getPrice() * 0.10;
        }
        return 0;
    }

    protected abstract double calculatePaymentFees();
}

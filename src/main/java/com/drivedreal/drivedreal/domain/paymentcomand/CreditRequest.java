package com.drivedreal.drivedreal.domain.paymentcomand;

public class CreditRequest {

    private Long id;
    private double amount;
    private boolean approved;

    public CreditRequest(Long id, double amount, boolean approved) {
        this.id = id;
        this.amount = amount;
        this.approved = approved;
    }


    public Long getId(){
        return id;
    }

    public double getAmount(){
        return amount;
    }

    public boolean isApproved() {
        return approved;
    }
}

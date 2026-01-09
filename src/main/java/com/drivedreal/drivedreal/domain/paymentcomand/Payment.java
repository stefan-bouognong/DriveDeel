package com.drivedreal.drivedreal.domain.paymentcomand;

public class Payment {

    private Long id;
    private PaymentType type;

    public Payment(Long id, PaymentType type) {
        this.id = id;
        this.type = type;
    }

    public Long getId(){
        return id;
    }
    public PaymentType getType() {
        return type;
    }
}

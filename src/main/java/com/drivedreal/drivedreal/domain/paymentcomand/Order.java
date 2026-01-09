package com.drivedreal.drivedreal.domain.paymentcomand;

public class Order {

    private Long id;
    private Vehicle vehicle;
    private Country country;
    private Payment payment;
    private CreditRequest creditRequest;

    public Order(Long id,
                Vehicle vehicle,
                Country country,
                Payment payment,
                CreditRequest creditRequest) {

        this.id = id;
        this.vehicle = vehicle;
        this.country = country;
        this.payment = payment;
        this.creditRequest = creditRequest;
    }
    public Long getId(){
        return id;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }

    public Country getCountry() {
        return country;
    }

    public Payment getPayment() {
        return payment;
    }

    public CreditRequest getCreditRequest() {
        return creditRequest;
    }
}

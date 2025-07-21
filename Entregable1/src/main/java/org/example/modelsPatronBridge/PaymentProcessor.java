package org.example.modelsPatronBridge;

public abstract class PaymentProcessor {

    protected PaymentGateway paymentGateway;

    public abstract void processPayment(double amount);
    public abstract void refundPayment(double amount);

    public PaymentProcessor(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
}

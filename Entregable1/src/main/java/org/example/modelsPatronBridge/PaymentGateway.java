package org.example.modelsPatronBridge;

public interface PaymentGateway {
    void authorize(double amount);
    void capture(double amount);
    }


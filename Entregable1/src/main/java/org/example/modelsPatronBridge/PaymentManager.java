package org.example.modelsPatronBridge;

import java.util.concurrent.CompletableFuture;

public class PaymentManager {
    public boolean processPayment(double amount, String provider) {
        PaymentGateway paymentGateway;
        PaymentProcessor paymentProcessor;

        switch (provider.toLowerCase()) {
            case "paypal":
                paymentProcessor = new PaypalPaymentProcessor(new PaypalGateway());
                break;
            case "mercadopago":
                paymentProcessor = new MercadoPagoPaymentProcessor(new MercadoPagoGateway());
                break;
            default:
                return false;
        }

        paymentProcessor.processPayment(amount);
        return true;
    }

    public boolean refundPayment(double amount, String provider) {
        PaymentGateway paymentGateway;
        PaymentProcessor paymentProcessor;

        switch (provider.toLowerCase()) {
            case "paypal":
                paymentProcessor = new PaypalPaymentProcessor(new PaypalGateway());
                break;
            case "mercadopago":
                paymentProcessor = new MercadoPagoPaymentProcessor(new MercadoPagoGateway());
                break;
            default:
                throw new IllegalArgumentException("Proveedor inválido");
        }

        paymentProcessor.refundPayment(amount);
        return true;
    }

    public CompletableFuture<Boolean> processPaymentAsync(double amount, String provider) {
        return CompletableFuture.supplyAsync(() -> processPayment(amount, provider));
    }

    public CompletableFuture<Boolean> refundPaymentAsync(double amount, String provider) {
        return CompletableFuture.supplyAsync(() -> refundPayment(amount, provider));
    }
}

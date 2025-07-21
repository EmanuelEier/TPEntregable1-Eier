package org.example.modelsPatronBridge;

public class PaymentManager {
    public void processPayment(double amount, String provider) {
        PaymentGateway paymentGateway;
        PaymentProcessor paymentProcessor;

        switch (provider.toLowerCase()) {
            case "paypal":
                paymentGateway = new PaypalGateway();
                paymentProcessor = new PaypalPaymentProcessor(paymentGateway);
                break;
            case "mercadopago":
                paymentGateway = new MercadoPagoGateway();
                paymentProcessor = new MercadoPagoPaymentProcessor(paymentGateway);
                break;
            default:
                throw new IllegalArgumentException("Proveedor inválido");
        }

        paymentProcessor.processPayment(amount);
    }

    public void refundPayment(double amount, String provider) {
        PaymentGateway paymentGateway;
        PaymentProcessor paymentProcessor;

        switch (provider.toLowerCase()) {
            case "paypal":
                paymentGateway = new PaypalGateway();
                paymentProcessor = new PaypalPaymentProcessor(paymentGateway);
                break;
            case "mercadopago":
                paymentGateway = new MercadoPagoGateway();
                paymentProcessor = new MercadoPagoPaymentProcessor(paymentGateway);
                break;
            default:
                throw new IllegalArgumentException("Proveedor inválido");
        }

        paymentProcessor.refundPayment(amount);
    }

}

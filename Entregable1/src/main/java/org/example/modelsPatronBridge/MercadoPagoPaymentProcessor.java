package org.example.modelsPatronBridge;

public class MercadoPagoPaymentProcessor extends PaymentProcessor{

    public MercadoPagoPaymentProcessor(PaymentGateway paymentGateway){ super(paymentGateway); }

    @Override
    public void processPayment(double amount) {
        paymentGateway.authorize(amount);
        paymentGateway.capture(amount);
        System.out.println("Se ha procesado el pago de $" + amount + "a través de Mercado Pago");
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Se ha reembolsado el pago de $" + amount + "a través de Mercado Pago");
    }
}

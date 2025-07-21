package org.example.modelsPatronBridge;

public class PaypalPaymentProcessor extends PaymentProcessor{

    public PaypalPaymentProcessor(PaymentGateway paymentGateway){ super(paymentGateway); }

    @Override
    public void processPayment(double amount) {
        paymentGateway.authorize(amount);
        paymentGateway.capture(amount);
        System.out.println("Se ha procesado el pago de $" + amount + "a través de Paypal");
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Se ha reeembolsado el pago de $" + amount + "a través de Paypal");
    }
}

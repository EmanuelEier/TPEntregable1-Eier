package org.example.modelsPatronBridge;

public class MercadoPagoGateway implements PaymentGateway{
    @Override
    public void authorize(double amount) {
        System.out.println("Pago autorizado con éxito");
    }

    @Override
    public void capture(double amount) {
        System.out.println("Pago capturado con éxito");
    }
}

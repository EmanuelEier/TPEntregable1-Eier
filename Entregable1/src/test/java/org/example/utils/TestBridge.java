package org.example.utils;
import jdk.jshell.spi.ExecutionControlProvider;
import org.example.modelsPatronBridge.PaymentManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class TestBridge {

    @Test
    void pagoConPaypal() throws Exception {
        PaymentManager paymentManager = new PaymentManager();
        CompletableFuture<Boolean> resultFuture = paymentManager.processPaymentAsync(2000, "Paypal");
        boolean result = resultFuture.get();
        Assertions.assertTrue(result);
    }

    @Test
    void reembolsoConPaypal() throws Exception {
        PaymentManager paymentManager = new PaymentManager();
        CompletableFuture<Boolean> resultFuture = paymentManager.refundPaymentAsync(2000, "Paypal");
        boolean result = resultFuture.get();
        Assertions.assertTrue(result);
    }

    @Test
    void pagoConMercadoPago() throws Exception {
        PaymentManager paymentManager = new PaymentManager();
        CompletableFuture<Boolean> resultFuture = paymentManager.processPaymentAsync(2000, "Mercadopago");
        boolean result = resultFuture.get();
        Assertions.assertTrue(result);
    }

    @Test
    void reembolsoConMercadoPago() throws Exception {
        PaymentManager paymentManager = new PaymentManager();
        CompletableFuture<Boolean> resultFuture = paymentManager.refundPaymentAsync(2000, "Mercadopago");
        boolean result = resultFuture.get();
        Assertions.assertTrue(result);
    }

    @Test
    void pagoConProveedorNoRegistrado() throws Exception {
        PaymentManager paymentManager = new PaymentManager();
        ExecutionException thrown = Assertions.assertThrows(ExecutionException.class, () -> {
            paymentManager.processPaymentAsync(2000, "BancoSantaFe").get();
        });

        Throwable cause = thrown.getCause();
        Assertions.assertTrue(cause instanceof IllegalArgumentException);
        Assertions.assertEquals("Proveedor inválido", cause.getMessage());
    }

    @Test
    void reembolsoConProveedorNoRegistrado() throws Exception {
        PaymentManager paymentManager = new PaymentManager();
        ExecutionException thrown = Assertions.assertThrows(ExecutionException.class, () -> {
            paymentManager.refundPaymentAsync(2000, "BancoSantaFe").get();
        });

        Throwable cause = thrown.getCause();
        Assertions.assertTrue(cause instanceof IllegalArgumentException);
        Assertions.assertEquals("Proveedor inválido", cause.getMessage());
    }
}

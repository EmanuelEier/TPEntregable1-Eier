package org.example.utils;
import org.example.ModelsPatronStrategy.AirShippingStrategy;
import org.example.ModelsPatronStrategy.TruckShippingStrategy;
import org.example.ModelsPatronStrategy.BoatShippingStrategy;
import org.example.ModelsPatronStrategy.ShippingCalculator;
import org.example.ModelsPatronStrategy.ShippingStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.CompletableFuture;

public class TestStrategy {
    @Test
    void calculateAirShipping() throws InterruptedException, ExecutionException {
        ShippingCalculator shippingCalculator = new ShippingCalculator(1500, 20, 20, 30, "United States", "Argentina", new AirShippingStrategy());
        CompletableFuture<Double> resultFuture = shippingCalculator.calculateCostAsync();
        double result = resultFuture.get();
        Assertions.assertEquals(result, 78000);
    }

    @Test
    void calculateTruckShipping() throws InterruptedException, ExecutionException {
        ShippingCalculator shippingCalculator = new ShippingCalculator(1500, 20, 20, 30, "Buenos Aires", "Rafaela", new TruckShippingStrategy());
        CompletableFuture<Double> resultFuture = shippingCalculator.calculateCostAsync();
        double result = resultFuture.get();
        Assertions.assertEquals(result, 19500);
    }

    @Test
    void calculateBoatShipping() throws InterruptedException, ExecutionException {
        ShippingCalculator shippingCalculator = new ShippingCalculator(1500, 20, 20, 30, "Portugal", "Buenos Aires", new BoatShippingStrategy());
        CompletableFuture<Double> resultFuture = shippingCalculator.calculateCostAsync();
        double result = resultFuture.get();
        Assertions.assertEquals(result, 39000);
    }
}

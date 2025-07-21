package org.example.ModelsPatronStrategy;

public class ShippingCalculator {
    ShippingStrategy shippingStrategy;
    private double weight, width, height, length;
    private String origin, destination;

    public ShippingCalculator(double weight, double width, double height, double length,
                              String origin, String destination, ShippingStrategy shippingStrategy) {
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.length = length;
        this.origin = origin;
        this.destination = destination;
        this.shippingStrategy = shippingStrategy;
    }

    public double calculateCost() {
        return shippingStrategy.calculateCost(weight, width, height, length, origin, destination);
    }
}

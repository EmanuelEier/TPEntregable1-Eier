package org.example.ModelsPatronStrategy;

public interface ShippingStrategy {
    double calculateCost(double weight, double width, double height, double length, String origin, String destination);
}

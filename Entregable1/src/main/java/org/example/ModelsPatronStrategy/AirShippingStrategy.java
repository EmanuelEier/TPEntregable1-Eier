package org.example.ModelsPatronStrategy;

public class AirShippingStrategy implements ShippingStrategy{
    @Override
    public double calculateCost(double weight, double widht, double height, double length, String origin, String destination) {
        return (widht * height * length + (weight * 5)) * 4; //las dimensiones serian en cm y el peso en gramos
    }
}

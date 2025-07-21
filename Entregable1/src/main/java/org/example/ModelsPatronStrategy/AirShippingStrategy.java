package org.example.ModelsPatronStrategy;

public class AirShippingStrategy implements ShippingStrategy{
    @Override
    public double CalculateCost(double weight, double widht, double height, double length, String origin, String destination) {
        double total = widht * height * length + (weight * 5); //las dimensiones serían en cm y el peso en gramos

        //ver como terminarlo, por la distancia entre origen y destino
        return total;
    }
}

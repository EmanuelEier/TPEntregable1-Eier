package org.example;
import org.example.ModelsPatronStrategy.ShippingStrategy;
import org.example.ModelsPatronStrategy.AirShippingStrategy;
import org.example.ModelsPatronStrategy.BoatShippingStrategy;
import org.example.ModelsPatronStrategy.TruckShippingStrategy;
import org.example.ModelsPatronStrategy.ShippingCalculator;
import org.example.modelsPatronBridge.PaymentManager;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1 -> operacionesPago(scanner);
                case 2 -> calculoEnvio(scanner);
                case 3 -> continuar = false;
            }
        }
        System.out.println("¡Hasta luego!");
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Operaciones de pago");
        System.out.println("2. Cálculo de envío");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void operacionesPago(Scanner scanner) {
        PaymentManager paymentManager = new PaymentManager();

        System.out.println("\n--- Operaciones de pago ---");
        System.out.print("Ingrese el monto: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ingrese el proveedor (Paypal o MercadoPago): ");
        String provider = scanner.nextLine();

        System.out.print("Ingrese que desea hacer (1: pagar, 2: reembolsar): ");
        int opcion = scanner.nextInt();

        CompletableFuture<Boolean> future;

        if (opcion == 1) {
            future = paymentManager.processPaymentAsync(amount, provider);
        } else if (opcion == 2) {
            future = paymentManager.refundPaymentAsync(amount, provider);
        } else {
            System.out.println("Opción inválida.");
            return;
        }

        try {
            Boolean result = future.get();
            System.out.println("Operación completada exitosamente: " + result);
        } catch (InterruptedException e) {
            System.out.println("La operación fue interrumpida.");
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error durante la operación: " + e.getCause().getMessage());
        }
    }

    private static void calculoEnvio(Scanner scanner) {
        System.out.println("\n--- Cálculo de Envío ---");
        System.out.print("Ingrese el peso en gramos: ");
        double weight = scanner.nextDouble();

        System.out.print("Ingrese el ancho (cm): ");
        double width = scanner.nextDouble();

        System.out.print("Ingrese el alto (cm): ");
        double height = scanner.nextDouble();

        System.out.print("Ingrese el largo (cm): ");
        double lenght = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ingrese el origen: ");
        String origin = scanner.nextLine();

        System.out.print("Ingrese el destino: ");
        String destination = scanner.nextLine();

        System.out.print("Ingrese el tipo de envío (air, truck, boat): ");
        String tipoEnvio = scanner.nextLine();

        ShippingStrategy shippingStrategy;
        switch (tipoEnvio.toLowerCase()) {
            case "air":
                shippingStrategy = new AirShippingStrategy();
                break;
            case "truck":
                shippingStrategy = new TruckShippingStrategy();
                break;
            case "boat":
                shippingStrategy = new BoatShippingStrategy();
                break;
            default:
                System.out.println("Tipo de envío inválido.");
                return;
        }

        ShippingCalculator shippingCalculator = new ShippingCalculator(weight, width, height, lenght, origin, destination, shippingStrategy);
        CompletableFuture<Double> envioFuture = shippingCalculator.calculateCostAsync();

        try {
            Double costo = envioFuture.get();
            System.out.println("Costo de envío: $" + costo);
        } catch (InterruptedException e) {
            System.out.println("La operación fue interrumpida.");
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error durante el cálculo del envío: " + e.getCause().getMessage());
        }
    }
}

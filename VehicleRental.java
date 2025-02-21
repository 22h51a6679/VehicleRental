import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehicle {
    private String type;
    private double pricePerDay;

    public Vehicle(String type, double pricePerDay) {
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    public String getType() {
        return type;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }
}

class Car extends Vehicle {
    public Car() {
        super("Car", 2000);
    }
}

class Bike extends Vehicle {
    public Bike() {
        super("Bike", 500);
    }
}

class Reservation {
    private Vehicle vehicle;
    private int days;

    public Reservation(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
    }

    public double calculateTotalPrice() {
        return vehicle.getPricePerDay() * days;
    }

    public String getDetails() {
        return String.format("Vehicle: %s\nDays: %d\nTotal Price: %.2f Rs", 
                              vehicle.getType(), days, calculateTotalPrice());
    }
}

public class VehicleRental {
    private static List<Vehicle> vehicles = new ArrayList<>();

    static {
        vehicles.add(new Car());
        vehicles.add(new Bike());
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { 
            System.out.println("Vehicle Rental System");
            System.out.println("---------------------");
            displayAvailableVehicles();

            Vehicle vehicle = null;
            while (vehicle == null) {
                try {
                    System.out.print("Enter the vehicle type (1 for Car, 2 for Bike): ");
                    int vehicleChoice = getValidIntegerInput(scanner, 1, 2);
                    vehicle = vehicles.get(vehicleChoice - 1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid choice! Please select a valid vehicle type.");
                }
            }

            System.out.print("Enter the number of days: ");
            int days = getValidIntegerInput(scanner, 1, Integer.MAX_VALUE);

            Reservation reservation = new Reservation(vehicle, days);
            System.out.println("\nReservation Details:");
            System.out.println(reservation.getDetails());
            
        } catch (Exception e) {  // Catching unexpected exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void displayAvailableVehicles() {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            System.out.printf("%d. %s - %.2f Rs/day\n", (i + 1), vehicle.getType(), vehicle.getPricePerDay());
        }
    }

    private static int getValidIntegerInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}

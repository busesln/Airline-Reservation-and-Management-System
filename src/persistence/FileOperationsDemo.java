package persistence;

import models.*;
import managers.*;
import java.time.LocalDateTime;
import java.util.List;

public class FileOperationsDemo {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        System.out.println("=== FILE OPERATIONS DEMO ===\n");

        System.out.println("STEP 1: Creating sample data...");
        FlightManager flightManager = new FlightManager();

        try {
            Route route1 = new Route("Istanbul", "Paris", 600.0);
            Route route2 = new Route("Ankara", "Berlin", 550.0);

            Plane plane1 = new Plane("TK001", "Boeing 737", 20, 6);
            Plane plane2 = new Plane("TK002", "Airbus A320", 25, 6);
            plane1.createSeats();
            plane2.createSeats();

            Flight f1 = new Flight("TK1234", route1, plane1, LocalDateTime.now().plusDays(1), 180);
            Flight f2 = new Flight("TK5678", route2, plane2, LocalDateTime.now().plusDays(2), 150);

            flightManager.addFlight(f1);
            flightManager.addFlight(f2);

            Passenger p1 = new Passenger("P001", "Ahmet", "Yilmaz", "ahmet@email.com");
            Passenger p2 = new Passenger("P002", "Ayse", "Kaya", "ayse@email.com");

            System.out.println("✅ Data created\n");

            System.out.println("STEP 2: Saving to files...");
            fileManager.saveFlights(flightManager.getAllFlights());

            java.util.List<Passenger> passengers = new java.util.ArrayList<>();
            passengers.add(p1);
            passengers.add(p2);
            fileManager.savePassengers(passengers);

            System.out.println("✅ Data saved\n");

            System.out.println("STEP 3: Loading from files...");
            List<Flight> loadedFlights = fileManager.loadFlights();
            List<Passenger> loadedPassengers = fileManager.loadPassengers();

            System.out.println("✅ Data loaded\n");

            System.out.println("STEP 4: Verifying loaded data...");
            System.out.println("Flights:");
            for (Flight f : loadedFlights) {
                System.out.println("  - " + f.getFlightNum() + ": " + f.getRoute());
            }

            System.out.println("Passengers:");
            for (Passenger p : loadedPassengers) {
                System.out.println("  - " + p.getFullName() + " (" + p.getPassengerID() + ")");
            }

            System.out.println("\n✅ Verification complete!");

            System.out.println("\nSTEP 5: Data persistence test...");
            System.out.println("Files exist: " + fileManager.dataExists());

            System.out.println("\n=== FILE OPERATIONS SUCCESSFUL ===");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package threading;

import models.*;
import managers.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MultithreadingDemo {

    public static void main(String[] args) {
        System.out.println("=== SCENARIO 1: Simultaneous Seat Reservation ===\n");

        Route route = new Route("Istanbul", "London", 500.0);
        Plane plane = new Plane("TK001", "Boeing 737", 30, 6);
        plane.createSeats();
        Flight flight = new Flight("TK1234", route, plane, LocalDateTime.now().plusDays(1), 240);

        ReservationManager reservationManager = new ReservationManager();

        System.out.println("Initial empty seats: 180 (30 rows × 6 cols)");
        System.out.println("Passengers (threads): 90\n");

        testWithSynchronization(reservationManager, flight);

        resetSeats(flight);

        testWithoutSynchronization(flight);

        System.out.println("\n=== SCENARIO 2: Asynchronous Report Generation ===\n");
        testAsyncReportGeneration();
    }

    private static void testWithSynchronization(ReservationManager manager, Flight flight) {
        System.out.println("--- WITH SYNCHRONIZATION (ReentrantLock) ---");

        List<PassengerThread> threads = new ArrayList<>();

        for (int i = 0; i < 90; i++) {
            Passenger passenger = new Passenger("P" + i, "Passenger", "" + i, "email" + i + "@test.com");
            PassengerThread thread = new PassengerThread(manager, flight, passenger, "RES" + i, true);
            threads.add(thread);
            thread.start();
        }

        for (PassengerThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int successCount = (int) threads.stream().filter(PassengerThread::isSuccess).count();
        int occupiedSeats = countOccupiedSeats(flight);

        System.out.println("Result:");
        System.out.println("  Successful reservations: " + successCount);
        System.out.println("  Occupied seats: " + occupiedSeats);
        System.out.println("  Empty seats: " + (180 - occupiedSeats));
        System.out.println("  Status: " + (occupiedSeats == 90 ? "✅ CORRECT" : "❌ RACE CONDITION"));
        System.out.println();
    }

    private static void testWithoutSynchronization(Flight flight) {
        System.out.println("--- WITHOUT SYNCHRONIZATION (Race Condition) ---");

        List<PassengerThread> threads = new ArrayList<>();

        for (int i = 0; i < 90; i++) {
            Passenger passenger = new Passenger("P" + i, "Passenger", "" + i, "email" + i + "@test.com");
            PassengerThread thread = new PassengerThread(null, flight, passenger, "RES" + i, false);
            threads.add(thread);
            thread.start();
        }

        for (PassengerThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int occupiedSeats = countOccupiedSeats(flight);

        System.out.println("Result:");
        System.out.println("  Occupied seats: " + occupiedSeats);
        System.out.println("  Empty seats: " + (180 - occupiedSeats));
        System.out.println("  Status: " + (occupiedSeats != 90 ? "⚠️ RACE CONDITION DETECTED" : "Unexpected"));
        System.out.println();
    }

    private static void testAsyncReportGeneration() {
        FlightManager manager = new FlightManager();

        try {
            Route r1 = new Route("Istanbul", "Paris", 600.0);
            Route r2 = new Route("Ankara", "Berlin", 550.0);
            Plane p1 = new Plane("TK001", "Boeing 737", 20, 6);
            Plane p2 = new Plane("TK002", "Airbus A320", 25, 6);
            p1.createSeats();
            p2.createSeats();

            Flight f1 = new Flight("TK1234", r1, p1, LocalDateTime.now().plusDays(1), 180);
            Flight f2 = new Flight("TK5678", r2, p2, LocalDateTime.now().plusDays(2), 150);

            manager.addFlight(f1);
            manager.addFlight(f2);

            System.out.println("Starting async report generation...");
            System.out.println("GUI remains responsive during generation!\n");

            ReportGenerator generator = new ReportGenerator(manager);
            generator.execute();

            String result = generator.get();
            System.out.println("\nFinal Report:\n" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int countOccupiedSeats(Flight flight) {
        int count = 0;
        Seat[][] seats = flight.getPlane().getSeats();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] != null && seats[i][j].isReserved()) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void resetSeats(Flight flight) {
        Seat[][] seats = flight.getPlane().getSeats();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] != null) {
                    seats[i][j].cancelReservation();
                }
            }
        }
    }
}

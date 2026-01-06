package persistence;

import models.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FileManager {

    private static final String DATA_DIR = "data/";
    private static final String FLIGHTS_FILE = DATA_DIR + "flights.ser";
    private static final String PASSENGERS_FILE = DATA_DIR + "passengers.ser";
    private static final String RESERVATIONS_FILE = DATA_DIR + "reservations.ser";

    public FileManager() {
        createDataDirectory();
    }

    private void createDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void saveFlights(List<Flight> flights) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FLIGHTS_FILE))) {
            oos.writeObject(flights);
            System.out.println("Flights saved: " + flights.size() + " records");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Flight> loadFlights() throws IOException, ClassNotFoundException {
        File file = new File(FLIGHTS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FLIGHTS_FILE))) {
            List<Flight> flights = (List<Flight>) ois.readObject();
            System.out.println("Flights loaded: " + flights.size() + " records");
            return flights;
        }
    }

    public void savePassengers(List<Passenger> passengers) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(PASSENGERS_FILE))) {
            oos.writeObject(passengers);
            System.out.println("Passengers saved: " + passengers.size() + " records");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Passenger> loadPassengers() throws IOException, ClassNotFoundException {
        File file = new File(PASSENGERS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(PASSENGERS_FILE))) {
            List<Passenger> passengers = (List<Passenger>) ois.readObject();
            System.out.println("Passengers loaded: " + passengers.size() + " records");
            return passengers;
        }
    }

    public void saveReservations(List<Reservation> reservations) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(RESERVATIONS_FILE))) {
            oos.writeObject(reservations);
            System.out.println("Reservations saved: " + reservations.size() + " records");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> loadReservations() throws IOException, ClassNotFoundException {
        File file = new File(RESERVATIONS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(RESERVATIONS_FILE))) {
            List<Reservation> reservations = (List<Reservation>) ois.readObject();
            System.out.println("Reservations loaded: " + reservations.size() + " records");
            return reservations;
        }
    }

    public void deleteAllData() {
        new File(FLIGHTS_FILE).delete();
        new File(PASSENGERS_FILE).delete();
        new File(RESERVATIONS_FILE).delete();
        System.out.println("All data files deleted");
    }

    public boolean dataExists() {
        return new File(FLIGHTS_FILE).exists() ||
                new File(PASSENGERS_FILE).exists() ||
                new File(RESERVATIONS_FILE).exists();
    }
}

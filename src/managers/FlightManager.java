package managers;

import models.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class FlightManager {
    private List<Flight> flights;

    public FlightManager() {
        this.flights = new ArrayList<>();
    }

    // Uçuş ekleme
    public void addFlight(Flight flight) throws DuplicateFlightException {
        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null");
        }
        if (getFlightByNumber(flight.getFlightNum()) != null) {
            throw new DuplicateFlightException("Flight with number " + flight.getFlightNum() + " already exists");
        }

        flights.add(flight);
        System.out.println("Flight added: " + flight.getFlightNum());
    }

    // Uçuş silme
    public boolean removeFlight(String flightNum) throws FlightNotFoundException {
        if (flightNum == null || flightNum.trim().isEmpty()) {
            throw new IllegalArgumentException("Flight number cannot be null or empty");
        }

        boolean removed = flights.removeIf(f -> f.getFlightNum().equals(flightNum));

        if (!removed) {
            throw new FlightNotFoundException("Flight " + flightNum + " not found");
        }

        System.out.println("Flight removed: " + flightNum);
        return true;
    }

    // Tüm uçuşları listeleme
    public List<Flight> getAllFlights() {
        return new ArrayList<>(flights);
    }

    // Belirli bir uçuşu bulma
    public Flight getFlightByNumber(String flightNum) {
        return flights.stream()
                .filter(f -> f.getFlightNum().equals(flightNum))
                .findFirst()
                .orElse(null);
    }

    // Kalkış yerine göre arama
    public List<Flight> searchByDeparture(String departurePlace) {
        return flights.stream()
                .filter(f -> f.getRoute().getDeparturePlace().equalsIgnoreCase(departurePlace))
                .collect(Collectors.toList());
    }

    // Varış yerine göre arama
    public List<Flight> searchByArrival(String arrivalPlace) {
        return flights.stream()
                .filter(f -> f.getRoute().getArrivalPlace().equalsIgnoreCase(arrivalPlace))
                .collect(Collectors.toList());
    }

    // Kalkış ve varış yerine göre arama
    public List<Flight> searchByRoute(String departurePlace, String arrivalPlace) {
        return flights.stream()
                .filter(f -> f.getRoute().getDeparturePlace().equalsIgnoreCase(departurePlace) &&
                        f.getRoute().getArrivalPlace().equalsIgnoreCase(arrivalPlace))
                .collect(Collectors.toList());
    }

    // Tarihi geçmiş uçuşları silme (JUnit test için gerekli)
    public void removeExpiredFlights() {
        LocalDateTime now = LocalDateTime.now();
        flights.removeIf(f -> f.getDepartureTime().isBefore(now));
    }

    // Toplam uçuş sayısı
    public int getFlightCount() {
        return flights.size();
    }
}

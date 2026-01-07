package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import managers.FlightManager;
import models.*;
import java.time.LocalDateTime;
import java.util.List;

class FlightSearchTest {

    private FlightManager manager;

    @BeforeEach
    void setUp() throws Exception {
        manager = new FlightManager();

        Route route1 = new Route("Istanbul", "London", 500.0);
        Route route2 = new Route("Ankara", "Paris", 600.0);
        Route route3 = new Route("Istanbul", "Berlin", 450.0);

        Plane plane1 = new Plane("TK001", "Boeing 737", 5, 6);
        Plane plane2 = new Plane("TK002", "Airbus A320", 4, 6);
        Plane plane3 = new Plane("TK003", "Boeing 777", 6, 6);

        Flight f1 = new Flight("TK1234", route1, plane1, LocalDateTime.now().plusDays(1), 240);
        Flight f2 = new Flight("TK5678", route2, plane2, LocalDateTime.now().plusDays(2), 180);
        Flight f3 = new Flight("TK9999", route3, plane3, LocalDateTime.now().plusDays(3), 200);

        manager.addFlight(f1);
        manager.addFlight(f2);
        manager.addFlight(f3);
    }

    @Test
    void testSearchByDeparture() {
        List<Flight> results = manager.searchByDeparture("Istanbul");
        assertEquals(2, results.size());
    }

    @Test
    void testSearchByArrival() {
        List<Flight> results = manager.searchByArrival("Paris");
        assertEquals(1, results.size());
        assertEquals("TK5678", results.get(0).getFlightNum());
    }

    @Test
    void testSearchByRoute() {
        List<Flight> results = manager.searchByRoute("Istanbul", "London");
        assertEquals(1, results.size());
        assertEquals("TK1234", results.get(0).getFlightNum());
    }

    @Test
    void testSearchNonExistent() {
        List<Flight> results = manager.searchByDeparture("Tokyo");
        assertEquals(0, results.size());
    }

    @Test
    void testEliminateExpiredFlights() throws Exception {
        Route route = new Route("Izmir", "Rome", 400.0);
        Plane plane = new Plane("TK004", "Airbus A330", 8, 6);
        Flight expired = new Flight("TK0001", route, plane, LocalDateTime.now().minusDays(1), 150);

        manager.addFlight(expired);
        assertEquals(4, manager.getFlightCount());

        manager.removeExpiredFlights();
        assertEquals(3, manager.getFlightCount());
    }
}

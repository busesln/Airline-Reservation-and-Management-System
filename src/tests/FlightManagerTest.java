package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import managers.FlightManager;
import models.*;
import java.time.LocalDateTime;

class FlightManagerTest {

    private FlightManager manager;
    private Flight flight1;

    @BeforeEach
    void setUp() throws Exception {
        manager = new FlightManager();
        Route route = new Route("Istanbul", "London", 500.0);
        Plane plane = new Plane("TK001", "Boeing 737", 5, 6);
        flight1 = new Flight("TK1234", route, plane, LocalDateTime.now().plusDays(1), 240);
    }

    @Test
    void testAddFlight() throws Exception {
        manager.addFlight(flight1);
        assertEquals(1, manager.getFlightCount());
    }

    @Test
    void testAddNullFlight() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.addFlight(null);
        });
        assertTrue(exception.getMessage().contains("cannot be null"));
    }

    @Test
    void testAddDuplicateFlight() throws Exception {
        manager.addFlight(flight1);

        Exception exception = assertThrows(Exception.class, () -> {
            manager.addFlight(flight1);
        });
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void testRemoveFlight() throws Exception {
        manager.addFlight(flight1);
        boolean removed = manager.removeFlight("TK1234");
        assertTrue(removed);
        assertEquals(0, manager.getFlightCount());
    }

    @Test
    void testRemoveNonExistentFlight() {
        assertThrows(Exception.class, () -> {
            manager.removeFlight("INVALID");
        });
    }

    @Test
    void testSearchByDeparture() throws Exception {
        manager.addFlight(flight1);
        var results = manager.searchByDeparture("Istanbul");
        assertEquals(1, results.size());
    }

    @Test
    void testRemoveExpiredFlights() throws Exception {
        Route route = new Route("Ankara", "Paris", 600.0);
        Plane plane = new Plane("TK002", "Airbus A320", 4, 6);
        Flight expiredFlight = new Flight("TK9999", route, plane, LocalDateTime.now().minusDays(1), 180);

        manager.addFlight(flight1);
        manager.addFlight(expiredFlight);
        assertEquals(2, manager.getFlightCount());

        manager.removeExpiredFlights();
        assertEquals(1, manager.getFlightCount());
    }
}

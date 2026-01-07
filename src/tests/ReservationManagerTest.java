package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import managers.ReservationManager;
import models.*;
import java.time.LocalDateTime;

class ReservationManagerTest {

    private ReservationManager reservationManager;
    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        reservationManager = new ReservationManager();

        Route route = new Route("Istanbul", "London", 500.0);
        Plane plane = new Plane("TK001", "Boeing 737", 5, 6);
        plane.createSeats();
        flight = new Flight("TK1234", route, plane, LocalDateTime.now().plusDays(1), 240);

        passenger = new Passenger("P001", "Ahmet", "Yilmaz", "ahmet@email.com");
    }

    @Test
    void testMakeReservation() throws Exception {
        Reservation res = reservationManager.makeReservation("RES001", flight, passenger, 0, 0);
        assertNotNull(res);
        assertEquals("RES001", res.getReservationCode());
    }

    @Test
    void testMakeReservationAlreadyReserved() throws Exception {
        reservationManager.makeReservation("RES001", flight, passenger, 1, 1);

        Passenger passenger2 = new Passenger("P002", "Ayse", "Kaya", "ayse@email.com");
        Exception exception = assertThrows(Exception.class, () -> {
            reservationManager.makeReservation("RES002", flight, passenger2, 1, 1);
        });
        assertTrue(exception.getMessage().contains("already reserved"));
    }

    @Test
    void testCancelReservation() throws Exception {
        reservationManager.makeReservation("RES001", flight, passenger, 2, 2);
        boolean cancelled = reservationManager.cancelReservation("RES001");
        assertTrue(cancelled);
    }

    @Test
    void testCancelNonExistentReservation() {
        boolean cancelled = reservationManager.cancelReservation("INVALID");
        assertFalse(cancelled);
    }

    @Test
    void testFindReservation() throws Exception {
        reservationManager.makeReservation("RES001", flight, passenger, 3, 3);
        Reservation found = reservationManager.findReservation("RES001");
        assertNotNull(found);
        assertEquals("RES001", found.getReservationCode());
    }

    @Test
    void testGetReservationsByPassenger() throws Exception {
        reservationManager.makeReservation("RES001", flight, passenger, 0, 1);
        reservationManager.makeReservation("RES002", flight, passenger, 0, 2);

        var reservations = reservationManager.getReservationsByPassenger("P001");
        assertEquals(2, reservations.size());
    }
}

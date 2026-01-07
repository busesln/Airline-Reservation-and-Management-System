package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import managers.SeatManager;
import models.*;
import java.time.LocalDateTime;

class SeatManagerTest {

    private SeatManager seatManager;
    private Flight flight;

    @BeforeEach
    void setUp() {
        seatManager = new SeatManager();
        Route route = new Route("Istanbul", "London", 500.0);
        Plane plane = new Plane("TK001", "Boeing 737", 5, 6);
        plane.createSeats();
        flight = new Flight("TK1234", route, plane, LocalDateTime.now().plusDays(1), 240);
    }

    @Test
    void testInitialEmptySeatsCount() {
        int count = seatManager.getEmptySeatsCount(flight);
        assertEquals(30, count);
    }

    @Test
    void testEmptySeatsAfterReservation() throws Exception {
        seatManager.reserveSeat(flight, 0, 0);
        int count = seatManager.getEmptySeatsCount(flight);
        assertEquals(29, count);
    }

    @Test
    void testReserveSeatSuccess() throws Exception {
        boolean success = seatManager.reserveSeat(flight, 2, 3);
        assertTrue(success);
    }

    @Test
    void testReserveSeatAlreadyReserved() throws Exception {
        seatManager.reserveSeat(flight, 1, 1);
        boolean success = seatManager.reserveSeat(flight, 1, 1);
        assertFalse(success);
    }

    @Test
    void testReserveNonExistentSeat() {
        Exception exception = assertThrows(Exception.class, () -> {
            seatManager.reserveSeat(flight, 99, 99);
        });
        assertTrue(exception.getMessage().contains("does not exist"));
    }

    @Test
    void testCancelSeatReservation() throws Exception {
        Seat seat = flight.getPlane().getSeat(0, 0);
        seat.reserve();
        assertTrue(seat.isReserved());

        seatManager.cancelSeatReservation(seat);
        assertFalse(seat.isReserved());
    }

    @Test
    void testSeatMatrix() {
        String[][] matrix = seatManager.getSeatMatrix(flight);
        assertEquals(5, matrix.length);
        assertEquals(6, matrix[0].length);
        assertEquals("O", matrix[0][0]);
    }
}

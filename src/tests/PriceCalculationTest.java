package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import managers.CalculatePrice;
import models.*;

class PriceCalculationTest {

    private CalculatePrice calculator;
    private Route route;

    @BeforeEach
    void setUp() {
        calculator = new CalculatePrice();
        route = new Route("Istanbul", "Paris", 500.0);
    }

    @Test
    void testEconomyPrice() {
        double price = calculator.calculateTicketPrice(route, SeatType.ECONOMY);
        assertEquals(500.0, price, 0.01);
    }

    @Test
    void testBusinessPrice() {
        double price = calculator.calculateTicketPrice(route, SeatType.BUSINESS);
        assertEquals(1250.0, price, 0.01);
    }

    @Test
    void testBaggageFree() {
        Baggage baggage = new Baggage(15.0);
        double price = calculator.calculateBaggagePrice(baggage);
        assertEquals(0.0, price, 0.01);
    }

    @Test
    void testBaggageExtraCharge() {
        Baggage baggage = new Baggage(30.0);
        double price = calculator.calculateBaggagePrice(baggage);
        assertEquals(500.0, price, 0.01);
    }

    @Test
    void testTotalPriceWithBaggage() {
        Seat seat = new Seat("1A", 0, SeatType.BUSINESS);
        Baggage baggage = new Baggage(25.0);
        double total = calculator.calculateTotalPrice(route, seat, baggage);
        assertEquals(1500.0, total, 0.01);
    }
}

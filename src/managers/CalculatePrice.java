package managers;

import models.*;
import utils.Constants;

public class CalculatePrice {

    // Koltuk tipine göre fiyat hesaplama
    public double calculateTicketPrice(Route route, SeatType seatType) {
        double basePrice = route.getBasePrice();

        switch (seatType) {
            case ECONOMY:
                return basePrice * Constants.ECONOMY_MULTIPLIER;
            case BUSINESS:
                return basePrice * Constants.BUSINESS_MULTIPLIER;
            default:
                return basePrice;
        }
    }

    // Seat nesnesi ile fiyat hesaplama
    public double calculateTicketPrice(Route route, Seat seat) {
        return calculateTicketPrice(route, seat.getType());
    }

    // Reservation için toplam fiyat
    public double calculateReservationPrice(Reservation reservation) {
        Route route = reservation.getFlight().getRoute();
        Seat seat = reservation.getSeat();
        return calculateTicketPrice(route, seat);
    }

    // Bagaj ücreti hesaplama
    public double calculateBaggagePrice(Baggage baggage) {
        double weight = baggage.getWeight();

        if (weight <= Constants.MAX_FREE_BAGGAGE_KG) {
            return 0;
        } else {
            return (weight - Constants.MAX_FREE_BAGGAGE_KG) * Constants.BAGGAGE_PRICE_PER_KG;
        }
    }

    // Toplam bilet fiyatı (koltuk + bagaj)
    public double calculateTotalPrice(Route route, Seat seat, Baggage baggage) {
        double seatPrice = calculateTicketPrice(route, seat);
        double baggagePrice = calculateBaggagePrice(baggage);
        return seatPrice + baggagePrice;
    }
}

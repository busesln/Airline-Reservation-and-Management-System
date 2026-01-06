package managers;

import models.*;

public class CalculatePrice {

    // Fiyat hesaplama katsayıları
    private static final double ECONOMY_MULTIPLIER = 1.0;
    private static final double BUSINESS_MULTIPLIER = 2.5;

    // Koltuk tipine göre fiyat hesaplama
    public double calculateTicketPrice(Route route, SeatType seatType) {
        double basePrice = route.getBasePrice();

        switch (seatType) {
            case ECONOMY:
                return basePrice * ECONOMY_MULTIPLIER;
            case BUSINESS:
                return basePrice * BUSINESS_MULTIPLIER;
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

    // Bagaj ücreti hesaplama (opsiyonel)
    public double calculateBaggagePrice(Baggage baggage) {
        double weight = baggage.getWeight();

        // 20 kg'a kadar ücretsiz, sonrası kg başına 50 TL
        if (weight <= 20) {
            return 0;
        } else {
            return (weight - 20) * 50;
        }
    }

    // Toplam bilet fiyatı (koltuk + bagaj)
    public double calculateTotalPrice(Route route, Seat seat, Baggage baggage) {
        double seatPrice = calculateTicketPrice(route, seat);
        double baggagePrice = calculateBaggagePrice(baggage);
        return seatPrice + baggagePrice;
    }
}

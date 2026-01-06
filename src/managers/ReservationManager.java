package managers;

import models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationManager {
    private List<Reservation> reservations;
    private SeatManager seatManager;

    // Concurrency için lock (Multithreading Scenario 1 için)
    private ReentrantLock lock;

    public ReservationManager() {
        this.reservations = new ArrayList<>();
        this.seatManager = new SeatManager();
        this.lock = new ReentrantLock();
    }

    // Thread-safe rezervasyon oluşturma
    public Reservation makeReservation(String reservationCode, Flight flight, Passenger passenger,
            int seatRow, int seatCol) throws Exception {
        lock.lock();
        try {
            // Koltuğu rezerve et
            boolean success = seatManager.reserveSeat(flight, seatRow, seatCol);

            if (!success) {
                throw new Exception("Seat is already reserved!");
            }

            Seat seat = flight.getPlane().getSeat(seatRow, seatCol);
            Reservation reservation = new Reservation(reservationCode, flight, passenger, seat);
            reservations.add(reservation);

            System.out.println("Reservation successful: " + reservationCode);
            return reservation;

        } finally {
            lock.unlock();
        }
    }

    // Rezervasyon iptali
    public boolean cancelReservation(String reservationCode) {
        lock.lock();
        try {
            Reservation reservation = findReservation(reservationCode);

            if (reservation == null) {
                return false;
            }

            // Koltuğu serbest bırak
            seatManager.cancelSeatReservation(reservation.getSeat());
            reservations.remove(reservation);

            System.out.println("Reservation cancelled: " + reservationCode);
            return true;

        } finally {
            lock.unlock();
        }
    }

    // Rezervasyon bulma
    public Reservation findReservation(String reservationCode) {
        return reservations.stream()
                .filter(r -> r.getReservationCode().equals(reservationCode))
                .findFirst()
                .orElse(null);
    }

    // Tüm rezervasyonları listeleme
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    // Yolcuya göre rezervasyon bulma
    public List<Reservation> getReservationsByPassenger(String passengerID) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getPassenger().getPassengerID().equals(passengerID)) {
                result.add(r);
            }
        }
        return result;
    }

    // Uçuşa göre rezervasyon sayısı
    public int getReservationCountByFlight(String flightNum) {
        return (int) reservations.stream()
                .filter(r -> r.getFlight().getFlightNum().equals(flightNum))
                .count();
    }
}

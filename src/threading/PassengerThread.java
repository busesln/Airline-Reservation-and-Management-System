package threading;

import models.*;
import managers.*;
import java.util.Random;

public class PassengerThread extends Thread {
    private final ReservationManager reservationManager;
    private final Flight flight;
    private final Passenger passenger;
    private final String reservationCode;
    private final boolean useSynchronization;
    private boolean success = false;
    private String errorMessage = "";

    public PassengerThread(ReservationManager manager, Flight flight, Passenger passenger,
            String reservationCode, boolean useSynchronization) {
        this.reservationManager = manager;
        this.flight = flight;
        this.passenger = passenger;
        this.reservationCode = reservationCode;
        this.useSynchronization = useSynchronization;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            Seat[][] seats = flight.getPlane().getSeats();
            int rows = seats.length;
            int cols = seats[0].length;

            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (useSynchronization) {
                reservationManager.makeReservation(reservationCode, flight, passenger, row, col);
                success = true;
            } else {
                Seat seat = flight.getPlane().getSeat(row, col);
                if (seat != null && !seat.isReserved()) {
                    Thread.sleep(10);
                    seat.reserve();
                    success = true;
                }
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            success = false;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

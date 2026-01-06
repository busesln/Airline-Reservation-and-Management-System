package exceptions;

public class SeatAlreadyReservedException extends Exception {
    private static final long serialVersionUID = 1L;

    private final String seatNumber;

    public SeatAlreadyReservedException(String seatNumber) {
        super("Seat already reserved: " + seatNumber);
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}

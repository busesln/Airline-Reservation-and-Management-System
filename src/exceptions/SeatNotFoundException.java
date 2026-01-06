package exceptions;

public class SeatNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public SeatNotFoundException(int row, int col) {
        super("Seat not found at position [" + row + "][" + col + "]");
    }
}

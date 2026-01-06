package exceptions;

public class DuplicateFlightException extends Exception {
    private static final long serialVersionUID = 1L;

    public DuplicateFlightException(String flightNum) {
        super("Flight already exists: " + flightNum);
    }
}

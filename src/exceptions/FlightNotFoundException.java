package exceptions;

public class FlightNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public FlightNotFoundException(String message) {
        super(message);
    }

    public FlightNotFoundException(String flightNum, String details) {
        super("Flight " + flightNum + " not found. " + details);
    }
}
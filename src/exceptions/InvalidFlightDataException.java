package exceptions;

public class InvalidFlightDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidFlightDataException(String message) {
        super("Invalid flight data: " + message);
    }

    public InvalidFlightDataException(String field, String value) {
        super("Invalid flight data - " + field + ": " + value);
    }
}

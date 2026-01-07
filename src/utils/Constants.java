package utils;

public class Constants {

    // Pricing Constants
    public static final double ECONOMY_MULTIPLIER = 1.0;
    public static final double BUSINESS_MULTIPLIER = 2.5;
    public static final double MAX_FREE_BAGGAGE_KG = 20.0;
    public static final double BAGGAGE_PRICE_PER_KG = 50.0;

    // File Paths
    public static final String DATA_DIR = "data/";
    public static final String FLIGHTS_FILE = DATA_DIR + "flights.ser";
    public static final String PASSENGERS_FILE = DATA_DIR + "passengers.ser";
    public static final String RESERVATIONS_FILE = DATA_DIR + "reservations.ser";

    // Default Values
    public static final double DEFAULT_SEAT_PRICE = 100.0;
    public static final int DEFAULT_PLANE_ROWS = 30;
    public static final int DEFAULT_PLANE_COLS = 6;

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Constants class cannot be instantiated");
    }
}

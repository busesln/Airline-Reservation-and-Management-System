package models;

/**
 * Customer user for booking flights and managing reservations.
 * Has limited privileges compared to Admin.
 * 
 * @author AirlineReservationSystem
 * @version 1.0
 */
public class Customer extends User {
    private static final long serialVersionUID = 1L;

    private String customerId;
    private String email;

    /**
     * Creates a Customer user.
     * 
     * @param username Customer username
     * @param password Customer password
     */
    public Customer(String username, String password) {
        super(username, password, "CUSTOMER");
        this.customerId = "C" + System.currentTimeMillis(); // Auto-generate ID
    }

    /**
     * Creates a Customer with email.
     * 
     * @param username Customer username
     * @param password Customer password
     * @param email    Customer email
     */
    public Customer(String username, String password, String email) {
        this(username, password);
        this.email = email;
    }

    /**
     * Customer login verification.
     * 
     * @param inputUsername Username to verify
     * @param inputPassword Password to verify
     * @return true if credentials match
     */
    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) &&
                this.password.equals(inputPassword);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

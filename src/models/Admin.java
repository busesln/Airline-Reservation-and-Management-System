package models;

/**
 * Admin user with special privileges.
 * Can manage flights, view all reservations, and access administrative
 * functions.
 * 
 * @author AirlineReservationSystem
 * @version 1.0
 */
public class Admin extends User {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an Admin user.
     * 
     * @param username Admin username
     * @param password Admin password
     */
    public Admin(String username, String password) {
        super(username, password, "ADMIN");
    }

    /**
     * Admin login verification.
     * Simple credential matching (can be enhanced with database lookup).
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

    /**
     * Checks if user has admin privileges.
     * 
     * @return always true for Admin
     */
    public boolean hasAdminPrivileges() {
        return true;
    }
}

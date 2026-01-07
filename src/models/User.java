package models;

import java.io.Serializable;

/**
 * Abstract base class for all users in the system.
 * Defines common properties and behavior for Admin and Customer users.
 * 
 * @author AirlineReservationSystem
 * @version 1.0
 */
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String username;
    protected String password;
    protected String role; // "ADMIN" or "CUSTOMER"

    /**
     * Constructor for User.
     * 
     * @param username Unique username
     * @param password User password
     * @param role     User role (ADMIN/CUSTOMER)
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Abstract login method to be implemented by subclasses.
     * Each user type may have different login logic.
     * 
     * @param inputUsername Username provided by user
     * @param inputPassword Password provided by user
     * @return true if credentials match, false otherwise
     */
    public abstract boolean login(String inputUsername, String inputPassword);

    // Getters
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role + ": " + username;
    }
}

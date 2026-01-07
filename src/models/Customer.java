package models;

public class Customer extends User {
    private static final long serialVersionUID = 1L;

    private String customerId;
    private String email;

    public Customer(String username, String password) {
        super(username, password, "CUSTOMER");
        this.customerId = "C" + System.currentTimeMillis(); // Auto-generate ID
    }

    public Customer(String username, String password, String email) {
        this(username, password);
        this.email = email;
    }

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

package models;

public class Admin extends User {
    private static final long serialVersionUID = 1L;

    public Admin(String username, String password) {
        super(username, password, "ADMIN");
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) &&
                this.password.equals(inputPassword);
    }

    public boolean hasAdminPrivileges() {
        return true;
    }
}

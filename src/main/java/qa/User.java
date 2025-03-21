package qa;

public class User {
    public final String userName;
    public final String password;
    private final Address address;

    public User(String userName, String password, Address address) throws InvalidUserDetailsException {
        if (userName == null || userName.length() <= 2 || !userName.matches("[a-zA-Z]+")) {
            throw new InvalidUserDetailsException.InvalidUsernameException("Username must be alphabetic and longer than 2 characters");
        }
        if (password == null || password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()].*")) {
            throw new InvalidUserDetailsException.InvalidPasswordException("Password does not meet requirements");
        }
        if (address == null) {
            throw new InvalidUserDetailsException("Address cannot be null");
        }

        this.userName = userName.toLowerCase();
        this.password = password;
        this.address = address;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public Address getAddress() {
        return address;
    }
} 
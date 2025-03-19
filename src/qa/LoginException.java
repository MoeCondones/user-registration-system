package qa;

/**
 * Exception thrown when login credentials are invalid.
 */
public class LoginException extends Exception {
    
    public LoginException() {
        super("Incorrect username or password");
    }
    
    public LoginException(String message) {
        super(message);
    }
} 
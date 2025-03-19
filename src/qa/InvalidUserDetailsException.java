package qa;

/**
 * Exception thrown when invalid user details are provided.
 */
public class InvalidUserDetailsException extends Exception {
    
    public InvalidUserDetailsException() {
        super("Invalid user details provided");
    }
    
    public InvalidUserDetailsException(String message) {
        super(message);
    }
    
    public static class InvalidUsernameException extends InvalidUserDetailsException {
        public InvalidUsernameException() {
            super("Username must be alphabetic and longer than 2 characters");
        }
        
        public InvalidUsernameException(String message) {
            super(message);
        }
    }
    
    public static class InvalidPasswordException extends InvalidUserDetailsException {
        public InvalidPasswordException() {
            super("Password does not meet requirements");
        }
        
        public InvalidPasswordException(String message) {
            super(message);
        }
    }
    
    public static class UserAlreadyExistsException extends InvalidUserDetailsException {
        public UserAlreadyExistsException() {
            super("Username already exists");
        }
    }
} 
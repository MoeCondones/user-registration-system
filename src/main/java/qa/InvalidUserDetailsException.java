package qa;

public class InvalidUserDetailsException extends Exception {
    public InvalidUserDetailsException(String message) {
        super(message);
    }

    public static class InvalidUsernameException extends InvalidUserDetailsException {
        public InvalidUsernameException(String message) {
            super(message);
        }
    }

    public static class InvalidPasswordException extends InvalidUserDetailsException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExistsException extends InvalidUserDetailsException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }
} 
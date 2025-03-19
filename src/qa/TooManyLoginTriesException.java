package qa;

public class TooManyLoginTriesException extends RuntimeException {
    
    public TooManyLoginTriesException() {
        super("Too many login attempts. Your account has been temporarily locked.");
    }
    
    public TooManyLoginTriesException(String message) {
        super(message);
    }
} 
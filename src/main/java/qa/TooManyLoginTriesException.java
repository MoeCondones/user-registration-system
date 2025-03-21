package qa;

public class TooManyLoginTriesException extends RuntimeException {
    public TooManyLoginTriesException(String message) {
        super(message);
    }
} 
package qa;

/**
 * Exception thrown when an invalid UK address is provided.
 */
public class InvalidUKAddressException extends Exception {
    
    public InvalidUKAddressException() {
        super("Invalid UK address details provided");
    }
    
    public InvalidUKAddressException(String message) {
        super(message);
    }
    
    public static class InvalidHouseNumberException extends InvalidUKAddressException {
        public InvalidHouseNumberException() {
            super("Invalid house number format");
        }
    }
    
    public static class InvalidPostcodeException extends InvalidUKAddressException {
        public InvalidPostcodeException() {
            super("Invalid UK postcode format");
        }
    }
    
    public static class InvalidCityException extends InvalidUKAddressException {
        public InvalidCityException() {
            super("City is not recognized");
        }
    }
} 
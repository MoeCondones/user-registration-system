package qa;

public class InvalidUKAddressException extends Exception {
    public InvalidUKAddressException(String message) {
        super(message);
    }

    public static class InvalidHouseNumberException extends InvalidUKAddressException {
        public InvalidHouseNumberException(String message) {
            super(message);
        }
    }

    public static class InvalidPostcodeException extends InvalidUKAddressException {
        public InvalidPostcodeException(String message) {
            super(message);
        }
    }

    public static class InvalidCityException extends InvalidUKAddressException {
        public InvalidCityException(String message) {
            super(message);
        }
    }
} 
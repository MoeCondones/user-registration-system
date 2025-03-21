package qa;

public class Address {
    private final String houseNumber;
    private final String street;
    private final String postcode;
    private final String city;
    private final IUserAccountDb db;

    public Address(String houseNumber, String street, String postcode, String city, IUserAccountDb db) throws InvalidUKAddressException {
        if (houseNumber == null || !houseNumber.matches("\\d+[A-Za-z]?")) {
            throw new InvalidUKAddressException.InvalidHouseNumberException("Invalid house number format");
        }
        if (postcode == null || !postcode.matches("[A-Z]{1,2}[0-9][A-Z0-9]? ?[0-9][A-Z]{2}")) {
            throw new InvalidUKAddressException.InvalidPostcodeException("Invalid UK postcode format");
        }
        if (city == null || !isValidCity(city, db)) {
            throw new InvalidUKAddressException.InvalidCityException("City is not recognized");
        }

        this.houseNumber = houseNumber;
        this.street = street;
        this.postcode = postcode.toUpperCase();
        this.city = city;
        this.db = db;
    }

    private boolean isValidCity(String city, IUserAccountDb db) {
        String[] validCities = db.getCityNames();
        for (String validCity : validCities) {
            if (validCity.equalsIgnoreCase(city)) {
                return true;
            }
        }
        return false;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }
} 
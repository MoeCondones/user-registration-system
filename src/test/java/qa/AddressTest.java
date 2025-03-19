package test.java.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

import qa.Address;
import qa.UserAccountDBStub;

@DisplayName("Address Class Tests")
class AddressTest {

    private UserAccountDBStub db;

    @BeforeEach
    void setUp() {
        db = new UserAccountDBStub();
    }

    @Nested
    @DisplayName("House Number Validation Tests")
    class HouseNumberValidationTests {

        @Test
        @DisplayName("Valid house number should be accepted")
        void validHouseNumberShouldBeAccepted() {
            // Act & Assert
            assertDoesNotThrow(() -> new Address("123", "Main Street", "SW1A 1AA", "London", db));
        }

        @Test
        @DisplayName("Valid house number with dash should be accepted")
        void validHouseNumberWithDashShouldBeAccepted() {
            // Act & Assert
            assertDoesNotThrow(() -> new Address("123-125", "Main Street", "SW1A 1AA", "London", db));
        }

        @Test
        @DisplayName("House number with letters should throw exception")
        void houseNumberWithLettersShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new Address("123A", "Main Street", "SW1A 1AA", "London", db));
            
            assertEquals("Invalid house number format", exception.getMessage());
        }

        @Test
        @DisplayName("Null house number should throw exception")
        void nullHouseNumberShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new Address(null, "Main Street", "SW1A 1AA", "London", db));
            
            assertEquals("Invalid house number format", exception.getMessage());
        }

        @Test
        @DisplayName("Empty house number should throw exception")
        void emptyHouseNumberShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new Address("", "Main Street", "SW1A 1AA", "London", db));
            
            assertEquals("Invalid house number format", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Address Line Validation Tests")
    class AddressLineValidationTests {

        @Test
        @DisplayName("Valid address line should be accepted")
        void validAddressLineShouldBeAccepted() {
            // Act & Assert
            assertDoesNotThrow(() -> new Address("123", "Main Street", "SW1A 1AA", "London", db));
        }

        @Test
        @DisplayName("Null address line should throw exception")
        void nullAddressLineShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new Address("123", null, "SW1A 1AA", "London", db));
            
            assertEquals("Address line cannot be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Postcode Validation Tests")
    class PostcodeValidationTests {

        @Test
        @DisplayName("Valid UK postcode should be accepted")
        void validUKPostcodeShouldBeAccepted() {
            // Act & Assert
            assertDoesNotThrow(() -> new Address("123", "Main Street", "SW1A 1AA", "London", db));
        }

        @Test
        @DisplayName("Invalid UK postcode should throw exception")
        void invalidUKPostcodeShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new Address("123", "Main Street", "12345", "London", db));
            
            assertEquals("Invalid UK postcode format", exception.getMessage());
        }

        @Test
        @DisplayName("Null postcode should throw exception")
        void nullPostcodeShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new Address("123", "Main Street", null, "London", db));
            
            assertEquals("Invalid UK postcode format", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("City Validation Tests")
    class CityValidationTests {

        @Test
        @DisplayName("Valid city should be accepted")
        void validCityShouldBeAccepted() {
            // Act & Assert
            assertDoesNotThrow(() -> new Address("123", "Main Street", "SW1A 1AA", "London", db));
        }

        @Test
        @DisplayName("Invalid city should throw exception")
        void invalidCityShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new Address("123", "Main Street", "SW1A 1AA", "Paris", db));
            
            assertEquals("City is not recognized", exception.getMessage());
        }

        @Test
        @DisplayName("Null city should throw exception")
        void nullCityShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new Address("123", "Main Street", "SW1A 1AA", null, db));
            
            assertEquals("City is not recognized", exception.getMessage());
        }
    }
} 
package test.java.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

import qa.Address;
import qa.User;
import qa.UserAccountDBStub;

@DisplayName("User Class Tests")
class UserTest {

    private UserAccountDBStub db;
    private Address validAddress;

    @BeforeEach
    void setUp() {
        db = new UserAccountDBStub();
        validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
    }

    @Nested
    @DisplayName("Username Validation Tests")
    class UsernameValidationTests {

        @Test
        @DisplayName("Valid username should be accepted")
        void validUsernameShouldBeAccepted() {
            // Act & Assert
            assertDoesNotThrow(() -> new User("JohnDoe", "Password$123", validAddress));
        }

        @Test
        @DisplayName("Username should be stored in lowercase")
        void usernameShouldBeStoredInLowercase() {
            // Act
            User user = new User("JohnDoe", "Password$123", validAddress);
            
            // Assert
            assertEquals("johndoe", user.userName);
        }

        @Test
        @DisplayName("Null username should throw exception")
        void nullUsernameShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User(null, "Password$123", validAddress));
            
            assertEquals("Username cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Username too short should throw exception")
        void usernameTooShortShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User("Jo", "Password$123", validAddress));
            
            assertEquals("Username must be alphabetic and longer than 2 characters", exception.getMessage());
        }

        @Test
        @DisplayName("Username with digits should throw exception")
        void usernameWithDigitsShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User("John123", "Password$123", validAddress));
            
            assertEquals("Username must be alphabetic and longer than 2 characters", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Password Validation Tests")
    class PasswordValidationTests {

        @Test
        @DisplayName("Valid password should be accepted")
        void validPasswordShouldBeAccepted() {
            // Act & Assert
            assertDoesNotThrow(() -> new User("JohnDoe", "Password$123", validAddress));
        }

        @Test
        @DisplayName("Password without digit should throw exception")
        void passwordWithoutDigitShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User("JohnDoe", "Password$", validAddress));
            
            assertEquals("Password does not meet requirements", exception.getMessage());
        }

        @Test
        @DisplayName("Password without special character should throw exception")
        void passwordWithoutSpecialCharShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User("JohnDoe", "Password123", validAddress));
            
            assertEquals("Password does not meet requirements", exception.getMessage());
        }

        @Test
        @DisplayName("Password with forbidden character should throw exception")
        void passwordWithForbiddenCharShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User("JohnDoe", "Password$123<", validAddress));
            
            assertEquals("Password does not meet requirements", exception.getMessage());
        }

        @Test
        @DisplayName("Password too short should throw exception")
        void passwordTooShortShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User("JohnDoe", "Pass$1", validAddress));
            
            assertEquals("Password does not meet requirements", exception.getMessage());
        }

        @Test
        @DisplayName("Null password should throw exception")
        void nullPasswordShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User("JohnDoe", null, validAddress));
            
            assertEquals("Password does not meet requirements", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Address Validation Tests")
    class AddressValidationTests {

        @Test
        @DisplayName("Null address should throw exception")
        void nullAddressShouldThrowException() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> new User("JohnDoe", "Password$123", null));
            
            assertEquals("Address cannot be null", exception.getMessage());
        }
    }
} 
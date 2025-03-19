package test.java.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

import qa.Address;
import qa.User;
import qa.UserAccountDBStub;
import qa.UserAccountManager;
import qa.TooManyLoginTriesException;

@DisplayName("UserAccountManager Class Tests")
class UserAccountManagerTest {

    private UserAccountDBStub db;
    private UserAccountManager manager;
    private Address validAddress;
    private User validUser;

    @BeforeEach
    void setUp() {
        db = new UserAccountDBStub();
        manager = new UserAccountManager(db);
        validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
        validUser = new User("TestUser", "Password$123", validAddress);
    }

    @Nested
    @DisplayName("Registration Tests")
    class RegistrationTests {

        @Test
        @DisplayName("Valid user should be registered successfully")
        void validUserShouldBeRegisteredSuccessfully() {
            // Act
            boolean result = manager.register(validUser);
            
            // Assert
            assertTrue(result);
        }

        @Test
        @DisplayName("Registering existing user should throw exception")
        void registeringExistingUserShouldThrowException() {
            // Arrange
            manager.register(validUser);
            
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> manager.register(validUser));
            
            assertEquals("Username already exists", exception.getMessage());
        }

        @Test
        @DisplayName("Username should be case insensitive during registration")
        void usernameShouldBeCaseInsensitiveDuringRegistration() {
            // Arrange
            manager.register(validUser);
            
            // Create a user with the same username but different case
            User sameUserDifferentCase = new User("testuser", "Password$123", validAddress);
            
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> manager.register(sameUserDifferentCase));
            
            assertEquals("Username already exists", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Login Tests")
    class LoginTests {

        @BeforeEach
        void registerUser() {
            manager.register(validUser);
        }

        @Test
        @DisplayName("Valid credentials should allow login")
        void validCredentialsShouldAllowLogin() {
            // Act & Assert
            assertDoesNotThrow(() -> manager.login(validUser));
        }

        @Test
        @DisplayName("Username should be case insensitive during login")
        void usernameShouldBeCaseInsensitiveDuringLogin() {
            // Arrange
            User loginUser = new User("testuser", "Password$123", validAddress);
            
            // Act & Assert
            assertDoesNotThrow(() -> manager.login(loginUser));
        }

        @Test
        @DisplayName("Password should be case sensitive during login")
        void passwordShouldBeCaseSensitiveDuringLogin() {
            // Arrange
            User loginUser = new User("TestUser", "password$123", validAddress);
            
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> manager.login(loginUser));
            
            assertEquals("User not registered or invalid credentials", exception.getMessage());
        }

        @Test
        @DisplayName("Too many login attempts should throw TooManyLoginTriesException")
        void tooManyLoginAttemptsShouldThrowException() {
            // Arrange
            User invalidUser = new User("TestUser", "WrongPassword$123", validAddress);
            
            // Act & Assert
            // First 3 attempts should throw IllegalArgumentException
            for (int i = 0; i < 3; i++) {
                assertThrows(IllegalArgumentException.class, () -> manager.login(invalidUser));
            }
            
            // 4th attempt should throw TooManyLoginTriesException
            Exception exception = assertThrows(TooManyLoginTriesException.class, 
                () -> manager.login(invalidUser));
            
            assertEquals("Too many login attempts. Your account has been temporarily locked.", exception.getMessage());
        }

        @Test
        @DisplayName("Login attempts counter should reset after successful login")
        void loginAttemptsCounterShouldResetAfterSuccessfulLogin() {
            // Arrange
            User invalidUser = new User("TestUser", "WrongPassword$123", validAddress);
            
            // Act & Assert
            // First 2 attempts with wrong password
            for (int i = 0; i < 2; i++) {
                assertThrows(IllegalArgumentException.class, () -> manager.login(invalidUser));
            }
            
            // Successful login
            assertTrue(manager.login(validUser));
            
            // Should be able to make 3 more failed attempts before exception
            for (int i = 0; i < 3; i++) {
                assertThrows(IllegalArgumentException.class, () -> manager.login(invalidUser));
            }
            
            // 4th attempt after reset should throw TooManyLoginTriesException
            assertThrows(TooManyLoginTriesException.class, () -> manager.login(invalidUser));
        }
    }
} 
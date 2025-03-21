package qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;

public class UserAccountManagerTest {
    @Mock
    private IUserAccountDb mockDb;
    
    private UserAccountManager manager;
    private Address validAddress;
    private User validUser;
    private String[] validCities = {"London", "Manchester", "Birmingham", "Glasgow"};

    @BeforeEach
    public void setUp() throws InvalidUKAddressException, InvalidUserDetailsException {
        MockitoAnnotations.openMocks(this);
        
        // Mock the database behavior
        when(mockDb.getCityNames()).thenReturn(validCities);
        
        manager = new UserAccountManager(mockDb);
        validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", mockDb);
        validUser = new User("TestUser", "Password$123", validAddress);
    }

    @Test
    @DisplayName("Should successfully register a new user")
    public void testSuccessfulRegistration() throws InvalidUserDetailsException {
        // Arrange
        when(mockDb.isRegistered(anyString())).thenReturn(false);
        when(mockDb.register(any(User.class))).thenReturn(true);

        // Act
        boolean result = manager.register(validUser);

        // Assert
        assertTrue(result);
        verify(mockDb).register(validUser);
    }

    @Test
    @DisplayName("Should fail to register an existing user")
    public void testRegistrationOfExistingUser() {
        // Arrange
        when(mockDb.isRegistered(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(InvalidUserDetailsException.UserAlreadyExistsException.class,
            () -> manager.register(validUser));
    }

    @Test
    @DisplayName("Should successfully login with valid credentials")
    public void testSuccessfulLogin() throws InvalidUserDetailsException, LoginException {
        // Arrange
        when(mockDb.isRegistered(anyString())).thenReturn(true);
        when(mockDb.getRegisteredUser(anyString())).thenReturn(validUser);

        // Act
        boolean result = manager.login(validUser);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should fail login with invalid password")
    public void testLoginWithInvalidPassword() {
        // Arrange
        when(mockDb.isRegistered(anyString())).thenReturn(true);
        when(mockDb.getRegisteredUser(anyString())).thenReturn(validUser);
        User invalidUser;
        try {
            invalidUser = new User("TestUser", "WrongPassword$123", validAddress);
            
            // Act & Assert
            assertThrows(LoginException.class, () -> manager.login(invalidUser));
        } catch (InvalidUserDetailsException e) {
            fail("Should not throw exception when creating test user");
        }
    }

    @Test
    @DisplayName("Should lock account after too many failed attempts")
    public void testAccountLockout() {
        // Arrange
        when(mockDb.isRegistered(anyString())).thenReturn(true);
        when(mockDb.getRegisteredUser(anyString())).thenReturn(validUser);
        User invalidUser;
        try {
            invalidUser = new User("TestUser", "WrongPassword$123", validAddress);
            
            // Act & Assert
            // Two failed attempts
            assertThrows(LoginException.class, () -> manager.login(invalidUser));
            assertThrows(LoginException.class, () -> manager.login(invalidUser));
            
            // Third attempt should trigger lockout
            assertThrows(TooManyLoginTriesException.class, () -> manager.login(invalidUser));
        } catch (InvalidUserDetailsException e) {
            fail("Should not throw exception when creating test user");
        }
    }
    
    @Test
    @DisplayName("Should reset login attempts counter after successful login")
    public void testLoginAttemptsResetAfterSuccess() throws InvalidUserDetailsException, LoginException {
        // Arrange
        when(mockDb.isRegistered(anyString())).thenReturn(true);
        when(mockDb.getRegisteredUser(anyString())).thenReturn(validUser);
        User invalidUser = new User("TestUser", "WrongPassword$123", validAddress);

        // Act & Assert
        // Two failed attempts
        assertThrows(LoginException.class, () -> manager.login(invalidUser));
        assertThrows(LoginException.class, () -> manager.login(invalidUser));
        
        // Successful login
        boolean result = manager.login(validUser);
        assertTrue(result);
        
        // Should be able to try two more times without TooManyLoginTriesException
        assertThrows(LoginException.class, () -> manager.login(invalidUser));
        assertThrows(LoginException.class, () -> manager.login(invalidUser));
        
        // Third attempt after reset should trigger lockout
        assertThrows(TooManyLoginTriesException.class, () -> manager.login(invalidUser));
    }
} 
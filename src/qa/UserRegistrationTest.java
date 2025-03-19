package qa;

/**
 * This class demonstrates how to test the user registration system
 * based on the requirements.
 */
public class UserRegistrationTest {
    
    public static void main(String[] args) {
        // Create a database stub
        UserAccountDBStub db = new UserAccountDBStub();
        
        // Test username validation
        testUsernameValidation(db);
        
        // Test password validation
        testPasswordValidation(db);
        
        // Test address validation
        testAddressValidation(db);
        
        // Test login functionality
        testLoginFunctionality(db);
    }
    
    private static void testUsernameValidation(UserAccountDBStub db) {
        System.out.println("\n=== Testing Username Validation ===");
        
        // Test case: Valid username
        try {
            Address validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User("JohnDoe", "Password$123", validAddress);
            System.out.println("Valid username test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("Valid username test: FAILED - " + e.getMessage());
        } catch (InvalidUserDetailsException e) {
            System.out.println("Valid username test: FAILED - " + e.getMessage());
        }
        
        // Test case: Username too short
        try {
            Address validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User("Jo", "Password$123", validAddress);
            System.out.println("Short username test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidUsernameException e) {
            System.out.println("Short username test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Short username test: FAILED - Wrong exception type");
        } catch (InvalidUserDetailsException e) {
            System.out.println("Short username test: PASSED - " + e.getMessage());
        }
        
        // Test case: Username with digits
        try {
            Address validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User("John123", "Password$123", validAddress);
            System.out.println("Username with digits test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidUsernameException e) {
            System.out.println("Username with digits test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Username with digits test: FAILED - Wrong exception type");
        } catch (InvalidUserDetailsException e) {
            System.out.println("Username with digits test: PASSED - " + e.getMessage());
        }
        
        // Test case: Null username
        try {
            Address validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User(null, "Password$123", validAddress);
            System.out.println("Null username test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidUsernameException e) {
            System.out.println("Null username test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Null username test: FAILED - Wrong exception type");
        } catch (InvalidUserDetailsException e) {
            System.out.println("Null username test: PASSED - " + e.getMessage());
        }
    }
    
    private static void testPasswordValidation(UserAccountDBStub db) {
        System.out.println("\n=== Testing Password Validation ===");
        
        // Test case: Valid password
        try {
            Address validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User("JohnDoe", "Password$123", validAddress);
            System.out.println("Valid password test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("Valid password test: FAILED - " + e.getMessage());
        } catch (InvalidUserDetailsException e) {
            System.out.println("Valid password test: FAILED - " + e.getMessage());
        }
        
        // Test case: Password without digit
        try {
            Address validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User("JohnDoe", "Password$", validAddress);
            System.out.println("Password without digit test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidPasswordException e) {
            System.out.println("Password without digit test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Password without digit test: FAILED - Wrong exception type");
        } catch (InvalidUserDetailsException e) {
            System.out.println("Password without digit test: PASSED - " + e.getMessage());
        }
        
        // Test case: Password without special character
        try {
            Address validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User("JohnDoe", "Password123", validAddress);
            System.out.println("Password without special char test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidPasswordException e) {
            System.out.println("Password without special char test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Password without special char test: FAILED - Wrong exception type");
        } catch (InvalidUserDetailsException e) {
            System.out.println("Password without special char test: PASSED - " + e.getMessage());
        }
        
        // Test case: Password with forbidden character
        try {
            Address validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User("JohnDoe", "Password$123<", validAddress);
            System.out.println("Password with forbidden char test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidPasswordException e) {
            System.out.println("Password with forbidden char test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Password with forbidden char test: FAILED - Wrong exception type");
        } catch (InvalidUserDetailsException e) {
            System.out.println("Password with forbidden char test: PASSED - " + e.getMessage());
        }
    }
    
    private static void testAddressValidation(UserAccountDBStub db) {
        System.out.println("\n=== Testing Address Validation ===");
        
        // Test case: Valid address
        try {
            Address address = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            System.out.println("Valid address test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("Valid address test: FAILED - " + e.getMessage());
        }
        
        // Test case: Invalid house number
        try {
            Address address = new Address("123A", "Main Street", "SW1A 1AA", "London", db);
            System.out.println("Invalid house number test: FAILED - Should have thrown exception");
        } catch (InvalidUKAddressException.InvalidHouseNumberException e) {
            System.out.println("Invalid house number test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Invalid house number test: PASSED - " + e.getMessage());
        }
        
        // Test case: Valid house number with dash
        try {
            Address address = new Address("123-125", "Main Street", "SW1A 1AA", "London", db);
            System.out.println("House number with dash test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("House number with dash test: FAILED - " + e.getMessage());
        }
        
        // Test case: Invalid city
        try {
            Address address = new Address("123", "Main Street", "SW1A 1AA", "Paris", db);
            System.out.println("Invalid city test: FAILED - Should have thrown exception");
        } catch (InvalidUKAddressException.InvalidCityException e) {
            System.out.println("Invalid city test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Invalid city test: PASSED - " + e.getMessage());
        }
        
        // Test case: Invalid postcode
        try {
            Address address = new Address("123", "Main Street", "12345", "London", db);
            System.out.println("Invalid postcode test: FAILED - Should have thrown exception");
        } catch (InvalidUKAddressException.InvalidPostcodeException e) {
            System.out.println("Invalid postcode test: PASSED - " + e.getMessage());
        } catch (InvalidUKAddressException e) {
            System.out.println("Invalid postcode test: PASSED - " + e.getMessage());
        }
    }
    
    private static void testLoginFunctionality(UserAccountDBStub db) {
        System.out.println("\n=== Testing Login Functionality ===");
        
        // Create a user account manager
        UserAccountManager manager = new UserAccountManager(db);
        
        try {
            // Create a valid address and user
            Address address = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            User user = new User("TestUser", "Password$123", address);
            
            // Register the user
            boolean registered = manager.register(user);
            System.out.println("User registration: " + (registered ? "PASSED" : "FAILED"));
            
            // Test case: Valid login
            try {
                User loginUser = new User("testuser", "Password$123", address); // Case insensitive username
                boolean loggedIn = manager.login(loginUser);
                System.out.println("Valid login test: PASSED");
            } catch (LoginException e) {
                System.out.println("Valid login test: FAILED - " + e.getMessage());
            } catch (TooManyLoginTriesException e) {
                System.out.println("Valid login test: FAILED - " + e.getMessage());
            }
            
            // Test case: Invalid password
            try {
                User loginUser = new User("TestUser", "WrongPassword$123", address);
                boolean loggedIn = manager.login(loginUser);
                System.out.println("Invalid password test: FAILED - Should have thrown exception");
            } catch (LoginException e) {
                System.out.println("Invalid password test: PASSED - " + e.getMessage());
            } catch (TooManyLoginTriesException e) {
                System.out.println("Invalid password test: FAILED - Wrong exception type");
            }
            
            // Test case: Too many login attempts
            try {
                User loginUser = new User("TestUser", "WrongPassword$123", address);
                
                // Try to login multiple times
                for (int i = 0; i < 5; i++) {
                    try {
                        manager.login(loginUser);
                        System.out.println("Login attempt " + (i+1) + ": FAILED - Should have thrown exception");
                    } catch (LoginException e) {
                        System.out.println("Login attempt " + (i+1) + ": PASSED - " + e.getMessage());
                    } catch (TooManyLoginTriesException e) {
                        throw e; // Re-throw to be caught by outer catch
                    }
                }
                
                System.out.println("Too many login attempts test: FAILED - Should have thrown TooManyLoginTriesException");
            } catch (TooManyLoginTriesException e) {
                System.out.println("Too many login attempts test: PASSED - " + e.getMessage());
            }
            
        } catch (InvalidUKAddressException e) {
            System.out.println("Login functionality test setup failed: " + e.getMessage());
        } catch (InvalidUserDetailsException e) {
            System.out.println("Login functionality test setup failed: " + e.getMessage());
        } catch (TooManyLoginTriesException e) {
            System.out.println("Login functionality test setup failed: " + e.getMessage());
        }
    }
} 
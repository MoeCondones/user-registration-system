package test.java.qa;

import qa.*;

/**
 * This class runs all the tests for the user registration system.
 * It doesn't use JUnit annotations but follows a similar structure.
 */
public class AllTests {
    
    public static void main(String[] args) {
        System.out.println("Running all tests for the User Registration System");
        System.out.println("=================================================");
        
        // Run User tests
        testUser();
        
        // Run Address tests
        testAddress();
        
        // Run UserAccountManager tests
        testUserAccountManager();
        
        System.out.println("\nAll tests completed.");
    }
    
    private static void testUser() {
        System.out.println("\n=== User Class Tests ===");
        
        UserAccountDBStub db = new UserAccountDBStub();
        Address validAddress = null;
        
        try {
            validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
        } catch (InvalidUKAddressException e) {
            System.out.println("✗ Setup failed: " + e.getMessage());
            return;
        }
        
        // Test valid username
        try {
            User user = new User("JohnDoe", "Password$123", validAddress);
            System.out.println("✓ Valid username test: PASSED");
            
            // Test username is stored in lowercase
            if ("johndoe".equals(user.userName)) {
                System.out.println("✓ Username stored in lowercase test: PASSED");
            } else {
                System.out.println("✗ Username stored in lowercase test: FAILED");
            }
        } catch (InvalidUserDetailsException e) {
            System.out.println("✗ Valid username test: FAILED - " + e.getMessage());
        }
        
        // Test null username
        try {
            new User(null, "Password$123", validAddress);
            System.out.println("✗ Null username test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidUsernameException e) {
            if (e.getMessage().contains("cannot be null")) {
                System.out.println("✓ Null username test: PASSED");
            } else {
                System.out.println("✗ Null username test: FAILED - Wrong message: " + e.getMessage());
            }
        } catch (InvalidUserDetailsException e) {
            System.out.println("✗ Null username test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test username too short
        try {
            new User("Jo", "Password$123", validAddress);
            System.out.println("✗ Short username test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidUsernameException e) {
            System.out.println("✓ Short username test: PASSED");
        } catch (InvalidUserDetailsException e) {
            System.out.println("✗ Short username test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test username with digits
        try {
            new User("John123", "Password$123", validAddress);
            System.out.println("✗ Username with digits test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidUsernameException e) {
            System.out.println("✓ Username with digits test: PASSED");
        } catch (InvalidUserDetailsException e) {
            System.out.println("✗ Username with digits test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test password validation
        try {
            new User("JohnDoe", "Password$123", validAddress);
            System.out.println("✓ Valid password test: PASSED");
        } catch (InvalidUserDetailsException e) {
            System.out.println("✗ Valid password test: FAILED - " + e.getMessage());
        }
        
        // Test password without digit
        try {
            new User("JohnDoe", "Password$", validAddress);
            System.out.println("✗ Password without digit test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidPasswordException e) {
            System.out.println("✓ Password without digit test: PASSED");
        } catch (InvalidUserDetailsException e) {
            System.out.println("✗ Password without digit test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test password without special character
        try {
            new User("JohnDoe", "Password123", validAddress);
            System.out.println("✗ Password without special char test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.InvalidPasswordException e) {
            System.out.println("✓ Password without special char test: PASSED");
        } catch (InvalidUserDetailsException e) {
            System.out.println("✗ Password without special char test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test null address
        try {
            new User("JohnDoe", "Password$123", null);
            System.out.println("✗ Null address test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException e) {
            if (e.getMessage().contains("Address cannot be null")) {
                System.out.println("✓ Null address test: PASSED");
            } else {
                System.out.println("✗ Null address test: FAILED - Wrong message: " + e.getMessage());
            }
        }
    }
    
    private static void testAddress() {
        System.out.println("\n=== Address Class Tests ===");
        
        UserAccountDBStub db = new UserAccountDBStub();
        
        // Test valid address
        try {
            new Address("123", "Main Street", "SW1A 1AA", "London", db);
            System.out.println("✓ Valid address test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("✗ Valid address test: FAILED - " + e.getMessage());
        }
        
        // Test valid house number with dash
        try {
            new Address("123-125", "Main Street", "SW1A 1AA", "London", db);
            System.out.println("✓ House number with dash test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("✗ House number with dash test: FAILED - " + e.getMessage());
        }
        
        // Test invalid house number
        try {
            new Address("123A", "Main Street", "SW1A 1AA", "London", db);
            System.out.println("✗ Invalid house number test: FAILED - Should have thrown exception");
        } catch (InvalidUKAddressException.InvalidHouseNumberException e) {
            System.out.println("✓ Invalid house number test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("✗ Invalid house number test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test null address line
        try {
            new Address("123", null, "SW1A 1AA", "London", db);
            System.out.println("✗ Null address line test: FAILED - Should have thrown exception");
        } catch (InvalidUKAddressException e) {
            if (e.getMessage().contains("Address line cannot be null")) {
                System.out.println("✓ Null address line test: PASSED");
            } else {
                System.out.println("✗ Null address line test: FAILED - Wrong message: " + e.getMessage());
            }
        }
        
        // Test invalid postcode
        try {
            new Address("123", "Main Street", "12345", "London", db);
            System.out.println("✗ Invalid postcode test: FAILED - Should have thrown exception");
        } catch (InvalidUKAddressException.InvalidPostcodeException e) {
            System.out.println("✓ Invalid postcode test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("✗ Invalid postcode test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test invalid city
        try {
            new Address("123", "Main Street", "SW1A 1AA", "Paris", db);
            System.out.println("✗ Invalid city test: FAILED - Should have thrown exception");
        } catch (InvalidUKAddressException.InvalidCityException e) {
            System.out.println("✓ Invalid city test: PASSED");
        } catch (InvalidUKAddressException e) {
            System.out.println("✗ Invalid city test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
    }
    
    private static void testUserAccountManager() {
        System.out.println("\n=== UserAccountManager Class Tests ===");
        
        UserAccountDBStub db = new UserAccountDBStub();
        UserAccountManager manager = new UserAccountManager(db);
        Address validAddress = null;
        User validUser = null;
        
        try {
            validAddress = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            validUser = new User("TestUser", "Password$123", validAddress);
        } catch (Exception e) {
            System.out.println("✗ Setup failed: " + e.getMessage());
            return;
        }
        
        // Test registration
        try {
            boolean result = manager.register(validUser);
            if (result) {
                System.out.println("✓ User registration test: PASSED");
            } else {
                System.out.println("✗ User registration test: FAILED - Registration returned false");
            }
        } catch (Exception e) {
            System.out.println("✗ User registration test: FAILED - " + e.getMessage());
        }
        
        // Test registering existing user
        try {
            manager.register(validUser);
            System.out.println("✗ Registering existing user test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.UserAlreadyExistsException e) {
            System.out.println("✓ Registering existing user test: PASSED");
        } catch (Exception e) {
            System.out.println("✗ Registering existing user test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test case insensitive username during registration
        try {
            User sameUserDifferentCase = new User("testuser", "Password$123", validAddress);
            manager.register(sameUserDifferentCase);
            System.out.println("✗ Case insensitive username test: FAILED - Should have thrown exception");
        } catch (InvalidUserDetailsException.UserAlreadyExistsException e) {
            System.out.println("✓ Case insensitive username test: PASSED");
        } catch (Exception e) {
            System.out.println("✗ Case insensitive username test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Create a new manager for login tests
        UserAccountManager loginManager = new UserAccountManager(db);
        User loginUser = null;
        
        try {
            loginUser = new User("LoginUser", "Password$123", validAddress);
            loginManager.register(loginUser);
        } catch (Exception e) {
            System.out.println("✗ Login test setup failed: " + e.getMessage());
            return;
        }
        
        // Test valid login
        try {
            boolean result = loginManager.login(loginUser);
            if (result) {
                System.out.println("✓ Valid login test: PASSED");
            } else {
                System.out.println("✗ Valid login test: FAILED - Login returned false");
            }
        } catch (Exception e) {
            System.out.println("✗ Valid login test: FAILED - " + e.getMessage());
        }
        
        // Test case insensitive username during login
        try {
            User caseInsensitiveUser = new User("loginuser", "Password$123", validAddress);
            boolean result = loginManager.login(caseInsensitiveUser);
            if (result) {
                System.out.println("✓ Case insensitive login test: PASSED");
            } else {
                System.out.println("✗ Case insensitive login test: FAILED - Login returned false");
            }
        } catch (Exception e) {
            System.out.println("✗ Case insensitive login test: FAILED - " + e.getMessage());
        }
        
        // Test case sensitive password during login
        try {
            User wrongPasswordUser = new User("LoginUser", "password$123", validAddress);
            loginManager.login(wrongPasswordUser);
            System.out.println("✗ Case sensitive password test: FAILED - Should have thrown exception");
        } catch (LoginException e) {
            System.out.println("✓ Case sensitive password test: PASSED");
        } catch (Exception e) {
            System.out.println("✗ Case sensitive password test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
        
        // Test too many login attempts
        UserAccountManager attemptsManager = new UserAccountManager(db);
        User attemptsUser = null;
        User invalidUser = null;
        
        try {
            attemptsUser = new User("AttemptsUser", "Password$123", validAddress);
            attemptsManager.register(attemptsUser);
            invalidUser = new User("AttemptsUser", "WrongPassword$123", validAddress);
        } catch (Exception e) {
            System.out.println("✗ Login attempts test setup failed: " + e.getMessage());
            return;
        }
        
        System.out.println("\nTesting too many login attempts:");
        try {
            // First 3 attempts should throw LoginException
            for (int i = 0; i < 3; i++) {
                try {
                    attemptsManager.login(invalidUser);
                    System.out.println("✗ Login attempt " + (i+1) + ": FAILED - Should have thrown exception");
                } catch (LoginException e) {
                    System.out.println("✓ Login attempt " + (i+1) + ": PASSED");
                } catch (Exception e) {
                    System.out.println("✗ Login attempt " + (i+1) + ": FAILED - Wrong exception type: " + e.getClass().getSimpleName());
                }
            }
            
            // 4th attempt should throw TooManyLoginTriesException
            attemptsManager.login(invalidUser);
            System.out.println("✗ Too many login attempts test: FAILED - Should have thrown exception");
        } catch (TooManyLoginTriesException e) {
            System.out.println("✓ Too many login attempts test: PASSED");
        } catch (Exception e) {
            System.out.println("✗ Too many login attempts test: FAILED - Wrong exception type: " + e.getClass().getSimpleName());
        }
    }
} 
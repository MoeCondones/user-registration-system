package qa;

public class Main {
    public static void main(String[] args) {
        // Create a database stub
        UserAccountDBStub db = new UserAccountDBStub();
        
        // Create a user account manager
        UserAccountManager manager = new UserAccountManager(db);
        
        try {
            // Create an address with valid house number
            Address address = new Address("123", "Main Street", "SW1A 1AA", "London", db);
            
            // Create a user with valid username and password
            User user = new User("JohnDoe", "Password$123", address);
            
            // Register the user
            boolean registered = manager.register(user);
            System.out.println("User registered: " + registered);
            
            // Try to login with case-insensitive username
            User loginUser = new User("johndoe", "Password$123", address);
            boolean loggedIn = manager.login(loginUser);
            System.out.println("User logged in: " + loggedIn);
            
            // Demonstrate too many login attempts
            System.out.println("\nDemonstrating too many login attempts:");
            try {
                // Create an invalid user for login attempts
                User invalidUser = new User("JohnDoe", "WrongPassword$123", address);
                
                // Try to login multiple times
                for (int i = 0; i < 5; i++) {
                    try {
                        manager.login(invalidUser);
                    } catch (LoginException e) {
                        System.out.println("Login attempt " + (i+1) + ": " + e.getMessage());
                    }
                }
            } catch (TooManyLoginTriesException e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
            
        } catch (InvalidUKAddressException e) {
            System.out.println("Address error: " + e.getMessage());
        } catch (InvalidUserDetailsException e) {
            System.out.println("User details error: " + e.getMessage());
        } catch (LoginException e) {
            System.out.println("Login error: " + e.getMessage());
        } catch (TooManyLoginTriesException e) {
            System.out.println("Too many login attempts: " + e.getMessage());
        }
    }
} 
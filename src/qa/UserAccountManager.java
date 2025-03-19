package qa; 
 
// Uses the actual database or a stub/mock database to login or register a user 
public class UserAccountManager 
{ 
    IUserAccountDb userAccountDb;
    private int loginAttempts = 0;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
 
    public UserAccountManager(IUserAccountDb userAccountDb) 
    { 
        this.userAccountDb = userAccountDb; 
    } 
 
    // Logs user in.  
    // The username and password format has already been validated 
    public boolean login(User user) throws TooManyLoginTriesException, LoginException
    { 
        // Check if number of tries to login is > 3
        loginAttempts++;
        if (loginAttempts > MAX_LOGIN_ATTEMPTS) {
            throw new TooManyLoginTriesException();
        }
 
        if (!userAccountDb.isRegisteredUser(user)) {
            throw new LoginException();
        }
 
        // Reset login attempts on successful login
        loginAttempts = 0;
        return true;
    } 
 
    public boolean register(User user) throws InvalidUserDetailsException
    { 
        if (userAccountDb.isAnExistingUser(user)) 
            throw new InvalidUserDetailsException.UserAlreadyExistsException(); 
 
        return userAccountDb.register(user); 
    } 
} 
package qa;

public class UserAccountManager {
    private final IUserAccountDb db;
    private int loginAttempts;

    public UserAccountManager(IUserAccountDb db) {
        this.db = db;
        this.loginAttempts = 0;
    }

    public boolean register(User user) throws InvalidUserDetailsException {
        if (db.isRegistered(user.userName)) {
            throw new InvalidUserDetailsException.UserAlreadyExistsException("Username already exists");
        }
        return db.register(user);
    }

    public boolean login(User user) throws InvalidUserDetailsException, LoginException {
        if (!db.isRegistered(user.userName)) {
            throw new InvalidUserDetailsException("User not registered");
        }

        User registeredUser = db.getRegisteredUser(user.userName);
        if (!registeredUser.checkPassword(user.password)) {
            loginAttempts++;
            if (loginAttempts >= 3) {
                throw new TooManyLoginTriesException("Too many login attempts. Your account has been temporarily locked.");
            }
            throw new LoginException("Incorrect username or password");
        }

        loginAttempts = 0;
        return true;
    }
} 
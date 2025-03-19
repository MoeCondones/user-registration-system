package qa; 
 
public class User 
{ 
    public String userName;  
    String password;  
    Address address;  
 
    public User(String userName, String password, Address address) throws InvalidUserDetailsException
    { 
        if (userName == null)
            throw new InvalidUserDetailsException.InvalidUsernameException("Username cannot be null");
            
        if (Utils.isAlphabetic(userName) && userName.length() > 2) 
            this.userName = userName.toLowerCase(); // Store username in lowercase for case-insensitive comparison
        else 
            throw new InvalidUserDetailsException.InvalidUsernameException(); 
 
        if (isValidPasswordFormat(password)) 
            this.password = password; 
        else 
            throw new InvalidUserDetailsException.InvalidPasswordException(); 
        
        if (address == null)
            throw new InvalidUserDetailsException("Address cannot be null");
            
        this.address = address; 
    } 
 
    private boolean isValidPasswordFormat(String password) 
    { 
        if (password == null)
            return false;
            
        if (password.length() <= 8)
            return false;
            
        if (!Utils.hasOneDigit(password))
            return false;
            
        if (!Utils.hasExtraPasswordChars(password))
            return false;
            
        // Check for forbidden characters/sequences
        if (password.contains("<") || password.contains(">") || 
            password.contains(";") || password.contains("=") || 
            password.contains("'or'") || password.contains("--"))
            return false;
            
        return true;
    } 
} 
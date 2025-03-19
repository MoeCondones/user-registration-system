package qa; 
 
import java.util.ArrayList; 
 
public class UserAccountDBStub implements IUserAccountDb { 
 
// Database in memory 
    ArrayList<String> cities; 
    ArrayList<User> users; 
 
    public UserAccountDBStub()
    { 
        users = new ArrayList<>(); 
        cities = new ArrayList<>();
        
        // Seed the database in memory (cities and users Lists)
        cities.add("London");
        cities.add("Manchester");
        cities.add("Birmingham");
        cities.add("Liverpool");
        cities.add("Edinburgh");
        cities.add("Glasgow");
        cities.add("Cardiff");
        cities.add("Belfast");
    } 
 
    public ArrayList<String> getCityNames() 
    { 
        return cities; 
    } 
 
    public boolean isAnExistingUser(User user) 
    { 
        // Check if username exists in the users List (case insensitive)
        String usernameToCheck = user.userName.toLowerCase();
        for (User existingUser : users) {
            if (existingUser.userName.toLowerCase().equals(usernameToCheck)) {
                return true;
            }
        }
        return false; 
    } 
 
    public boolean isRegisteredUser(User user) 
    { 
        // Check if username & password exists in the users List
        // Username is case insensitive, password is case sensitive
        String usernameToCheck = user.userName.toLowerCase();
        for (User existingUser : users) {
            if (existingUser.userName.toLowerCase().equals(usernameToCheck) && 
                existingUser.password.equals(user.password)) {
                return true;
            }
        }
        return false; 
    } 
 
    public boolean register(User user) throws InvalidUserDetailsException
    { 
        if (isAnExistingUser(user)) 
            return false; 
 
        // Add the user to the database in memory
        users.add(user);
        return true; 
    } 
} 
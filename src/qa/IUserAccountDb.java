package qa; 
 
import java.util.ArrayList; 
 
public interface IUserAccountDb 
{ 
    ArrayList<String> getCityNames(); 
 
    boolean register(User user) throws InvalidUserDetailsException; 
 
    boolean isRegisteredUser(User user); 
 
    boolean isAnExistingUser(User user); 
} 
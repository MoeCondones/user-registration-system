package qa; 
 
import java.util.ArrayList; 
 
public interface IUserAccountDb 
{ 
    boolean isRegistered(String username); 
 
    boolean register(User user) throws InvalidUserDetailsException; 
 
    User getRegisteredUser(String username); 
 
    String[] getCityNames(); 
} 
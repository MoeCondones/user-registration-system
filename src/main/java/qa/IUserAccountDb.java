package qa; 
 
import java.util.ArrayList; 
 
public interface IUserAccountDb 
{ 
    ArrayList<String> getCityNames(); 
 
    boolean register(User user); 
 
    boolean isRegisteredUser(User user); 
 
    boolean isAnExistingUser(User user); 
} 
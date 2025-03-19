package qa; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
public class Utils 
{ 
    public static boolean isAlphabetic(String inputString) 
    { 
        if (inputString == null)
            return false;
        return inputString.matches("[a-zA-Z]+"); 
    } 
    
    public static boolean isNumeric(String inputString) 
    { 
        if (inputString == null)
            return false;
        return inputString.matches("[0-9]+"); 
    } 
    
    public static boolean hasIllegalChars(String inputString) 
    { 
        if (inputString == null)
            return false;
            
        String[] badChars = { " ", "\t", "\n", "\r", "[", "]", "<", ">", "~", ";", "'", "@", "£", "$", "%", "?" }; 
        for (String s : badChars) 
        { 
            if (inputString.contains(s)) 
            { 
                return true; 
            } 
        } 
        return false; 
    } 
 
    public static boolean hasOneDigit(String inputString) 
    { 
        if (inputString == null)
            return false;
            
        for (char c : inputString.toCharArray()) 
        { 
            if (Character.isDigit(c)) 
                return true; 
        } 
        return false; 
    } 
 
    public static boolean hasExtraPasswordChars(String inputString) 
    { 
        if (inputString == null)
            return false;
            
        String[] extraChars = { "$", "£", "%", "@", "?" }; 
        for (String s : extraChars) 
        { 
            if (inputString.contains(s)) 
                return true; 
        } 
        return false; 
    } 
 
    public static boolean isValidUKPostCode(String postCode) 
    { 
        if (postCode == null)
            return false;
            
        String regex = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$"; 
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher(postCode.trim()); 
        return matcher.matches(); 
    } 
} 
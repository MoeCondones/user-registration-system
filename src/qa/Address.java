package qa; 
 
public class Address 
{ 
    IUserAccountDb userAccountDb; 
     
    public String number; 
    public String addressLine; 
    public String postCode; 
    public String city; 
 
    public Address(String number, String addressLine,  
     String postCode, String city,  
     IUserAccountDb userAccountDb) throws InvalidUKAddressException
    { 
        this.userAccountDb = userAccountDb;
        
        if (isValidHouseNumber(number))
            this.number = number;
        else
            throw new InvalidUKAddressException.InvalidHouseNumberException();
            
        if (addressLine == null)
            throw new InvalidUKAddressException("Address line cannot be null");
            
        this.addressLine = addressLine; 
        
        if (isValidCity(city))
            this.city = city; 
        else 
            throw new InvalidUKAddressException.InvalidCityException(); 
 
        if (Utils.isValidUKPostCode(postCode)) 
            this.postCode = postCode; 
        else 
            throw new InvalidUKAddressException.InvalidPostcodeException(); 
    } 
    
    private boolean isValidHouseNumber(String number) {
        if (number == null || number.isEmpty())
            return false;
            
        // House number can contain digits and dash only
        return number.matches("[0-9]+(-[0-9]+)?");
    }
     
    public boolean isValidCity(String city) 
    { 
        if (city == null)
            return false;
            
        return userAccountDb.getCityNames().contains(city); 
    } 
} 
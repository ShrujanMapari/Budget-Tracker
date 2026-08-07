
/**
 * Support class.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User
{
    // instance variables - replace the example below with your own
    
    private String password;
    
    private String username;
    /**
     * Constructor for objects of class User
     */
    public User(String nm, String pass){
        // log in 
        username = nm;
        password = pass;
    }

    // Getters
    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    // Setters (optional)
    public void setPassword(String newPassword)
    {
        password = newPassword;
    }

    //Override
    public String toString()
    {
        return username;
    }
}

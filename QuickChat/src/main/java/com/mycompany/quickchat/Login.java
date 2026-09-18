package com.mycompany.quickchat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * The Login class handles user registration and authentication
 * for the QuickChat application.
 *
 * @author Your Name (Student Number)
 */
public class Login {

    // ---------- Stored user details ----------
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String storedFirstName;
    private String storedLastName;

    // Methods will go below this line
    
    /**
     * Checks that the username contains an underscore and is
     * no more than five characters long.
     */
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    /**
     * Checks that the password:
     *  - is at least 8 characters long
     *  - contains a capital letter
     *  - contains a number
     *  - contains a special character
     *
     * @param password the password to validate
     * @return true if valid, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {
        boolean hasLength  = password.length() >= 8;
        boolean hasCapital = password.matches(".[A-Z].");
        boolean hasNumber  = password.matches(".[0-9].");
        boolean hasSpecial = password.matches(".[!@#$%^&(),.?\":{}|<>].*");
        return hasLength && hasCapital && hasNumber && hasSpecial;
    }
    
    /**
     * Checks that the cell phone number contains the international
     * country code (+27) followed by 9 digits.
     *
     * Regex reference:
     *   GeeksforGeeks. (2023). Java Regex - Pattern and Matcher classes.
     *   Retrieved from https://www.geeksforgeeks.org/regular-expressions-in-java/
     *
     * @param cellNumber the cell phone number entered by the user
     * @return true if the number is correctly formatted, false otherwise
     */
    public boolean checkCellPhoneNumber(String cellNumber) {
        Pattern pattern = Pattern.compile("^\\+27[0-9]{9}$");
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }
    
    /**
     * Verifies that the entered login details match the stored ones.
     *
     * @param username the username entered at login
     * @param password the password entered at login
     * @return true if the credentials match, false otherwise
     */
    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername)
            && password.equals(storedPassword);
    }
    
    /**
     * Returns a message based on whether the user's login was successful.
     *
     * @param loginSuccess the true/false value returned by loginUser()
     * @return a welcome message or an error message
     */
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + storedFirstName + ", " + storedLastName
                 + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
     
}
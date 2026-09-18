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
     * Registers a new user by validating their details and storing them
     * if all checks pass.
     *
     * @param username   the desired username
     * @param password   the desired password
     * @param cellNumber the SA cell phone number (with +27)
     * @param firstName  the user's first name
     * @param lastName   the user's last name
     * @return a message indicating success or the specific failure reason
     */
    public String registerUser(String username, String password,
                               String cellNumber, String firstName,
                               String lastName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your "
                 + "username contains an underscore and is no more than five "
                 + "characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the "
                 + "password contains at least eight characters, a capital letter, "
                 + "a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain "
                 + "international code.";
        }

        // All checks passed — store the details
        this.storedUsername   = username;
        this.storedPassword   = password;
        this.storedCellNumber = cellNumber;
        this.storedFirstName  = firstName;
        this.storedLastName   = lastName;

        return "Username successfully captured.\n"
             + "Password successfully captured.\n"
             + "Cell phone number successfully added.";
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
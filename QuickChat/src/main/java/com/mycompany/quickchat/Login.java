package com.mycompany.quickchat;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String storedFirstName;
    private String storedLastName;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasLength  = password.length() >= 8;
        boolean hasCapital = password.matches(".[A-Z].");
        boolean hasNumber  = password.matches(".[0-9].");
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");
        return hasLength && hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber.matches("^\\+27[0-9]{9}$");
    }

    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        this.storedUsername   = username;
        this.storedPassword   = password;
        this.storedCellNumber = cellNumber;
        this.storedFirstName  = firstName;
        this.storedLastName   = lastName;
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + storedFirstName + ", " + storedLastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
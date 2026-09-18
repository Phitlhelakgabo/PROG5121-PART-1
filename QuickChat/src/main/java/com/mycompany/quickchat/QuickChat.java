/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;
 
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class QuickChat {

    public static void main(String[] args) {
             Scanner input = new Scanner(System.in);
        Login login = new Login();

        // ---------- REGISTRATION ----------
        System.out.println("=== REGISTRATION ===");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter SA cell phone number (+27...): ");
        String cellNumber = input.nextLine();

        String registrationMessage = login.registerUser(
                username, password, cellNumber, firstName, lastName);
        System.out.println(registrationMessage);

        // ---------- LOGIN ----------
        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        boolean success = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(success));

        input.close();
    }
}
    


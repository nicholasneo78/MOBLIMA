package MOBLIMA;

import MOBLIMA.Model.LoginApp;
import MOBLIMA.Model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Main class for the application.
 * <br> Displays the landing page of the application.
 * <br> Calls on Login App to authenticate the user.
 *@author SE3-grp1
 *@version 1.0
 *@since 2019-11-15
 */
public class MOBLIMA {
    public static void main(String args[]) throws Exception {
        int[] loginResult;
        int isAdmin;
        int userID;
        int choice = 0;
        boolean run = true;
        do {
            try {
                LoginApp login = new LoginApp();
                Scanner sc = new Scanner(System.in);
                System.out.println("================================================================");
                System.out.println("MOBLIMA                            ");
                System.out.println("================================================================");
                System.out.println("Welcome to MOBLIMA");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("Enter your choice:");
                choice = sc.nextInt();

                if (choice == 1) {
                    login.register();

                } else if (choice == 2) {
                    loginResult = login.login();
                    isAdmin = loginResult[0];
                    userID = loginResult[1];
                    User user = User.createUser(userID, isAdmin);
                    user.printName();
                    user.view();
                    run = false;
                } else {
                    System.out.println("Invalid Input!");
                }
            } catch (InputMismatchException | NullPointerException e) {
                System.out.println("Invalid Input!");
            }
        } while (run);
    }
}


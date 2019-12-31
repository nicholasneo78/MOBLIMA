package MOBLIMA.View;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents what the customers will see after login into their account.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class CustomerMenuView {
    /**
     * To display customers the main menu with the options that they can choose to move on
     * to their desired purpose afterwards.
     * @return This Customer's choice
     */
    public static int show() {
        int choice = 0;
        do {
            try {

                Scanner sc = new Scanner(System.in);
                System.out.println("================================================================");
                System.out.println("                            MENU                            ");
                System.out.println("================================================================");
                System.out.println("What would you like to do today?");
                System.out.println("1. View Booking History");
                System.out.println("2. View movies");//have listmovie,rate/review movie, check seat availability, purchase ticket
                System.out.println("3. Search movies");
                System.out.println("4. Top 5 movies by ratings");
                System.out.println("5. Exit");
                System.out.println("Enter your choice:");
                choice = sc.nextInt();
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid Input!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!");
            } catch (NullPointerException e) {
                System.out.println("Invalid Input!");
            }
        } while (choice < 1 || choice > 5);
        return choice;
    }
}


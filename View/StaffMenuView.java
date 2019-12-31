package MOBLIMA.View;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents what the admins will see after login into their account.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class StaffMenuView {
    /**
     * To display admins the main menu with the options that they can choose to move on
     * to their desired purpose afterwards.
     * @return This Admin's choice.
     */
    public static int show() {

        int choice = 0;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("================================================================");
            System.out.println("                            MENU                            ");
            System.out.println("================================================================");
            System.out.println("What would you like to do today?");
            System.out.println("1. View Movies");
            System.out.println("2. Add Movie");//have listmovie,rate/review movie, check seat availability, purchase ticket
            System.out.println("3. Top 5 movies by ratings");
            System.out.println("4. Check and update discounts");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid Input!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        } catch (NullPointerException e) {
            System.out.println("Invalid input!");
        }
        return choice;
    }
}

package MOBLIMA.View;

import MOBLIMA.Controller.CustomerController;
import MOBLIMA.Controller.MovieController;
import MOBLIMA.Model.Customer;
import MOBLIMA.Model.Movie;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents what the admins will see after they make their choice in the class CustomerMenuView.java.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class CustomerView {
    /**
     * To display customers the next menu after the customers input their options
     * (precedes from the menu from class CustomerMenuView.java).
     * @param customer This object customer of class Customer.java with all his/her personal particulars inside.
     */
    public static void show(Customer customer) {
        int choice, option;
        boolean run = true;
        MovieController movieController = new MovieController();
        CustomerController customerController = new CustomerController();
        while (run) {
            choice = CustomerMenuView.show();
            Scanner sc = new Scanner(System.in);
            Scanner scString = new Scanner(System.in);
            try {
                switch (choice) {
                    case 1:
                        customer.bookingHistory();
                        break;
                    case 2:
                        customerController.bookMovie(-1, customer, null);
                        break;
                    case 3:
                        System.out.println("Please enter the name of the movie you want to find: ");
                        String movieName = scString.nextLine();
                        boolean found;
                        Movie m = new Movie();
                        found = customerController.findMovie(movieName);
                        while (!found) {
                            System.out.println("(1) Re-enter movie name");
                            System.out.println("(0) Go back to menu");
                            option = sc.nextInt();
                            if (option == 1) {
                                System.out.println("Please enter the name of the movie you want to find: ");
                                movieName = scString.nextLine();
                                found = customerController.findMovie(movieName);
                            } else if (option == 0) {
                                break;
                            } else {
                                System.out.println("Invalid choice. Exit to menu");
                                break;
                            }
                        }
                        if (found) {
                            System.out.println("Enter corresponding number to choose movie or 0 to exit");
                            option = sc.nextInt();
                            if (option != 0) {
                                int movieID = MovieController.listFoundMovies(option, movieName);
                                m.setMovie(movieID);
                                if (m.getName() == null) {
                                    System.out.println("Invalid input!");
                                    break;
                                } else {
                                    m.getMovieInfo();
                                    System.out.println("(1) Book the movie");
                                    System.out.println("(2) Review and Rate the movie");
                                    System.out.println("(0) Exit back to menu");
                                    option = sc.nextInt();
                                    if (option == 1)
                                        customerController.bookMovie(1, customer, m.getMovieID());
                                    else if (option == 2)
                                        customerController.bookMovie(2, customer, m.getMovieID());
                                    else if (option == 0)
                                        break;
                                    else {
                                        System.out.println("Invalid choice. Exit to menu.");
                                        break;
                                    }
                                }
                            } else
                                break;
                        }
                        break;
                    case 4:
                        movieController.returnTopMovies();
                        break;
                    case 5:
                        System.out.println("Thank you for using MOBLIMA! Have a good day");
                        run = false;
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            } catch (NullPointerException e) {
                System.out.println("Invalid input");
            }
        }
    }
}

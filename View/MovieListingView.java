package MOBLIMA.View;

import MOBLIMA.Controller.MovieController;
import MOBLIMA.Model.Movie;

import java.util.Scanner;
/**
 * Represents what the Customers will see after they make the choice of booking a movie (option 2 from
 * the class CustomerView.java).
 * Also, it represents what the Admin will see after they make the choice to view a movie (option 1 from
 * the class StaffView.java).
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class MovieListingView {
    /**
     * To display customers the next menu of the movie listing after they choose to
     * book a movie from the class CustomerView.java.
     * To display Admin the next menu of the movie listing after they choose to view a movie
     * from the class StaffView.java.
     * @param admin This to show whether is this the admin or customers accessing this movie listing,
     *              thereby showing a different display menu for these two groups of people.
     * @return An integer array representing a pair of integer whose first element is the user choice to proceed after
     * seeing the movie listing, second element is the movie ID.
     */
    public static int[] show(int admin) {
        System.out.println("================================================================");
        System.out.println("                        MOVIE LISTING                           ");
        System.out.println("================================================================");
        System.out.println("List of movies below");
        MovieController.listMovies(0, admin); //Show all movies available
        System.out.println("Enter corresponding number to view more about movie");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        int movieID = MovieController.listMovies(option, admin); //List specific movie
        Movie m = new Movie();
        m.setMovie(movieID);
        if (m.getName() != null) {

            m.getMovieInfo();
            if (admin == 0) {
                System.out.println();
                System.out.println("(1) Book the movie");
                System.out.println("(2) Review and Rate the movie");
                System.out.println("(0) Exit back to menu");
                option = sc.nextInt();
                while (option < 0 || option > 2) {
                    System.out.println("Invalid choice! Please re-enter your choice.");
                    System.out.println("(1) Book the movie");
                    System.out.println("(2) Review and Rate the movie");
                    System.out.println("(0) Exit back to menu");
                    option = sc.nextInt();
                }
            } else {
                System.out.println();
                System.out.println("(1) Edit the movie");
                System.out.println("(2) Add showtime");
                System.out.println("(3) Remove showtime");
                System.out.println("(0) Exit back to menu");
                option = sc.nextInt();
                while (option < 0 || option > 3) {
                    System.out.println("Invalid choice! Please re-enter your choice.");
                    System.out.println("(1) Edit the movie");
                    System.out.println("(2) Add showtime");
                    System.out.println("(3) Remove showtime");
                    System.out.println("(0) Exit back to menu");
                    option = sc.nextInt();
                }
            }

            int[] result = {option, movieID};
            return result;
        } else {
            System.out.println("Invalid input!");
        }
        return null;
    }

}

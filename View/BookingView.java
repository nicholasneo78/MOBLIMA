package MOBLIMA.View;

import MOBLIMA.Model.MovieListing;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents what the customers will see on the screen when they want to book their movie tickets.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class BookingView {
    /**
     * To display customers the whole list of the movies available and to prompt the customers to choose the
     * cineplex, movie, date of their choice.
     * @return This Customer's choice.
     */
    public static int show(){
        int option;
        ArrayList<String> dates;
        MovieListing ml= new MovieListing();
        dates = ml.returnDate();
        int count = 0;
        System.out.println("Enter the date you want to choose: ");
        for (String s : dates) {
            System.out.print((count+1) + ". ");
            System.out.println(s);
            count++;
        }
        Scanner sc = new Scanner(System.in);
        option = sc.nextInt();
        return option;
    }
}

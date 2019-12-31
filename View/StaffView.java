package MOBLIMA.View;

import MOBLIMA.Controller.MovieController;
import MOBLIMA.Controller.StaffController;
import MOBLIMA.Model.Cineplex;
import MOBLIMA.Model.Movie;
import MOBLIMA.Model.Staff;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Represents what the admins will see after they make their choice in the class StaffMenuView.java.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class StaffView {
    /**
     * To display admins the next menu after the admins input their options
     * (precedes from the menu from class StaffMenuView.java).
     * @param staff This object staff of class Staff.java with all his/her personal particulars inside.
     */
    public static void show(Staff staff){
        int choice, option;
        MovieController movieController = new MovieController();
        StaffController staffController = new StaffController();
        Scanner sc = new Scanner(System.in);
        String location;
        boolean run = true;
        while(run){
            choice = StaffMenuView.show();
            try{
            switch(choice){
                case 1:
                    staffController.editMovie();
                    break;
                case 2:
                    Movie m = new Movie();
                    m.addMovie();
                    break;
                case 3:
                    movieController.returnTopMovies();
                    break;
                case 4:
                    Cineplex cplex=new Cineplex(0);//filler id
                    cplex.viewDiscount();
                    System.out.println("Enter location to edit or 0 to exit");
                    location=sc.nextLine();
                    cplex.editDiscount(location);
                    break;
                case 5:
                    System.out.println("Bye");
                    run = false;
                    break;
            }} catch (InputMismatchException e){
                System.out.println("Invalid input!");
            } catch(NullPointerException e){
                System.out.println("Invalid input!");
            }
        }
    }
}

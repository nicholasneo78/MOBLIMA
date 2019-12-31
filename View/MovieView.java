package MOBLIMA.View;

import MOBLIMA.Model.Cineplex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Represents the CSV read from MovieListing.csv to retrieve the movie data
 * that is to be displayed to the users (Admins and Customers) in the class MovieListingView.java
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class MovieView {
    /**
     * To read the CSV data from MovieListing.csv and display only what the user chose in the terminal
     * based on the movie and the date.
     * @param movieID This user's option of movie.
     * @param date This user's option of date.
     */
    public static void show(int movieID, String date) {
        BufferedReader br = null;
        String line = "";
        try {

            br = new BufferedReader(new FileReader("MOBLIMA/Data/MovieListing.csv"));
            br.readLine();
            int count=1;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] movie = line.split(",");
                if(Integer.valueOf(movie[0])==movieID && movie[7].compareTo(date) == 0) {
                    String movieTitle=movie[1];
                    int movieTiming = Integer.parseInt(movie[2]);
                    int cineplexID = Integer.parseInt(movie[4]);
                    Cineplex c=new Cineplex(cineplexID);
                    String cineplexLocation=c.getCineplexLocation();
                    int cinemaID = Integer.parseInt(movie[5]);
                    date = movie[7];
                    int holiday = Integer.parseInt(movie[8]);
                    String cinemaClass = movie[9];
                    System.out.print(count+". ");
                    System.out.print("Movie: "+movieTitle);
                    System.out.print("\t");
                    System.out.print("Cineplex: "+cineplexLocation);
                    System.out.print("\t");
                    System.out.println("Cinema Type: "+cinemaClass);
                    System.out.print("\t\t");
                    System.out.print("Timing: "+movieTiming);
                    System.out.print("\t");
                    System.out.print("Date: "+ date);
                    System.out.println();
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }
        }
    }
}

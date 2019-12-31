package MOBLIMA.Controller;

import MOBLIMA.Model.Movie;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents Movie Controller, which controls operations that are related to listing movies or getting movie ID.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */

public class MovieController {
    /**
     * Lists all movies in database if user is a staff.
     * Only shows movies with "Now Showing" or "Coming Soon" status if user is a customer.
     * @param option Index of the movie that the user wants to see.
     * @param admin Admin privilege, 1 if user is a staff, otherwise 0.
     * @return Movie ID. If movie does not exist, return 0.
     */
    public static int listMovies(int option, int admin) {
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("MOBLIMA/Data/moviedata.csv"));
            br.readLine();
            int count = 1;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] movies = line.split(",");
                if (option == 0) {
                    if (movies[6].compareTo("Closed") != 0 || admin == 1) {
                        System.out.println(count + ". Movie: " + String.format("%-30s",movies[1]) + "\t Status:" + movies[6]);
                    } else {
                        if (admin == 0) count--;
                    }
                } else {
                    if (movies[6].compareTo("Closed") == 0) {
                        if (admin == 0) count--;
                    }
                    if (count == option) {
                        return Integer.valueOf(movies[0]);
                    }
                }
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    /**
     * Get the movie ID of the movie found when user looks it up by name. Excludes movies whose status is "Closed".
     * @param option Index of the movie found result.
     * @param nameFound The movie name that the user inputs.
     * @return An integer value for the ID of the movie. By default return 0.
     */
    public static int listFoundMovies(int option, String nameFound){
        BufferedReader br = null;
        String line = "";
        try{
            br = new BufferedReader(new FileReader("MOBLIMA/Data/moviedata.csv"));
            br.readLine();
            int count = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] movies = line.split(",");
                if(movies[1].toLowerCase().contains(nameFound.toLowerCase()) && !movies[6].equals("Closed")){
                    count++;
                    if(count == option){
                        return Integer.valueOf(movies[0]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    /**
     * Lists top 5 movies by rating. Returns only those with a rating if there are fewer than 5 of such.
     */
    public void returnTopMovies(){
        BufferedReader br = null;
        String line = "";
        ArrayList<Movie> movieList = new ArrayList<>();
        int count = 0;
        int movieID;
        try {
            br = new BufferedReader(new FileReader("MOBLIMA/Data/moviedata.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] movies = line.split(",");
                if(movies[6].equalsIgnoreCase("Now Showing")){
                    movieID = Integer.valueOf(movies[0]);
                    Movie m = new Movie();
                    m.setMovie(movieID);
                    movieList.add(m);
                }
            }
            System.out.println("Top 5 movies by ratings are: ");
            Collections.sort(movieList, Movie.RatingComparator);
            for(Movie movie: movieList){
                System.out.println((count+1) +". " + movie.getName() + " - Rating: "+movie.getOverallRating());
                count ++;
                if (count == 5)
                    break;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

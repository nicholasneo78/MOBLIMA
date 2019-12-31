package MOBLIMA.Model;

import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 * Represents a Movie.
 * Each cinema may have multiple movies.
 *
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class Movie {

    /**
     * Title of movie.
     */
    private String movieTitle;

    /**
     * ID of movie.
     */
    private int movieID;

    /**
     * Movie Type: 2D or 3D.
     */
    private String movieTypes;

    /**
     * Synopsis of movie.
     */
    private String synopsis;

    /**
     * Director of movie.
     */
    private String director;

    /**
     * Cast of movie.
     */
    private String[] cast;

    /**
     * Showing status of movie.
     */
    private String status;

    /**
     * Overall rating of movie (average rating).
     */
    private int overallRating;

    /**
     * List of user reviews.
     */
    private String[] pastReview;

    /**
     * List of user ratings.
     */
    private String[] pastRating;

    /**
     * Counter for number of movies.
     */
    private int moviecount = 0;

    /**
     * Set the movie information of selected movie ID.
     *
     * @param movieid ID of selected movie.
     */
    public void setMovie(int movieid) {
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("MOBLIMA/Data/moviedata.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                moviecount++;
                // use comma as separator
                String[] movie = line.split(",");
                if (Integer.valueOf(movie[0]) == movieid) {
                    movieID = Integer.valueOf(movieid);
                    movieTitle = movie[1];
                    movieTypes = movie[2];
                    synopsis = movie[3];
                    director = movie[4];
                    cast = movie[5].split("%");
                    status = movie[6];
                    overallRating = Integer.valueOf(movie[7]);
                    pastReview = movie[8].split("%");
                    pastRating = movie[9].split("%");
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
    }

    /**
     * Add new movie into CSV.
     */
    public void addMovie() {
        setMovie(0);
        BufferedWriter bw = null;
        FileWriter fw = null;
        Scanner sc = new Scanner(System.in);
        String movieTitle;
        int movieID;
        String movieTypes;
        String synopsis;
        String director;
        String[] cast;
        String status;
        int overallRating;
        String[] pastReview;
        String[] pastRating;
        System.out.println("New movie title:");
        movieTitle = sc.nextLine();
        movieID = moviecount + 1;
        System.out.println("Movie type:");
        movieTypes = sc.nextLine();
        System.out.println("Movie synopsis:");
        synopsis = sc.nextLine();
        System.out.println("Movie director:");
        director = sc.nextLine();
        System.out.println("Movie cast (separate using %):");
        cast = sc.nextLine().split("%");
        System.out.println("Movie status:");
        status = sc.nextLine();
        overallRating = -1;
        pastReview = null;
        pastRating = null;
        try {
            fw = new FileWriter("MOBLIMA/Data/moviedata.csv", true);
            fw.append(movieID + "," + movieTitle + "," + movieTypes + "," + synopsis + "," + director + ",");
            int count = 0;
            while (count < cast.length) {
                fw.append(cast[count]);
                count++;
                if (count < cast.length) fw.append("%");

            }
            fw.append(",");
            fw.append(status + "," + overallRating + ",");
            fw.append(" ");
            fw.append(",");
            fw.append(" ");
            fw.append("\n");
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }

    }

    /**
     * Display information of movie.
     */
    public void getMovieInfo() {
        int i = 1;
        System.out.println("================================================================");
        System.out.println("                        MOVIE INFO                           ");
        System.out.println("================================================================");
        System.out.print("Movie Title: " + movieTitle);
        System.out.print("\t");
        System.out.println("Movie Type: " + movieTypes);
        System.out.print("Director: " + director);
        System.out.print("\t");
        System.out.print("Cast: ");
        System.out.print(cast[0]);
        while (cast.length > i) {
            System.out.print(", " + cast[i]);
            i++;
        }
        System.out.println();
        System.out.println("Synopsis: " + synopsis);
        i = 0;
        System.out.println();
        if (overallRating < 0) {
            System.out.println("Overall Rating: nil");
        } else {
            System.out.println("Overall Rating: " + overallRating);
        }
        System.out.println("Reviews-Ratings: ");
        while (pastReview.length > i) {
            System.out.println(pastReview[i] + "-" + pastRating[i]);
            i++;
        }
    }

    /**
     * Get title of movie.
     *
     * @return Title of movie.
     */
    public String getName() {
        return movieTitle;
    }

    /**
     * Get status of movie.
     *
     * @return Status of movie.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get movie type.
     *
     * @return Movie type.
     */
    public String getMovieType() {
        return movieTypes;
    }

    /**
     * Get ID of movie.
     *
     * @return ID of movie.
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Get overall rating of movie.
     *
     * @return Overall rating of movie.
     */
    public int getOverallRating() {
        return overallRating;
    }

    /**
     * Compares the overall rating of the movies.
     */
    public static Comparator<Movie> RatingComparator = (o1, o2) -> {
        int rating1 = o1.getOverallRating();
        int rating2 = o2.getOverallRating();
        return rating2 - rating1;
    };
}


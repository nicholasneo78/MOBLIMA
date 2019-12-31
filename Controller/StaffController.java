package MOBLIMA.Controller;

import MOBLIMA.Model.Movie;
import MOBLIMA.Model.MovieListing;
import MOBLIMA.View.BookingView;
import MOBLIMA.View.MovieListingView;
import MOBLIMA.View.MovieView;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the Staff Controller, which handles the logic flow, data handling and interactions between Staff objects
 * and Staff View.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class StaffController {
    /**
     * Integer value for the ID of a movie.
     */
    private int movieID;

    /**
     * Display a panel for the staff to edit the information of a movie that is identified by a movieID.
     * @param movieid The ID of the movie.
     */
    public void editMovieInfo(int movieid) {
        int select;
        String temp;
        Scanner sc = new Scanner(System.in);
        FileWriter writer = null;
        BufferedReader br = null;
        String line = "";
        ArrayList<String> result = new ArrayList<>();
        Movie m = new Movie();
        m.setMovie(movieid);
        try {
            do {
                System.out.println("Enter corresponding number to reenter item of " + m.getName() + ":");
                System.out.println("1.Movie Title");
                System.out.println("2.Movie Type");
                System.out.println("3.Status");
                System.out.println("4.Synopsis");
                System.out.println("5.Director");
                System.out.println("6.Cast");
                select = sc.nextInt();
                sc.nextLine();
            } while (select < 1 || select > 6);
            System.out.println("Enter new value:");
            temp = sc.nextLine();

            int count = 0;
            br = new BufferedReader(new FileReader("MOBLIMA/Data/moviedata.csv"));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] movie = line.split(",");
                if (count == 0) {
                    result.add(line);
                    count++;
                } else {
                    if (Integer.valueOf(movie[0]) == movieid) {
                        switch (select) {
                            case 1:
                                movie[1] = temp;
                                break;
                            case 2:
                                movie[2] = temp;
                                break;
                            case 3:
                                movie[6] = temp;
                                break;
                            case 4:
                                movie[3] = temp;
                                break;
                            case 5:
                                movie[4] = temp;
                                break;
                            case 6:
                                movie[5] = temp;
                                break;
                        }
                    }
                    result.add("\n");
                    result.add(String.join(",", movie));
                }
            }
            writer = new FileWriter("MOBLIMA/Data/moviedata.csv");
            for(String data: result){
                writer.append(data);
            }
            System.out.println("Movie info is updated!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Allows the staff to add a new show time for a movie.
     * @param m The Movie object representing the chosen movie.
     */
    public void addListing(Movie m) {

        BufferedWriter bw = null;
        FileWriter fw = null;
        Scanner sc = new Scanner(System.in);
        String timeslot;
        String date;
        String cineplexID;
        String cinemaID;
        String holiday;
        String cinemaClass;

        System.out.println("New time slot:");
        timeslot = sc.nextLine();

        System.out.println("New Date:");
        date = sc.nextLine();
        System.out.println("Cineplex id:");
        cineplexID = sc.nextLine();
        System.out.println("Cinema id:");
        cinemaID = sc.nextLine();
        System.out.println("Holiday satus(1/0):");
        holiday = sc.nextLine();
        System.out.println("Cinema class:");
        cinemaClass = sc.nextLine();
        try {
            fw = new FileWriter("MOBLIMA/Data/MovieListing.csv", true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(m.getMovieID()) + "," + m.getName() + "," + timeslot + "," + m.getStatus() + "," + cineplexID + "," + cinemaID + "," + "00000x0000000000x0000000000x0000000000x0000000000x00000," + date + "," + holiday + "," + cinemaClass);

            bw.write("\n");
            System.out.println("Movie Lisiting added successfully!");
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();

            }
        }
    }

    /**
     * Shows the Staff Edit menu as well as handles the logic flow of the editing process.
     *
     * <br>(1) Edit the information of the movie.
     *
     * <br>(2) Add a new show time for the movie.
     *
     * <br>(3) Remove a show time for the movie.
     *
     * <br>(0) Exit to menu.
     */
    public void editMovie() {
        int[] result = MovieListingView.show(1);
        movieID = result[1];
        int choice = result[0];
        switch (choice) {
            case 0:
                break;
            case 1:
                Movie m = new Movie();
                m.setMovie(movieID);
                editMovieInfo(movieID);
                break;
            case 2:
                Movie mo = new Movie();
                mo.setMovie(movieID);
                addListing(mo);
                break;
            case 3:
                Movie mov = new Movie();
                mov.setMovie(movieID);
                removeShowtime(mov.getMovieID());
                break;
        }
    }

    /**
     * Allows the staff to remove a show time for a chosen movie.
     * @param movieID The ID of the movie.
     */

    public void removeShowtime(int movieID) {
        FileWriter writer = null;
        BufferedReader br = null;
        String line = "";
        int showTime;
        int cinemaID;

        int option = BookingView.show();
        CustomerController customerController = new CustomerController();
        MovieListing ml = new MovieListing();
        ArrayList<String> dates = ml.returnDate();
        ArrayList<String> newData = new ArrayList<String>();
        String date = dates.get(option - 1);
        MovieView.show(movieID, date);
        ml = customerController.pickMovie(movieID, date);
        if (ml == null)
            return;
        else {
            try {
                showTime = ml.getShowTime();
                cinemaID = ml.getCinemaID();

                br = new BufferedReader(new FileReader("MOBLIMA/Data/MovieListing.csv"));
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] movies = line.split(",");
                    if (Integer.valueOf(movies[0]) == movieID && movies[7].equals(date) && Integer.valueOf(movies[2]) == showTime && Integer.valueOf(movies[5]) == cinemaID) {
                        continue;
                    } else {
                        newData.add(line);
                    }
                }
                writer = new FileWriter("MOBLIMA/Data/MovieListing.csv");
                writer.append("movieID,movieTitle,timeSlot,showingStatus,cineplexID,cinemaID,seatAvailabilty,Date,Holiday,classOfCinema");
                writer.append("\n");
                for (String data : newData) {
                    writer.append(data);
                    writer.append("\n");
                }

                System.out.println("Remove showtime successfully!");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


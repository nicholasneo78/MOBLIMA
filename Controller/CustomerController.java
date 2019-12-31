package MOBLIMA.Controller;

import MOBLIMA.Model.*;
import MOBLIMA.View.BookingView;
import MOBLIMA.View.MovieListingView;
import MOBLIMA.View.MovieView;

import java.io.*;
import java.util.*;

/**
 * Represents the Customer Controller, which handles the logic flow, data handling and interactions between Customer
 * and Customer View.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class CustomerController {
    /**
     * Title of movie.
     */
    private String movieTitle;
    /**
     * ID of movie.
     */
    private int movieID;
    /**
     * Timing of movie.
     */
    private int movieTiming;
    /**
     * ID of cineplex.
     */
    private int cineplexID;
    /**
     * Location of cineplex.
     */
    private String cineplexLocation;
    /**
     * Class of cinema.
     */
    private String cinemaClass;
    /**
     * ID of cinema.
     */
    private int cinemaID;
    /**
     * 1 if show time falls on a holiday, 0 otherwise.
     */
    private int holiday;

    /**
     * Picks the movie that the user wants to book tickets.
     * @param movieID The ID of movie.
     * @param date The date that the user wants to book.
     * @return MovieListing object that has information of the chosen movie, the booking date and timeslot.
     * <br> If MovieListing object does not exist, returns null.
     */
    public MovieListing pickMovie(int movieID, String date) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Enter choice or 0 to go back to main:");
        choice = sc.nextInt();
        if (choice == 0) {
            return null;
        }
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("MOBLIMA/Data/MovieListing.csv"));
            br.readLine();
            int count = 0;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] movie = line.split(",");
                if (Integer.valueOf(movie[0]) == movieID && movie[7].compareTo(date) == 0) {
                    movieTitle = movie[1];
                    movieTiming = Integer.parseInt(movie[2]);
                    cineplexID = Integer.parseInt(movie[4]);
                    cinemaID = Integer.parseInt(movie[5]);
                    date = movie[7];
                    holiday = Integer.parseInt(movie[8]);
                    cinemaClass = movie[9];
                    Cineplex cineplex = new Cineplex(cineplexID);
                    cineplexLocation = cineplex.getCineplexLocation();
                    count++;
                    if (count == choice) {
                        MovieListing ml = new MovieListing(movieTitle, movieTiming, cineplexID, cineplexLocation, cinemaID, cinemaClass, holiday);
                        return ml;
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
        return null;
    }

    /**
     * Allows the user to rate and review the movie that the user has booked.
     * @param movieid The ID of the movie.
     * @param c The Customer object represents the user.
     */
    public void rateAndReview(int movieid, Customer c) {
        String review;
        int newOverall;
        int rating = 0;
        int i = 0;
        int total = 0;
        String[] pastRating;
        BufferedReader brCustomer = null;
        String lineCustomer = "";
        String[] bookingHistory = {""};
        Scanner sc = new Scanner(System.in);
        Scanner scString = new Scanner(System.in);

        try {
            brCustomer = new BufferedReader(new FileReader("MOBLIMA/Data/userdata.csv"));
            brCustomer.readLine();
            while ((lineCustomer = brCustomer.readLine()) != null) {
                String[] users = lineCustomer.split(",");
                if (Integer.valueOf(users[0]) == c.getUserID()) {
                    bookingHistory = users[7].split("-");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (brCustomer != null) {
                try {
                    brCustomer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Movie m = new Movie();
        m.setMovie(movieid);
        List<String> bookingList = Arrays.asList(bookingHistory);
        if (bookingList.contains(m.getName())) {

            boolean ratingFlag = true;
            do {System.out.println("Enter Review:");
                review = scString.nextLine();
                System.out.println("Enter Rating (Integer only):");
                try {
                    rating = sc.nextInt();
                    if (rating < 0 || rating > 5)
                        System.out.println("Enter Rating between 0 to 5: ");
                    else
                        ratingFlag = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input!");
                    sc.next();
                } catch (NullPointerException e) {
                    System.out.println("Invalid input!");
                    sc.next();
                }
            } while (ratingFlag == true);


            FileWriter writer = null;
            BufferedReader br = null;
            String line = "";
            int count = 0;
            try {
                writer = new FileWriter("MOBLIMA/MOBLIMA/Data/moviedatat.csv");
                br = new BufferedReader(new FileReader("MOBLIMA/Data/moviedata.csv"));
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] movie = line.split(",");
                    if (count == 0) {
                        writer.append(line);
                        writer.append("\n");
                        count++;
                    } else {
                        if (Integer.valueOf(movie[0]) == movieid) {
                            writer.append(movie[0]);
                            writer.append(",");
                            writer.append(movie[1]);
                            writer.append(",");
                            writer.append(movie[2]);
                            writer.append(",");
                            writer.append(movie[3]);
                            writer.append(",");
                            writer.append(movie[4]);
                            writer.append(",");
                            writer.append(movie[5]);
                            writer.append(",");
                            writer.append(movie[6]);
                            writer.append(",");
                            pastRating = movie[9].split("%");
                            while (pastRating.length > i) {
                                total += Integer.valueOf(pastRating[i]);
                                i++;
                            }
                            total += rating;
                            i++;
                            newOverall = total / (i);
                            writer.append(String.valueOf(newOverall));
                            writer.append(",");
                            writer.append(movie[8] + "%" + review);
                            writer.append(",");
                            writer.append(movie[9] + "%" + rating);
                            writer.append("\n");
                        } else {
                            writer.append(line);
                            writer.append("\n");
                        }
                    }
                }
                System.out.println("Rate and Review successfully!");
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
            File file = new File("MOBLIMA/Data/moviedata.csv");
            file.delete();

            File oldfile = new File("MOBLIMA/Data/moviedatat.csv");
            File newfile = new File("MOBLIMA/Data/moviedata.csv");

            oldfile.renameTo(newfile);
        } else {
            System.out.println("Cannot review or rate movie that you have not booked");
        }
    }

    /**
     * Shows and handles the booking process of the chosen movie.
     * @param choice Customer's input of what to proceed after choosing the movie.
     *             <br>  (1) Customer wants to book the movie.
     *             <br>  (2) Customer wants to rate and review the movie.
     *              <br> (0) Exit back to menu.
     * @param c Customer object that represents the user.
     * @param movieid The ID of the movie.
     */
    public void bookMovie(int choice, Customer c, int... movieid) {
        ArrayList<String> dates;
        MovieListing ml = new MovieListing();
        dates = ml.returnDate();
        Scanner sc = new Scanner(System.in);
        int[] result;
        int option;
        char[][] oldSeats;
        char[][] newSeats;
        boolean quit;

        try {
            if (choice == -1) {
                result = MovieListingView.show(0);
                if (result != null) {
                    movieID = result[1];
                    choice = result[0];
                } else
                    return;
            }

            if (movieid != null)
                movieID = movieid[0];

            switch (choice) {
                case 0:
                    break;
                case 1:
                    Movie m = new Movie();
                    m.setMovie(movieID);
                    if (m.getStatus().compareTo("Now Showing") != 0)
                        System.out.println("Movie is not currently available to book");
                    else {
                        option = BookingView.show();
                        String date = dates.get(option - 1);
                        MovieView.show(movieID, date);
                        ml = pickMovie(movieID, date);
                        if (ml == null)
                            break;
                        else {
                            Cinema cinema = new Cinema(ml.getCinemaID());
                            oldSeats = ml.returnSeat(ml.getCinemaID(), ml.getShowTime(), date);
                            //cinema.printSeat1D(oldSeats);
                            //cinema.printSeat1D(oldSeats);
                            System.out.println("Enter the number of tickets you want to buy: ");
                            int no = sc.nextInt();
                            quit = cinema.userChooseSeat(ml.getShowTime(), date, no);
                            if (quit)
                                break;
                            else {
                                Ticket t = new Ticket(ml, m.getMovieType(), cinema);
                                Cineplex cplex = new Cineplex(ml.getCinemaID());
                                t.setTypeQuantity();
                                t.printTicket();
                                System.out.println("Please confirm your ticket purchase");
                                System.out.println("1. Yes");
                                System.out.println("0. No");
                                int purchase = sc.nextInt();
                                if (purchase == 1) {
                                    Transaction tran = new Transaction(c.getUserName(), c.getUserMobileNo(), c.getUserEmail(), t);
                                    try {
                                        tran.recordTransaction();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    c.updateHistory(ml);
                                    System.out.println("Booking is confirmed");
                                    break;
                                } else if (purchase == 0) {
                                    cinema.setSeatLayoutInfoToCsv(ml.getCinemaID(), ml.getShowTime(), date, oldSeats);
                                    //cinema.printSeat1D(oldSeats);
                                    //cinema.printSeat1D(cinema.getSeatLayoutInfoFromCsv(ml.getCinemaID(), ml.getShowTime(), date));
                                    System.out.println("Booking is cancelled");
                                    break;
                                } else {
                                    System.out.println("Invalid input");
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    rateAndReview(movieID, c);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        } catch (NullPointerException e) {
            System.out.println("Invalid input!");
        } catch(IndexOutOfBoundsException e){
            System.out.println("Invalid input!");
        }
    }

    /**
     * The user searches for movie using by name.
     * @param nameToFind The name that the user enters.
     * @return Boolean value that is True if the movie is found, False otherwise.
     */
    public boolean findMovie(String nameToFind) {
        BufferedReader br = null;
        boolean found = false;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("MOBLIMA/Data/moviedata.csv"));
            br.readLine();
            int count = 1;
            System.out.println("Search result: ");
            while ((line = br.readLine()) != null) {
                String[] movies = line.split(",");
                if (movies[1].toLowerCase().contains(nameToFind.toLowerCase()) && !movies[6].equals("Closed")) {
                    System.out.println(count + ". " + "Movie Title: " + movies[1] + ", Status: " + movies[6]);
                    count++;
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No movie found!");
            }
            return found;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return found;
    }
}

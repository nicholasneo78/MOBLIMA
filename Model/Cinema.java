package MOBLIMA.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.lang.NumberFormatException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.File;

/**
 * An object that represents a cinema in a cineplex.
 *
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class Cinema {
    /**
     * Integer value for the number of rows of seats.
     */
    final int NO_OF_ROWS = 5;

    /**
     * Number of columns of seat.
     */
    final int NO_OF_COLUMNS = 10;

    /**
     * ID of movie.
     */
    private int movieID;

    /**
     * Title of movie.
     */
    private String movieTitle;

    /**
     * Showtime of movie.
     */
    private int timeSlot;

    /**
     * Showing status of movie.
     */
    private String showingStatus;

    /**
     * ID of cineplex.
     */
    private int cineplexID;

    /**
     * ID of cinema.
     */
    private int cinemaID;

    /**
     * Date of movie showing.
     */
    private String date;

    /**
     * If showtime is a holiday.
     */
    private int holiday;

    /**
     * Class of cinema movie is showed in.
     */
    private String classOfCinema;

    /**
     * ID of seat.
     */
    private String SeatID = "";

    /**
     * Number of tickets customer wants to purchase.
     */
    private int quantity = 0;

    /**
     * Array of seats.
     */
    private char[][] seat = new char[NO_OF_ROWS][NO_OF_COLUMNS + 1];

    /**
     * Constructor for a Cinema object.
     *
     * @param cinemaID ID of cinema.
     */
    //constructor
    public Cinema(int cinemaID) {

        //this.cineplex = new Cineplex(cineplexID);

        String MovieListingFile = "MOBLIMA/Data/MovieListing.csv";
        BufferedReader br = null;
        FileReader fr = null;

        String line = "";
        String csvSplitBy = ",";
        try {
            fr = new FileReader(MovieListingFile);
            br = new BufferedReader(fr);
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] id = line.split(csvSplitBy);
                if (Integer.valueOf(id[5]) == cinemaID) {
                    movieID = Integer.valueOf(id[0]);
                    movieTitle = id[1];
                    timeSlot = Integer.valueOf(id[2]);
                    showingStatus = id[3];
                    cineplexID = Integer.valueOf(id[4]);
                    this.cinemaID = Integer.valueOf(id[5]);
                    date = id[7];
                    holiday = Integer.valueOf(id[8]);
                    classOfCinema = id[9];

                    //noOfSeats = Integer.valueOf(cinema[2]);
                    //noOfVacantSeats = Integer.valueOf(cinema[3]);

                }
            }

            fr.close();
            br.close();

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
     * Displays the seat layout of the cinema.
     *
     * @param cinemaID     ID of cinema.
     * @param timeSlot     Showtime of movie.
     * @param date         Date the movie is showing.
     * @param modifiedSeat Seat layout.
     */
    ///get seat layout after modification
    public void displaySeatLayout(int cinemaID, int timeSlot, String date, char[][] modifiedSeat) {
        char alphabet = 'A';

        System.out.println("\n\tcinemaID: " + String.valueOf(cinemaID) + "\t timeSlot: " + String.valueOf(timeSlot) + "\n");
        System.out.println("\t1  2  3  4  5     6  7  8  9  10");
        System.out.println("    \tExit\t\t\t    Exit");

        for (int row = 0; row < NO_OF_ROWS; row++) {
            for (int column = 0; column < NO_OF_COLUMNS + 1; column++) {
                if (column == 0)
                    System.out.print((alphabet++) + "  \t");

                System.out.print(seat[row][column]);
                if (column != NO_OF_COLUMNS)
                    System.out.print("  ");
            }
            System.out.println();
        }
    }

    /**
     * Retrieve the seat layout from CSV file.
     *
     * @param cinemaID ID of cinema.
     * @param timeSlot Showtime of movie.
     * @param date     Date the movie is showing.
     * @return Seating layout.
     */
    public char[][] getSeatLayoutInfoFromCsv(int cinemaID, int timeSlot, String date) {


        String seatAvailability;
        String GetSeatInfoFile = "MOBLIMA/Data/MovieListing.csv";
        BufferedReader br = null;
        FileReader fr = null;

        String line = "";
        String csvSplitBy = ",";

        try {
            fr = new FileReader(GetSeatInfoFile);
            br = new BufferedReader(fr);
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] id = line.split(csvSplitBy);
                if (Integer.valueOf(id[5]) == cinemaID && Integer.valueOf(id[2]) == timeSlot && id[7].compareTo(date) == 0) {
                    seatAvailability = id[6];

                    int counter = 0;
                    for (int i = 0; i < NO_OF_ROWS; i++) {
                        for (int j = 0; j < NO_OF_COLUMNS + 1; j++) {
                            seat[i][j] = seatAvailability.charAt(counter);
                            counter++;
                        }
                    }

                    displaySeatLayout(cinemaID, timeSlot, date, seat);
                }
            }


            fr.close();
            br.close();
            return seat;

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
     *Converts 2D array into string
     * @param seats Seating of cinema
     */
    public void printSeat1D(char[][] seats) {
        String seating1D = "";  //final string to overwrite to the edited csv file
        for (int i = 0; i < NO_OF_ROWS; i++) {
            for (int j = 0; j < NO_OF_COLUMNS + 1; j++) {
                seating1D += seats[i][j];
            }
        }
        System.out.println(seating1D);
    }

    /**
     * Updates seat layout to CSV
     * @param cinemaID ID of cinema
     * @param timeSlot Showtime of movie
     * @param date Date the movie is showing
     * @param updatedSeat The updated seat layout.
     */
    //new function to write to the csv file
    public void setSeatLayoutInfoToCsv(int cinemaID, int timeSlot, String date, char[][] updatedSeat) {
		
		/*Debug
		System.out.println(updatedSeat[0][0]);
		System.out.println(updatedSeat[0][5]);
		*/

        //Convert Array to String to send to CSV
        //Initialise empty string to store
        String seating1D = "";  //final string to overwrite to the edited csv file
        for (int i = 0; i < NO_OF_ROWS; i++) {
            for (int j = 0; j < NO_OF_COLUMNS + 1; j++) {
                seating1D += updatedSeat[i][j];
            }
        }

        //Debug
        //System.out.println(seating1D);

        FileWriter writer = null;
        BufferedReader br = null;
        FileWriter writerMain = null;
        BufferedWriter bw = null;

        String line = "";
        int count = 0;

        try {
            writer = new FileWriter("MOBLIMA/Data/MovieListingEdited.csv");
            br = new BufferedReader(new FileReader("MOBLIMA/Data/MovieListing.csv"));
            writerMain = new FileWriter("MOBLIMA/Data/MovieListingMain.csv"); //duplicate of test.csv
            bw = new BufferedWriter(new FileWriter("MOBLIMA/Data/MovieListingEdited.csv"));


            while ((line = br.readLine()) != null) {

                //use comma as separator
                String[] id = line.split(",");
                if (count == 0) {
					
					/*debug
					System.out.println(id[1] + " == " + id[4]); //cinemaID and timeSlot - prints the column header
					System.out.println(line); 
					*/

                    writerMain.append(line);
                    writerMain.append("\n");

                    writer.append(line);
                    writer.append("\n");
                    count++;

                } else {
                    if (Integer.valueOf(id[5]) == cinemaID && Integer.valueOf(id[2]) == timeSlot && id[7].compareTo(date) == 0) {
						
						/*debug
						System.out.println(id[1] + " -- " + id[4]);
						System.out.println(line + "a");
						*/

                        writerMain.append(line);
                        writerMain.append("\n");

                        //update value of the seating
                        id[6] = seating1D;
                        line = "";

                        for (int i = 0; i < 10; i++) {
                            if (i != 10 - 1)
                                line += id[i] + ",";
                            else
                                line += id[i];
                        }

                        writer.append(line);
                        //Debug
                        //writer.append("-test");
                        writer.append("\n");

                    } else {
						/*debug
						System.out.println(id[1] + " xx " + id[4]);
						System.out.println(line + "b"); 
						*/

                        writerMain.append(line);
                        writerMain.append("\n");

                        writer.append(line);
                        //Debug
                        //writer.append("a");
                        writer.append("\n");
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
                    writer.flush();
                    writer.close();
                    writerMain.flush();
                    writerMain.close();
                    bw.flush();
                    bw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //File file = new File("test.csv");
        //file.delete();

        File newfile = new File("MOBLIMA/Data/MovieListing.csv");
        File mainfile = new File("MOBLIMA/Data/MovieListingMain.csv");
        File oldfile = new File("MOBLIMA/Data/MovieListingEdited.csv");

        newfile.delete();

        oldfile.renameTo(newfile);
        mainfile.delete();

    }

    /**
     * For the user to select which seat to reserve.
     * @param cinemaID ID of cinema.
     * @param timeSlot Showtime of movie.
     * @param date Date the movie is showing.
     * @param currentSittingArrangement Seating layout.
     * @param no Number of seats to select.
     * @return Boolean quit that contains if the operation was successful.
     */
    public boolean occupySeat(int cinemaID, int timeSlot, String date, char[][] currentSittingArrangement, int no) {

		/*String ab="a";
		if (ab.equalsIgnoreCase("A"))
			rowID = 0;
		 */

        String row = "";
        String columnStr;
        int column;
        String userChoice;
        quantity = no;
        boolean quit = false;

        int rowID = 0;
        int columnID = 0;

        Scanner inStr = new Scanner(System.in);

        boolean invalidRow = true;

        while (no > 0) {
            while (invalidRow) {
                //get seat layout
                System.out.println("================================================================");
                System.out.println("                        SEAT LAYOUT                            ");
                System.out.println("================================================================");

                getSeatLayoutInfoFromCsv(cinemaID, timeSlot, date);

                System.out.println("Row? (-1 to quit)");
                row = inStr.nextLine();

                if (row.equalsIgnoreCase("A")) {
                    rowID = 0;
                    invalidRow = false;
                } else if (row.equalsIgnoreCase("B")) {
                    rowID = 1;
                    invalidRow = false;
                } else if (row.equalsIgnoreCase("C")) {
                    rowID = 2;
                    invalidRow = false;
                } else if (row.equalsIgnoreCase("D")) {
                    rowID = 3;
                    invalidRow = false;
                } else if (row.equalsIgnoreCase("E")) {
                    rowID = 4;
                    invalidRow = false;
                } else if (row.equalsIgnoreCase("-1")) {
                    quit = true;
                    no = 0;
                    break;
                } else {
                    System.out.println("Please enter a valid row!");
                }
            }
            if (!quit) {
                System.out.println("Column? (-1 to quit)");
                columnStr = inStr.nextLine();

                try {
                    column = Integer.parseInt(columnStr);
                    //do column offset for the aisle
                    if (column > 0 && column <= 5) {
                        columnID = column - 1;

                        if (currentSittingArrangement[rowID][columnID] == '1') {
                            System.out.println("This seat has already been occupied, please choose another seat!");
                            invalidRow = true;
                        } else {
                            currentSittingArrangement[rowID][columnID] = '1';
                            System.out.println("Thank You! You have successfully booked a seat!");
                            SeatID += row + String.valueOf(columnID + 1) + " ";
                            no--;
                            invalidRow = true;
                            setSeatLayoutInfoToCsv(cinemaID, timeSlot, date, seat);
                        }
                    } else if (column > 5 && column <= 10) {
                        columnID = column;

                        if (currentSittingArrangement[rowID][columnID] == '1') {
                            System.out.println("This seat has already been occupied, please choose another seat!");
                            invalidRow = true;
                        } else {
                            currentSittingArrangement[rowID][columnID] = '1';
                            System.out.println("Thank You! You have successfully booked a seat!");
                            SeatID += row + String.valueOf(columnID) + " ";
                            setSeatLayoutInfoToCsv(cinemaID, timeSlot, date, seat);
                            no--;
                            invalidRow = true;
                        }
                    } else if (column == -1) {
                        quit = true;
                        break;
                    } else {
                        System.out.println("Please enter a valid column!\n");
                        invalidRow = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid column!\n");
                    invalidRow = true;
                }
            }
        }
        return quit;
    }

    /**
     * Calls function to ask user to choose seats.
     * @param timeSlot Showtime of movie.
     * @param date Sate the movie is showing.
     * @param no Number of seats to select.
     * @return Boolean quit if user quit during the choosing process.
     */
    public boolean userChooseSeat(int timeSlot, String date, int no) {
        //displaySeatLayout(cinemaID,timeSlot,date,seat);
        boolean quit;
        quit = occupySeat(cinemaID, timeSlot, date, seat, no);
        return quit;
    }

    /**
     * Get the ID of cineplex.
     * @return ID of cineplex.
     */
    public int getCineplexID() {
        return cineplexID;
    }

    /**
     * Set the ID of cineplex.
     * @param cineplexID ID of cineplex.
     */
    public void setCineplexID(int cineplexID) {
        this.cineplexID = cineplexID;
    }

    /**
     * Get the ID of cinema.
     * @return ID of cinema.
     */
    public int getCinemaID() {
        return cinemaID;
    }

    /**
     * Set the ID of cinema.
     * @param cinemaID ID of cinema.
     */
    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    /**
     * Get the ID of movie.
     * @return ID of movie.
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Get the title of the movie.
     * @return title of the movie.
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Get the showing status of the movie.
     * @return showing status of the movie.
     */
    public String getShowingStatus() {
        return showingStatus;
    }


    /**
     * Set the date the movie is showing.
     * @param date date the movie is showing.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the date the movie is showing.
     * @return date the movie is showing.
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date the movie is showing.
     * @param date date the movie is showing.
     */
    public void setDateID(String date) {
        this.date = date;
    }

    /**
     * Get if the showtime is a holiday.
     * @return if showtime is a holiday.
     */
    public int getHoliday() {
        return holiday;
    }

    /**
     * Get the seat.
     * @return Seat array.
     */
    public char[][] getSeat(){
        printSeat1D(seat);
        return seat;}

    /**
     * GGet the class of cinema.
     * @return class of cinema.
     */
    public String getClassOfCinema() {
        return classOfCinema;
    }

    /**
     * Get the showtime of the movie.
     * @return Showtime of the movie.
     */
    public int getTimeSlot() {
        return timeSlot;
    }

    /**
     * Set the showtime of the movie.
     * @param timeSlot Showtime of the movie.
     */
    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    /**
     * Get the ID of the seat.
     * @return ID of te seat.
     */
    public String getSeatID() {
        return SeatID;
    }

    /**
     * Get the number of tickets customer wants to purchase.
     * @return Number of tickets customer wants to purchase.
     */
    public int getQuantity() {
        return quantity;
    }
}

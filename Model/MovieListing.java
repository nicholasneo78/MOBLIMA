package MOBLIMA.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.File;
/**
 * Represents the list of show times of movies.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class MovieListing {
	/**
	 * Title of movie.
	 */
	private String movieTitle;

	/**
	 * Showtime of movie.
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
	 * If showtime is holiday.
	 */
	private int holiday;

	/**
	 * Constructor for MovieListing object.
	 * @param title Title of movie.
	 * @param timing Showtime of movie.
	 * @param cineplex Cineplex the movie is showing.
	 * @param location Location of the Cineplex.
	 * @param cinema Cinema the movie is showing.
	 * @param cClass Class of the cinema.
	 * @param hol Integer value of 1 if showtime is holiday, 0 otherwise.
	 */
	public MovieListing(String title, int timing, int cineplex, String location, int cinema, String cClass, int hol){
		movieTitle = title;
		movieTiming = timing;
		cineplexID = cineplex;
		cineplexLocation = location;
		cinemaClass = cClass;
		cinemaID = cinema;
		holiday = hol;
	}

	public MovieListing(){
	}

	/**
	 *Finds the dates in CSV.
	 * @return Array List of dates.
	 */
	public ArrayList<String> returnDate() {
		BufferedReader br = null;
	    String line = "";
	    boolean contains;
	    ArrayList<String>result = new ArrayList<String>();
	    try {
	    	br = new BufferedReader(new FileReader("MOBLIMA/Data/MovieListing.csv"));
	        br.readLine();
	        int count=1;
	        
	        while ((line = br.readLine()) != null) {
	        	String[] Dates = line.split(",");
	        	contains = result.contains(Dates[7]);
	        	if (!contains) {
	        		result.add(Dates[7]);
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
		return result;	    
	}

	/**
	 * Displays seat layout for a specific date and showtime.
	 * @param cinemaID ID of cinema.
	 * @param time Showtime of movie.
	 * @param date Date the movie is showing.
	 * @return Seat layout.
	 */
	public char[][] returnSeat(int cinemaID, int time, String date){
		char[][] result = new char[5][11];
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader("MOBLIMA/Data/MovieListing.csv"));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] Seats = line.split(",");
				if(Integer.valueOf(Seats[5]) == cinemaID && Integer.valueOf(Seats[2]) == time && Seats[7].equals(date)){
					String seatAvailability = Seats[6];
					int counter = 0;
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 10 + 1; j++) {
							result[i][j] = seatAvailability.charAt(counter);
							counter++;
						}
					}
				}
			}
			return result;

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
		return result;
	}

	/**
	 * Get class of a specific cinema.
	 * @return Class of a cinema.
	 */
	public String getCinemaClass() {
		return cinemaClass;
	}

	/**
	 * Get title of movie.
	 * @return Title of movie.
	 */
	public String getMovieTitle() {
		return movieTitle;
	}

	/**
	 * Get ID of cinema.
	 * @return ID of cinema.
	 */
	public int getCinemaID() {
		return cinemaID;
	}

	/**
	 * Get ID of cineplex.
	 * @return ID of cineplex.
	 */
	public int getCineplexID() {
		return cineplexID;
	}

	/**
	 * Get showtime of movie.
	 * @return Showtime of movie.
	 */
	public int getShowTime() {
		return movieTiming;
	}

	/**
	 * Get if showtime is holiday.
	 * @return if showtime is holiday.
	 */
	public int getHoliday() {
		return holiday;
	}
	
}


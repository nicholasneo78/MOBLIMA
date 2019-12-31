package MOBLIMA.Model;

import java.util.Scanner;
/**
 * Represents a seat in a cinema. A cinema has more than one seat.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class Seat {

	/**
	 * ID of seat.
	 */
	private int seatID;

	/**
	 * If seat is vacant.
	 */
	private boolean seatVacant;

	/**
	 * Enter showtime information.
	 */
	public static void forAdminView() {
		Cinema a = new Cinema(105);  //pass dummy number.
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Input CineplexID");
		int inputCineplexID = in.nextInt();
		a.setCineplexID(inputCineplexID);
		
		System.out.println("Input CinemaID");
		int inputCinemaID = in.nextInt();
		a.setCinemaID(inputCinemaID);
		
		System.out.println("Input Show Time");
		int inputShowTime = in.nextInt();
		a.setTimeSlot(inputShowTime);
		
		//System.out.println("Choose Seat: ");
		a.getSeatLayoutInfoFromCsv(a.getCinemaID(), a.getTimeSlot(),a.getDate());
	}


	/**
	 * Get the ID of the seat.
	 * @return ID of seat.
	 */
	public int getSeatID() {
		return seatID;
	}

	/**
	 * Get if seat vacant.
	 * @return if seat is vacant.
	 */
	public boolean getSeatVacant() {
		return seatVacant;
	}

	/**
	 * Set seat vacancy.
	 * @param seatVacant seat vacancy.
	 */
	public void setSeatVacant(boolean seatVacant) {
		this.seatVacant = seatVacant;
	}
	
	

}

package MOBLIMA.Model;

import java.util.Scanner;
/**
 * Represents a movie ticket.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class Ticket {
    /**
     *Price of a singular ticket.
     */
    private double ticketPrice;

    /**
     *Initialises the total ticket price to be 0.
     */
    private double totalPrice=0;

    /**
     *Movie type: 2D or 3D.
     */
    private String movieType;

    /**
     *Cinema class: IMAX or normal or first class.
     */
    private String cinemaClass;

    /**
     *Integer value of 1 if show time falls in a holiday, 0 otherwise.
     */
    private int holiday;

    /**
     *Number of child tickets.
     */
    private int childTickets;

    /**
     *Number of adult tickets.
     */
    private int adultTickets;

    /**
     *Number of National Service men tickets.
     */
    private int nsTickets;

    /**
     *Number of senior tickets.
     */
    private int seniorTickets;

    /**
     *Number of student tickets.
     */
    private int studentTickets;

    /**
     *ID of cineplex.
     */
    private int cineplexID;

    /**
     *ID of cinema.
     */
    private int cinemaID;

    /**
     *ID of seat.
     */
    private String seatID;

    /**
     *Name of the movie.
     */
    private String movieName;

    /**
     *Number of ticket.
     */
    private int quantity;

    /**
     *Show time of the movie.
     */
    private int timeSlot;
    /**
     * Cineplex object represents the cineplex that shows the movie the customer has booked.
     */
    private Cineplex c;
    /**
     * MovieListing object contains information relating to the movie and showtime.
     */
    private MovieListing ml;
    /**
     *Constructor of movie ticket.
     *@param movieListing MovieListing object contains information relating to the movie and showtime.
     *@param movieType Type of movie booked: 2D or 3D.
     *@param cinema Cinema object represents the cinema showing the movie.
     */
    public Ticket(MovieListing movieListing,String movieType,Cinema cinema){
    	ml=movieListing;
        movieName = movieListing.getMovieTitle();
        cinemaID = movieListing.getCinemaID();
        cineplexID = movieListing.getCineplexID();
        this.movieType = movieType;
        cinemaClass = movieListing.getCinemaClass();
        quantity =cinema.getQuantity();
        seatID = cinema.getSeatID();
        timeSlot = movieListing.getShowTime();
        holiday = movieListing.getHoliday();
        c=new Cineplex(cineplexID);
    }
    /**
     * Sets the number of tickets for different ticket types.
     */
    public void setTypeQuantity(){
        int child=0,adult=0,student=0,ns=0,senior=0;
        Boolean test=false;
        Scanner sc = new Scanner(System.in);
        while(test==false) {
            System.out.println("Enter the number of tickets for each type: ");
            System.out.println("1. Child Ticket:");
            child = sc.nextInt();
            System.out.println("2. Adult Ticket:");
            adult = sc.nextInt();
            System.out.println("3. Student Ticket:");
            student = sc.nextInt();
            System.out.println("4. Senior Ticket:");
            senior = sc.nextInt();
            System.out.println("5. NS Ticket:");
            ns = sc.nextInt();
            if(quantity==student+child+adult+ns+senior) {
            	test=true;
            	childTickets=child;
            	adultTickets=adult;
            	nsTickets=ns;
            	seniorTickets=senior;
            	studentTickets=student;
            }
            else {
            	System.out.println("Incorrect number of tickets!");
            }
            }
        
    }

    /**
     * calculates the total price of the tickets purchased.
     */
    private void calculatePrice(){
        ticketPrice = c.getBasePrice();
        if(holiday == 0) {
        totalPrice+=ticketPrice*(childTickets)*(1-c.getDiscount("child"));
        totalPrice+=ticketPrice*(adultTickets)*(1-c.getDiscount("adult"));
        totalPrice+=ticketPrice*(seniorTickets)*(1-c.getDiscount("senior"));
        totalPrice+=ticketPrice*(nsTickets)*(1-c.getDiscount("ns"));
        totalPrice+=ticketPrice*(studentTickets)*(1-c.getDiscount("student"));}
        else {
        	totalPrice=ticketPrice*quantity;
        }
        if(movieType.equals("2D")){
            totalPrice += 0;
        } else if(movieType.equals("3D")){
            totalPrice += 3*quantity;
        }

        if(cinemaClass.equals("normal")){
            totalPrice += 0;
        } else if(cinemaClass.equals("IMAX")){
            totalPrice += 5*quantity;
        } else if(cinemaClass.equals("First Class")){
            totalPrice += 20*quantity;
        }
    }
    /**
     * Gets total price of tickets purchased.
     * @return Total price of ticket purchased.
     */
    public double getTotalPrice(){
        calculatePrice();
        return totalPrice;
    }

    /**
     * Gets quantity of ticket.
     * @return Quantity of ticket.
     */
    public int getQuantity(){ return quantity;}

    /**
     * Gets cinema ID.
     * @return Cinema ID.
     */
    public int getCinemaID(){
        return cinemaID;
    }

    /**
     * Gets cineplex ID.
     * @return Cineplex ID.
     */
    public int getCineplexID(){
        return cineplexID;
    }

    /**
     * Gets name of movie.
     * @return Name of movie.
     */
    public String getMovieName(){
        return movieName;
    }

    /**
     * Displays the movie tickets that the customer has booked.
     */
    public void printTicket(){
    	double priceTotal = getTotalPrice();
        System.out.println("================================================================");
        System.out.println("                        TICKET INFO:                            ");
        System.out.println("================================================================");
        System.out.print("Movies: "+ movieName);
        System.out.print("\t");
        System.out.print("Time slot: " + timeSlot);
        System.out.print("\t");
        System.out.println("Cineplex: " + c.getCineplexLocation());
        System.out.print("Cinema: " + cinemaID);
        System.out.print("\t");
        System.out.println("Cinema class: "+ml.getCinemaClass());
        System.out.print("\t");
        System.out.println("Seats chosen: " + seatID);
        System.out.print("Tickets: ");
        if(childTickets != 0)
            System.out.println(childTickets+" Child tickets");
        if(adultTickets != 0)
            System.out.println(adultTickets+" Adult tickets");
        if(seniorTickets != 0)
            System.out.println(seniorTickets+" Senior tickets");
        if(studentTickets != 0)
            System.out.println(studentTickets+" Student tickets");
        if(nsTickets != 0)
            System.out.println(nsTickets+" NS tickets");
        System.out.println("Total: " + quantity);
        System.out.printf("Total Price: %.2f \n", priceTotal);
        System.out.println("================================================================");
    }
}

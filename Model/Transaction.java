package MOBLIMA.Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Represents a transaction made by a customer.
 *
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class Transaction {
    /**
     * Name of customer.
     */
    private String customerName;

    /**
     * Mobile number of customer.
     */
    private int customerMobileNo;

    /**
     * Email of customer.
     */
    private String customerEmail;

    /**
     * ID of transaction.
     */
    private String transactionID;

    /**
     * Time of transaction.
     */
    private String transactionTime;

    /**
     * ID of cineplex.
     */
    private int cineplexID;

    /**
     * ID of cinema.
     */
    private int cinemaID;

    /**
     * Title of movie.
     */
    private String movieName;

    /**
     * Total amount of transaction.
     */
    private double transactionAmount = 0;

    /**
     * Number of tickets customer purchases.
     */
    private int quantity;

    /**
     * Constructor for a Transaction object.
     *
     * @param customerName  Name of customer.
     * @param customerNo    Mobile number of customer.
     * @param customerEmail Email of customer.
     * @param ticket        Ticket of transaction.
     */
    public Transaction(String customerName, int customerNo, String customerEmail, Ticket ticket) {
        this.customerName = customerName;
        customerMobileNo = customerNo;
        this.customerEmail = customerEmail;
        cineplexID = ticket.getCineplexID();
        cinemaID = ticket.getCinemaID();
        movieName = ticket.getMovieName();
        transactionID = generateID();
        quantity = ticket.getQuantity();
        calculateTransaction(ticket);
    }

    /**
     * Return Date format.
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

    /**
     * Calls for total cost of transaction.
     *
     * @param ticket Ticket of transaction.
     */
    private void calculateTransaction(Ticket ticket) {
        transactionAmount = ticket.getTotalPrice();
    }

    /**
     * Generates an ID for the transaction.
     *
     * @return ID of transaction.
     */
    private String generateID() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ID = cinemaID + sdf.format(timestamp);
        return ID;
    }

    /**
     * Writes transaction into CSV.
     */
    public void recordTransaction() throws IOException {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("MOBLIMA/Data/Transaction History.csv", true);
            bw = new BufferedWriter(fw);

            bw.write(transactionID + "," + customerName + "," + String.valueOf(customerMobileNo) + "," + customerEmail + "," + movieName + "," + String.valueOf(cineplexID) + "," + String.valueOf(cinemaID) + "," + String.valueOf(transactionAmount) + "," + String.valueOf(quantity));

            bw.write("\n");
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


}

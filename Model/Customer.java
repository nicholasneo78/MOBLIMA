package MOBLIMA.Model;


import MOBLIMA.View.CustomerView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a customer, or moviegoer.
 * <br> Sub class of class User.
 *
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class Customer extends User {
    private String[] history;

    /**
     * Constructor for a Customer object that represents an existing customer with recorded booking history.
     *
     * @param id      The ID of the customer.
     * @param name    The customer's name.
     * @param email   The customer's email.
     * @param age     The customer's age.
     * @param mobile  The customer's mobile number.
     * @param admin   The customer's admin privilege (0 for customer).
     * @param history The customer's booking history.
     */
    private Customer(int id, String name, String email, int age, int mobile, int admin, String[] history) {
        userID = id;
        userAge = age;
        userName = name;
        userEmail = email;
        userMobileNo = mobile;
        this.admin = admin;
        this.history = history;
    }

    /**
     * Constructor for a Customer object that represents an a new customer just registered with no record booking
     * history.
     *
     * @param id     The ID of the customer.
     * @param name   The customer's name.
     * @param email  The customer's email.
     * @param age    The customer's age.
     * @param mobile The customer's mobile number.
     * @param admin  The customer's admin privilege (0 for customer).
     */
    private Customer(int id, String name, String email, int age, int mobile, int admin) {
        userID = id;
        userAge = age;
        userName = name;
        userEmail = email;
        userMobileNo = mobile;
        this.admin = admin;
    }

    /**
     * Gets the information of the customer from the database and sets all the relevant information.
     *
     * @param userID The customer's ID.
     * @return The Customer object with all attributes (information) set accordingly from the database.
     */
    protected static Customer setUser(int userID) {
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("MOBLIMA/Data/userdata.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] customer = line.split(",");
                if (Integer.valueOf(customer[0]) == userID) {

                    if (!customer[7].equals(" "))
                        return new Customer(userID, customer[2], customer[4], Integer.valueOf(customer[1]), Integer.valueOf(customer[3]), Integer.valueOf(customer[5]), customer[7].split("-"));
                    else
                        return new Customer(userID, customer[2], customer[4], Integer.valueOf(customer[1]), Integer.valueOf(customer[3]), Integer.valueOf(customer[5]));

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
     * Gets the booking history of a specific customer.
     *
     * @return The customer's booking history.
     */
    public String[] getHistory() {
        return history;
    }

    /**
     * Shows the customer menu after the user logins.
     */
    public void view() {
        CustomerView.show(this);
    }

    /**
     * Updates the booking history of a user after the user booked a movie.
     *
     * @param ml MovieListing object contains all the relevant information about the movie and show time.
     */
    public void updateHistory(MovieListing ml) {
        FileWriter writer = null;
        BufferedReader br = null;
        ArrayList<String> result = new ArrayList<>();
        String line = "";
        int count = 0;
        try {

            br = new BufferedReader(new FileReader("MOBLIMA/Data/userdata.csv"));
            result.add(br.readLine());
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] id = line.split(",");
                if (Integer.valueOf(id[0]).equals(userID)) {
                    result.add("\n");
                    result.add(line + "-" + ml.getMovieTitle());

                } else {
                    result.add("\n");
                    result.add(line);
                }
            }
            writer = new FileWriter("MOBLIMA/Data/userdata.csv");
            for(String data: result)
                writer.append(data);
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

    /**
     * Shows booking history of a user. If the history is empty, show "No booking history".
     */
    public void bookingHistory() {
        int i = 0;
        int j = 1;

        setUser(userID);
        ;
        if (history == null) {
            System.out.println("No booking history.");
        } else {
            while (i < history.length) {
                if (history[i].compareTo(" ") == 0) {
                    i++;
                } else {
                    System.out.println((j) + "." + history[i]);
                    i++;
                    j++;
                }
            }
        }
    }

    /**
     * Print the greeting message with the customer's name.
     */
    public void printName() {
        System.out.println("Welcome to MOBLIMA, " + userName + ".");
    }
}
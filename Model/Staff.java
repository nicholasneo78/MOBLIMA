package MOBLIMA.Model;

import MOBLIMA.View.StaffView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a staff, or admin user with admin privilege.
 * <br> Subclass of class User.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */

public class Staff extends User {

    /**
     * Constructor for a Staff object representing a Staff user.
     * @param id UserID of Staff.
     * @param name Name of Staff.
     * @param email Email of Staff.
     * @param age Age of Staff.
     * @param mobile Mobile number of Staff.
     * @param admin Admin rights for Staff (Integer value of 1).
     */
    private Staff(int id, String name, String email, int age, int mobile, int admin) {
        /**
         * ID of Staff.
         */
        userID = id;
        /**
         * Age of Staff.
         */
        userAge = age;
        /**
         * Name of Staff.
         */
        userName = name;
        /**
         * Email of Staff.
         */
        userEmail = email;
        /**
         * Mobile Number of Staff.
         */
        userMobileNo = mobile;
        /**
         * Admin rights of Staff.
         */
        this.admin = admin;
    }

    /**
     * Set the staff information of selected user ID.
     * @param userID UserID of staff.
     * @return Return Staff object with all the attributes set according to the information in the database.
     * <br> Return null if Staff user does not exist.
     */
    protected static Staff setUser(int userID) {
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("MOBLIMA/Data/admindata.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] customer = line.split(",");
                if (Integer.valueOf(customer[0]) == userID) {
                    return new Staff(userID, customer[1], customer[3], Integer.valueOf(customer[4]), Integer.valueOf(customer[2]), Integer.valueOf(customer[5]));
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
     * Print welcome message personalized by name of staff.
     */
    public void printName(){
        System.out.println("Welcome, "+userName+".");
    }
    /**
     * Shows the Staff menu after the user logins.
     */
    public void view() {
        StaffView.show(this);
    }
}


	

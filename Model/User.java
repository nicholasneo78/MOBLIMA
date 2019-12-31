package MOBLIMA.Model;

/**
 * Abstract class User representing the user of the application.
 * <br> Super class of Customer and Staff.
 *  @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public abstract class User {
    /**
     * Name of the user.
     */
    protected String userName;
    /**
     * ID of the user.
     */
    protected int userID;
    /**
     * Mobile number of the user.
     */
    protected int userMobileNo;
    /**
     * Age of the number.
     */
    protected int userAge;
    /**
     * Email of the user.
     */
    protected String userEmail;
    /**
     * Integer value of 1 if user is a staff, 0 otherwise.
     */
    protected int admin;

    /**
     * Abstract method to be implemented by Customer or Staff to display appropriate menu.
     */
    public abstract void view();

    /**
     * Gets user's name.
     * @return User's name.
     */
    public String getUserName() { return userName;}

    /**
     * Gets user's ID.
     * @return User's ID.
     */
    public int getUserID(){ return userID; }

    /**
     * Gets user's mobile number.
     * @return User's mobile number.
     */
    public int getUserMobileNo(){ return userMobileNo; }

    /**
     * Gets user's age.
     * @return User's age.
     */
    public int getUserAge(){ return userAge; }

    /**
     * Gets user's email.
     * @return User's email.
     */
    public String getUserEmail(){ return userEmail; }

    /**
     * Method to print user's name.
     */
    public void printName() {
        System.out.println(userName);
    }

    /**
     * Creates a User object that will take up either the role of Customer or Staff.
     * @param uid User's ID.
     * @param isAdmin Integer value of 1 if user is a staff, 0 otherwise.
     * @return User object in either Customer class or Staff class.
     */
    public static User createUser(int uid, int isAdmin) {
        return isAdmin == 1 ? Staff.setUser(uid) : Customer.setUser(uid);
    }
}



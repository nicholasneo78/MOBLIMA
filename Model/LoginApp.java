package MOBLIMA.Model;


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the access control of MOBLIMA.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class LoginApp {
    /**
     * To count the number of login credential.
     */
    private static int userCount = 0;

    /**
     * Login page for both customer and admin.
     * @return Integer array consisting of isAdmin and userid.
     */
    public int[] login() throws Exception {
        Scanner scan = new Scanner(System.in);
        String loginFile = "MOBLIMA/Data/login.csv";
        BufferedReader br = null;
        FileReader fr = null;
        MovieListing ml = new MovieListing();
        Movie m = new Movie();
        String line = "";
        String csvSplitBy = ",";
        int isAdmin = -1;
        boolean run = true;
        int userid = 0;

        try {
            while (run) {
                fr = new FileReader(loginFile);
                br = new BufferedReader(fr);
                System.out.println("================================================================");
                System.out.println("                            LOGIN                            ");
                System.out.println("================================================================");
                System.out.println("Welcome to MOBLIMA");
                System.out.println("Please enter your username and password");
                System.out.println();
                Console console = System.console();
                //System.out.println("Username: ");
                //String username = scan.nextLine();
                String username = console.readLine("Username: ");
                //System.out.println("Password: ");
                //String password = scan.nextLine();
                String password = new String(console.readPassword("Password: "));

                while ((line = br.readLine()) != null) {
                    userCount++;
                    String[] user = line.split(csvSplitBy);
                    if (user[1].equals(username) && user[2].equals(password)) {
                        isAdmin = Integer.parseInt(user[3]);
                        userid = Integer.parseInt(user[0]);
                        run = false;
                        break;
                    }
                }

                if (isAdmin == -1)
                    System.out.println("Invalid credentials!\n");

                fr.close();
                br.close();
            }


            int[] result = {isAdmin, userid};
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
        return null;
    }
    /**
     * Registers a new user.
     */
    public void register() {
        String line = "";
        int customerAge;
        String customerName;
        int customerNumber;
        String customerEmail;
        BufferedWriter bw = null;
        FileWriter fw = null;
        BufferedWriter bw1 = null;
        FileWriter fw1 = null;
        BufferedReader br = null;
        FileReader fr = null;
        Console console = System.console();
        String user;
        String password = "";
        try {
            System.out.println("================================================================");
            System.out.println("                            REGISTER                            ");
            System.out.println("================================================================");
            System.out.println("Enter new User:");
            Scanner sc = new Scanner(System.in);
            user = sc.nextLine();
            while (true) {
                //System.out.println("Enter new password:");
                password = new String(console.readPassword("Enter new password: "));
                //System.out.println("Re-enter new password:");
                if (!password.equals(new String(console.readPassword("Re-enter new password: ")))) {
                    System.out.println("Password not match");
                } else {
                    break;
                }
            }
            System.out.println("Enter name:");
            customerName = sc.nextLine();

            System.out.println("Enter age:");
            customerAge = sc.nextInt();
            System.out.println("Enter email:");
            sc.nextLine();
            customerEmail = sc.nextLine();
            System.out.println("Enter phone number:");
            customerNumber = sc.nextInt();
            String loginFile = "MOBLIMA/Data/login.csv";


            fr = new FileReader(loginFile);
            br = new BufferedReader(fr);
            fw = new FileWriter("MOBLIMA/Data/userdata.csv", true);
            fw1 = new FileWriter("MOBLIMA/Data/login.csv", true);
            System.out.println("Register successfully! You will be returned to the main page.");
            while ((line = br.readLine()) != null) {
                userCount++;
            }
            fw1.append(userCount + "," + user + "," + password + "," + "0\n");
            fw.append(userCount + "," + customerAge + "," + customerName + "," + customerNumber + "," + customerEmail + "," + "0" + "," + " " + "," + " " + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException e){
            System.out.println("Invalid Input!");
        }
        finally {
            if (fw != null) {
                try {
                    br.close();
                    fw.close();
                    fw1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




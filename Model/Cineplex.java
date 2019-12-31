package MOBLIMA.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Cineplex.
 * Each cineplex may have different discount percentage for different customer groups.
 * @author SE3-grp1
 * @version 1.0
 * @since 2019-11-15
 */
public class Cineplex {

	/**
	 *ID of cineplex.
	 */
	private int cineplexID;
	/**
	 *Location of cineplex.
	 */
	private String location;
	/**
	 *Number of cinema in a given cineplex.
	 */
	private int noOfCinemas;
	/**
	 *Base ticket price of cineplex.
	 */
	private int basePrice;
	/**
	 *Child discount percentage of a given cineplex.
	 */
	private double childDiscount;
	/**
	 *Senior discount percentage of a given cineplex.
	 */
	private double seniorDiscount;
	/**
	 *Ns discount percentage of a given cineplex.
	 */
	private double nsDiscount;
	/**
	 *Student discount percentage of a given cineplex.
	 */
	private double studentDiscount;
	/**
	 *Adult discount percentage of a given cineplex.
	 */
	private double adultDiscount;

	/**
	 *Constructor for the Cineplex object.
	 *@param cineplexID ID of the selected cineplex.
	 */
	public Cineplex(int cineplexID) {
		 String cineplexFile = "MOBLIMA/Data/Cineplex.csv";
	        BufferedReader br = null;
	        FileReader fr = null;
	        
	        String line = "";
	        String csvSplitBy = ",";
	        try {
	            fr = new FileReader(cineplexFile);
	            br = new BufferedReader(fr);
	          br.readLine();
	                
	                while ((line = br.readLine()) != null) {
	                    String [] cineplex = line.split(csvSplitBy);
	                    //System.out.println(cineplex[0]);
	                    	if(Integer.valueOf(cineplex[0]) == cineplexID) {
	                    	this.cineplexID = Integer.valueOf(cineplex[0]);
	                    	location = cineplex[1];
	                    	noOfCinemas = Integer.valueOf(cineplex[2]);
	                    	basePrice=Integer.valueOf(cineplex[3]);
	                    	childDiscount=Double.valueOf(cineplex[4]);
	                    	seniorDiscount=Double.valueOf(cineplex[6]);
	                    	studentDiscount=Double.valueOf(cineplex[5]);
	                    	nsDiscount = Double.valueOf(cineplex[7]);
	                    	adultDiscount=Double.valueOf(cineplex[8]);
	                    	
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
	 * Gets base price of ticket.
	 * @return Base price of ticket.
	 */
	public double getBasePrice() {
		return basePrice;
	}

	/**
	 * Edits the discount percentage of specific cineplex for all customer groups.
	 * @param location Location of the cineplex.
	 */
	public void editDiscount(String location) {
		Scanner sc=new Scanner(System.in);
		String basePrice;
		String childDiscount;
		String studentDiscount;
		String adultDiscount;
		String seniorDiscount;
		String nsDiscount;
		if(location.compareTo("0")==0) {
			return;
		}
		else {
			System.out.println("Enter new base price:");
			basePrice=sc.nextLine();
			System.out.println("Enter new child discount:");
			childDiscount=sc.nextLine();
			System.out.println("Enter new student discount:");
			studentDiscount=sc.nextLine();
			System.out.println("Enter new adult discount:");
			adultDiscount=sc.nextLine();
			System.out.println("Enter new senior discount:");
			seniorDiscount=sc.nextLine();
			System.out.println("Enter new ns discount:");
			nsDiscount=sc.nextLine();
			FileWriter writer=null;
			BufferedReader br=null;
			String line="";
			int count=0;
			try {
		        writer = new FileWriter("MOBLIMA/Data/Cineplext.csv" );
		        br = new BufferedReader(new FileReader("MOBLIMA/Data/Cineplex.csv"));
		        while ((line = br.readLine()) != null) {
		        	
		            // use comma as separator
		            String[] cineplex = line.split(",");
		            if(count==0) {
		            	writer.append(line);
		            	writer.append("\n");
		            	count++;
		            }else {
		            	if(cineplex[1].equalsIgnoreCase(location)) {
		            	writer.append(cineplex[0]);
		            	writer.append(",");
		            	writer.append(cineplex[1]);
		            	writer.append(",");
		            	writer.append(cineplex[2]);
		            	writer.append(",");
		            	writer.append(basePrice);
		            	writer.append(",");
		            	writer.append(childDiscount);
		            	writer.append(",");
		            	writer.append(studentDiscount);
		            	writer.append(",");
		            	writer.append(seniorDiscount);
		            	writer.append(",");
		            	writer.append(nsDiscount);
		            	writer.append(",");
		            	writer.append(adultDiscount);
		            	writer.append(",");
		            	
		            	writer.append("\n");
		                }
		            	else {
		            		writer.append(line);
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
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
			 File file=new File("MOBLIMA/Data/Cineplex.csv");
		        file.delete();
		        
		        File oldfile =new File("MOBLIMA/Data/Cineplext.csv");
		        File newfile =new File("MOBLIMA/Data/Cineplex.csv");
		        

		        oldfile.renameTo(newfile);
			
		}
		
	}

	/**
	 * Shows discount percentage for all customer groups of all cineplex.
	 */
	public void viewDiscount() {
		String cineplexFile = "MOBLIMA/Data/Cineplex.csv";
        BufferedReader br = null;
        FileReader fr = null;
        
        String line = "";
        String csvSplitBy = ",";
        try {
            fr = new FileReader(cineplexFile);
            br = new BufferedReader(fr);
          br.readLine();
                
                while ((line = br.readLine()) != null) {
                    String [] cineplex = line.split(csvSplitBy);
                    System.out.println("Location: "+cineplex[1]);
                    System.out.println("\t Base price:$"+cineplex[3]);
                    System.out.println("\t Child discount:"+cineplex[4]);
                    System.out.println("\t Student discount:"+cineplex[5]);
                    System.out.println("\t Adult discount:"+cineplex[8]);
                    System.out.println("\t Senior discount:"+cineplex[6]);
                    System.out.println("\t NS discount:"+cineplex[7]);
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
	 * Gets discount percentage for a specific customer group of a given cineplex.
	 * @param ageGroup String value represents customer group.
	 * @return Discount percentage for that customer group.
	 */
	public double getDiscount(String ageGroup) {
		
		double discountRate = 0;
		
		if(ageGroup == "child")
			discountRate = childDiscount;
		else if(ageGroup == "ns")
			discountRate =  nsDiscount;
		else if(ageGroup == "student")
			discountRate = studentDiscount;
		else if(ageGroup == "senior")
			discountRate = seniorDiscount;
		else //for NS
			discountRate =adultDiscount;
			
		return discountRate;
	}


	/**
	 * Gets cineplex ID of cineplex.
	 * @return CineplexID.
	 */
	public int getCineplexID() {
		return cineplexID;
	}

	/**
	 * Get loaction of cineplex.
	 * @return cineplex Location.
	 */
	public String getCineplexLocation() {
		return location;
	}

	/**
	 * Get number of cinema in a specific cineplex.
	 * @return The number of cinema in a specific cineplex.
	 */
	public int getNoOfCinemas() {
		return noOfCinemas;
	}
	
	
}

package quickFoodMS;
/**
 * Class that represents a customer. 
 *
 * @author Brandon Sherriff
 */

public class Customer {
	
	private int ID;
	private String fName;
	private String lName;
	private String teleNum;
	private String email;
	private String streetAdd;
	private String suburb;
	private String location;
	
	
	/**
	 * Method that creates a customer. 
	 *
	 * @param ID, the ID of the customer
	 * @param fName, the first name of the customer
	 * @param lName, the last name of the customer
	 * @param teleNum, the telephone number of the customer
	 * @param email, the email address of the customer
	 * @param streetAdd, the street address where the customer resides
	 * @param suburb, the suburb where the customer resides
	 * @param location, the location (town) where the customer resides
	 */
	public Customer (int ID, String fName ,String lName, String teleNum, String email, String streetAdd ,String suburb ,String location){
		this.setID(ID);
		this.setfName(fName);
		this.setlName(lName);
		this.setTeleNum(teleNum);
		this.setEmail(email);
		this.setStreetAdd(streetAdd);
		this.setSuburb(suburb);
		this.setLocation(location);
		
	}
	
	/**
	 * Method that retrieves the ID of the customer
	 * @return ID, the ID of the customer
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Method that sets the ID of the customer
	 * @param iD, the ID of the customer
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * Method that retrieves the first name of the customer
	 * @return fName, the first name of the customer
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * Method that sets the first name of the customer
	 * @param fName, the first name of the customer
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * Method that retrieves the last name of the customer
	 * @return lName, the last name of the customer
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * Method that sets the last name of the customer
	 * @param lName, the last name of the customer
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * Method that retrieves the telephone number of the customer
	 * @return teleNum, the telephone of the customer
	 */
	public String getTeleNum() {
		return teleNum;
	}

	/**
	 * Method that sets the telephone of the customer
	 * @param teleNum, the telephone of the customer
	 */
	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}

	/**
	 * Method that retrieves the email address of the customer
	 * @return email, the email address of the customer
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method that sets the email address of the customer
	 * @param email, the email address of the customer
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method that retrieves the street address where the customer resides
	 * @return streetAdd, the street address where the customer resides
	 */
	public String getStreetAdd() {
		return streetAdd;
	}

	/**
	 * Method that sets the street address where the customer resides
	 * @param streetAdd, the street address where the customer resides
	 */
	public void setStreetAdd(String streetAdd) {
		this.streetAdd = streetAdd;
	}

	/**
	 * Method that retrieves the suburb where the customer resides
	 * @return suburb, the suburb where the customer resides
	 */
	public String getSuburb() {
		return suburb;
	}

	/**
	 * Method that sets the suburb where the customer resides
	 * @param suburb, the suburb where the customer resides
	 */
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	/**
	 * Method that retrieves the location (town) where the customer resides
	 * @return location, the location (town) where the customer resides
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Method that sets the location (town) where the customer resides
	 * @param location, the location (town) where the customer resides
	 */
	public void setLocation(String location) {
		this.location = location;
	}

}

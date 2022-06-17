package quickFoodMS;

/**
 * Class that represents a restaurant. 
 *
 * @author Brandon Sherriff
 */

public class Restaurant {
	
	private int ID;
	private String name;
	private String location;
	private String teleNum;
	
	/**
	 * Method that creates a restaurant. 
	 *
	 * @param ID, the ID of the restaurant
	 * @param name, the name of the restaurant
	 * @param location, the location (town) of the restaurant
	 * @param teleNum, the telephone number of the restaurant
	 */
	public Restaurant (int ID, String name, String location, String teleNum) {
		this.setID(ID);
		this.setName(name);
		this.setLocation(location);
		this.setTeleNum(teleNum);
	}
	
	/**
	 * Method that retrieves the ID of the restaurant
	 * @return the ID of the restaurant
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Method that sets the ID of the restaurant
	 * @param iD, the ID of the restaurant
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * Method that retrieves the name of the restaurant
	 * @return name, the name of the restaurant
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method that sets the name of the restaurant
	 * @param name, the name of the restaurant
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method that retrieves the location (town) of the restaurant
	 * @return location, the location (town) of the restaurant
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Method that sets the location (town) of the restaurant
	 * @param location, the location (town) of the restaurant
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Method that retrieves the telephone number of the restaurant
	 * @return teleNum, the telephone of the restaurant
	 */
	public String getTeleNum() {
		return teleNum;
	}
	
	/**
	 * Method that sets the telephone number of the restaurant
	 * @param teleNum, the telephone of the restaurant
	 */
	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}
	
}

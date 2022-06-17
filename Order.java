package quickFoodMS;

/**
 * Class that represents an order. 
 *
 * @author Brandon Sherriff
 */

public class Order {
	
	private int orderNum;
	private int custID;
	private int restID;
	private String specInstruct;
	private double amtToBePaid;

	/**
	 * Method that creates an order. 
	 *
	 * @param orderNum, the order number of the order
	 * @param custID, the ID of the customer associated with the order
	 * @param restID, the ID of the restaurant associated with the order
	 * @param specInstruct, the special instructions for the order
	 * @param amtToBePaid, the total amount to be paid for the order
	 */
	
	public Order(int orderNum, int custID, int restID, String specInstruct, double amtToBePaid) {
		this.setOrderNum(orderNum);
		this.setCustID(custID);
		this.setRestID(restID);
		this.setSpecInstruct(specInstruct);
		this.setAmtToBePaid(amtToBePaid);
		
	}
	
	/**
	 * Method that retrieves the order number of the order
	 * @return orderNum, the order number of the order
	 */
	public int getOrderNum() {
		return orderNum;
	}

	/**
	 * Method that sets the order number of the order
	 * @param orderNum, the order number of the order
	 */
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	/**
	 * Method that retrieves the ID of the customer associated with the order
	 * @return custID, the ID of the customer associated with the order
	 */
	public int getCustID() {
		return custID;
	}
	
	/**
	 * Method that sets the ID of the customer associated with the order
	 * @param custID, the ID of the customer associated with the order
	 */
	public void setCustID(int custID) {
		this.custID = custID;
	}
	
	/**
	 * Method that retrieves the ID of the restaurant associated with the order
	 * @return restID, the ID of the restaurant associated with the order
	 */
	public int getRestID() {
		return restID;
	}
	
	/**
	 * Method that sets the ID of the restaurant associated with the order
	 * @param restID, the ID of the restaurant associated with the order
	 */
	public void setRestID(int restID) {
		this.restID = restID;
	}
	
	/**
	 * Method that retrieves the special instruction for the order
	 * @return specInstruct, the special instruction for the order
	 */
	public String getSpecInstruct() {
		return specInstruct;
	}
	
	/**
	 * Method that sets the special instruction for the order
	 * @param specInstruct, the special instruction for the order
	 */
	public void setSpecInstruct(String specInstruct) {
		this.specInstruct = specInstruct;
	}
	
	/**
	 * Method that retrieves the total amount to be paid for the order
	 * @return amtToBePaid, the total amount to be paid for the order
	 */
	public double getAmtToBePaid() {
		return amtToBePaid;
	}
	
	/**
	 * Method that sets the total amount to be paid for the order
	 * @param amtToBePaid, the total amount to be paid for the order
	 */
	public void setAmtToBePaid(double amtToBePaid) {
		this.amtToBePaid = amtToBePaid;
	}

}
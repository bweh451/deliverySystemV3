package quickFoodMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Formatter;

/**
 * Class that contains methods relative to creating and generating an invoice 
 *
 * @author Brandon Sherriff
 */
public class Invoice {
	
	/**
	 * Created a public static instance of the CheckQueries class in order to use its methods throughout this class.
	 */
	public static CheckQueries checkQuery = new CheckQueries();
	
	/**
	 *Created a public static instance of the DecimalFormat class in order to use its methods throughout this class.
	 */
	public static DecimalFormat dFormat = new DecimalFormat("0.00");
	
	/**
	 * Method that calls multiple other methods within the class, it can be seen as the final (main) method of this class
	 * This method determines which invoice to write depending on if the order has a driver assigned to it or not
	 * It writes the invoice to a file called "invoice.txt"
	 * 
	 * @param orderNum, the order number or the order the invoice is being generated from (entered in the "finaliseOrder" method)
	 * @see UpdateQueries#finaliseOrder
	 * @see CheckQueries#checkIfOrderHasDriver
	 * @see getItemsOrdered
	 * @see createFullInvoice
	 */
	public void writeInvoice (int orderNum) {
		String fullInvoice = "";
		
		//Checks if the order has a driver assigned to it
		int driverID = checkQuery.checkIfOrderHasDriver(orderNum);
		
		//If the driver ID is smaller than 1 then the invoice will contain the following text
		if(driverID < 1) {
			fullInvoice = "Sorry! Our drivers are too far away from you to be able to deliver to your location.";
		}
		
		//If the driverID is bigger than 1 then the following methods will be called
		else  {
			String itemsOrdered = getItemsOrdered(orderNum);
			fullInvoice = createFullInvoice(orderNum, itemsOrdered);
		}
		
		try {
			
			//Created a formatter that writes to a file called "invoice.txt"
			Formatter format = new Formatter("invoice.txt");
			
			//Writes whatever text is inside of the fullInvoice String to the "invoice.txt" file
			format.format(fullInvoice);
			
			//Formatter gets closed
			format.close();
		}
		
		catch(Exception e) {
			System.out.println(e);
		}

	}
	
	/**
	 * Method that retrieves all the items ordered for a specific order and lists them within a string
	 * 
	 * @param orderNum, the order number or the order the invoice is being generated from (entered in the "finaliseOrder" method)
	 * @see UpdateQueries#finaliseOrder
	 * @return orderItemList, a String containing a list of the items ordered including their price and quantities
	 */
	public String getItemsOrdered(int orderNum) {
		
		String item = "";
		String orderItemList = "";
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			//Uses INNER JOIN to get information from the orderItems table relative to the orders table's orderNum
			PreparedStatement statement = conn.prepareStatement("SELECT orderItems.* FROM orders INNER JOIN orderItems ON orders.orderNum = orderItems.orderNum WHERE orders.orderNum = ?");
			
			statement.setInt(1, orderNum);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				
				//Used the a method from the DecimalFormat class so that the price can be displayed with 2 decimal places
				item = "\n" + rs.getInt("orderItems.itemQty") + " x " + rs.getString("orderItems.itemName") + " " + "(R" + dFormat.format(rs.getDouble("orderItems.itemPrice")) + ")";
				orderItemList += item;
			}
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return orderItemList;
	}
	
	/**
	 * Method that creates an invoice in the correct format by getting all the necessary information from all tables linked to the orders table
	 * 
	 * @param orderNum, the order number or the order the invoice is being generated from (entered in the "finaliseOrder" method)
	 * @param orderItemList, a String containing a list of the items ordered including their price and quantities (created in the "getItemsOrdered" method)
	 * @see UpdateQueries#finaliseOrder
	 * @see getItemsOrdered
	 * @return fullInvoice, a String containing a full invoice with all the necessary information 
	 */
	public String createFullInvoice(int orderNum, String orderItemList) {
		
		String fullInvoice = "";

		try {
			Connection conn = DBConnection.connectToDB();
			
			//Get all the information within all the table by using INNER JOINs where the orders table is linked via foreign keys to the other tables
			PreparedStatement statement = conn.prepareStatement("SELECT orders.*, customers.*, restaurants.*, drivers.* FROM orders "
					+ "INNER JOIN customers ON orders.custID = customers.custID "
					+ "INNER JOIN restaurants ON orders.restID = restaurants.restID "
					+ "INNER JOIN drivers ON orders.driverID = drivers.driverID "
					+ "WHERE orders.orderNum = ?");
			
			statement.setInt(1, orderNum);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				fullInvoice = "Order Number: " + rs.getInt("orders.orderNum") 
						+"\nCustomer: " + rs.getString("customers.custFName") + " " + rs.getString("customers.custLName")
						+"\nEmail: " + rs.getString("customers.custEmail") 
						+"\nPhone Number: " + rs.getString("customers.custTeleNum")
						+"\nLocation : " + rs.getString("customers.custLocation")
						+"\n\n" + "You have ordered the following from " + rs.getString("restaurants.restName") + " in " + rs.getString("restaurants.restLocation") + ":"
						+"\n" + orderItemList
						+"\n\nSpecial instructions: " + rs.getString("orders.specInstruct")
						
						//Used the a method from the DecimalFormat class so that the price can be displayed with 2 decimal places
						+"\n\nTotal: R" + dFormat.format(rs.getDouble("orders.amtToBePaid")) 
						
						+"\n\n" + rs.getString("drivers.driverFName") + " " + rs.getString("drivers.driverLName") + " is nearest to the restaurant and so he will be delivering your order at: "
						+"\n\n" + rs.getString("customers.custStreetAdd") + "\n" + rs.getString("customers.custSuburb")
						+"\n\n" + "If you need to contact the restaurant, their number is: " + rs.getString("restaurants.restTeleNum");
			}
			
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
		
	return fullInvoice;
		
	}
	
}

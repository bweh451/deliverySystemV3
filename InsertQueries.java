package quickFoodMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class that contains methods relative to inserting data into the database
 *
 * @author Brandon Sherriff
 */
public class InsertQueries {
	
	/**
	 * Created a public static instance of the CheckQueries class in order to use its methods throughout this class.
	 */
	public static CheckQueries checkQuery = new CheckQueries();
	
	/**
	 * A method that can be seen as the final (main) method of this class.
	 * This methods calls a few other methods within this class and is used to insert a new order, order items,
	 * ( and if the user hasn't chosen an existing restaurant or customer) restaurant and customer.
	 * 
	 * @param sc, an existing scanner object
	 * @see createNewOrderObject
	 * @see CheckQueries#checkIfRecordExists
	 * @see createNewCustomerObject
	 * @see insertNewCustomerObject
	 * @see createNewRestaurantObject
	 * @see insertNewRestaurantObject
	 * @see createNewOrderItemsObject
	 * @see getTotalOrderAmt
	 * @see insertNewOrderObject
	 * @see assignDriverToOrder
	 * @see insertNewOrderItemsObject
	 * @see getCustOrRestLocation
	 */
	public void insertNewEntries(Scanner sc) {
		
		//Calls method to create a new Order object.
		Order newOrder = createNewOrderObject(sc);
		
		//Checks if the Order object returns null then the following will be printed.
		if(newOrder == null) {
			System.out.println("Oops! Looks like the order already exists. \nPlease try again.");
		}
		
		//If the order object is not null then the following code will execute.
		else {
			
			//Calls a method that checks if the customer the user has entered exists.
			boolean custExists = checkQuery.checkIfRecordExists(newOrder.getCustID(), "customers", "custID");
			
			//If the customer does not exist the following code will be executed.
			if(!custExists) {
				
				//Calls a method to create a new customer object.
				Customer newCust = createNewCustomerObject(sc, newOrder);
				
				//Calls a method that inserts all the data stored within the created customer object, 
				//into its relevant table within the database.
				insertNewCustomerObject(newCust);
			}
			
			//The following code block is similar to the one above, however it deals with a restaurant object.
			//***Code block begins.
			boolean restExists = checkQuery.checkIfRecordExists(newOrder.getRestID(), "restaurants", "restID");
			
			if(!restExists) {
				Restaurant newRest = createNewRestaurantObject(sc, newOrder);
				insertNewRestaurantObject(newRest);
			}
			//***Code block ends.
			
			//The following methods are called to get the location of the customer and the restaurant, respectively, related to the order.
			String custLocation = getCustOrRestLocation(newOrder, "customers", "custLocation", "custID");
			String restLocation = getCustOrRestLocation(newOrder, "restaurants", "restLocation", "restID");
			
			//Checks if the customer location and restaurant location matches, if they don't match the following will be printed to the user
			if(!custLocation.equalsIgnoreCase(restLocation)) {
				
				System.out.println("\nThe customer location (" + custLocation + ") and restaurant location (" + restLocation + ") do not match! "
						+ "This is not allowed! \nPlease try again.");
			}
			
			//If the location do match the following will happen
			else {
				//Calls a method that creates a new OrderItems object.
				OrderItems newOrderItems = createNewOrderItemsObject(sc, newOrder);
				
				//Calls a method that calculates the order total based on the item prices within the OrderItems object.
				double totalAmtToBePaid = getTotalOrderAmt(newOrderItems);
				
				//Sets the "amtToBePaid" variable within the Orders object to what has been calculated by the method above.
				newOrder.setAmtToBePaid(totalAmtToBePaid);
				
				//Inserts the data stored within the Orders object into its relevant table within the database.
				insertNewOrderObject(newOrder);
				
				//Calls a method that assigns a driver to the Orders table, relevant to the order inserted above.
				assignDriverToOrder(newOrder, custLocation);
				
				//Calls a method that inserts the data within the OrderItems object into its relevant table within the database.
				insertNewOrderItemsObject(newOrderItems);
			}

		}
		
	}
	
	/**
	 * A method that creates a new Order object by making use of prompts and user input.
	 * 
	 * @param sc, an existing scanner object
	 * @return order, a new Order object
	 * @see CheckQueries#checkIfNum
	 * @see CheckQueries#checkIfRecordExists
	 */
	public Order createNewOrderObject(Scanner sc) {
		
		//Set Order object to null.
		Order order = null;
		
		System.out.println("Please enter the order number of the order: ");
		
		//Checks if the user has entered an integer and returns said integer
		int orderNum = checkQuery.checkIfNum(sc);
		
		//Checks if the the order exists.
		boolean orderExists = checkQuery.checkIfRecordExists(orderNum, "orders", "orderNum");
		
		//If the order doesn't exist the following code will execute.
		//If the order does in face exist then it will return null.
		if(!orderExists) {

			System.out.println("Please enter the ID of the customer: ");
			int custID = checkQuery.checkIfNum(sc);
			
			System.out.println("Please enter the ID of the restaurant the customer wants to order from: ");
			int restID = checkQuery.checkIfNum(sc);
			
			System.out.println("Please enter the special instructions the customer added to the order: ");
			String specInstruct = sc.nextLine();
			
			
			order = new Order(orderNum, custID, restID, specInstruct, 0);
		
		}
		
		return order;
		
	}
	/**
	 * A method that creates a new Customer object by making use of prompts and user input.
	 * 
	 * @param sc, an existing scanner object
	 * @param order, an existing Order object
	 * @return cust, a new Customer object
	 */
	public Customer createNewCustomerObject(Scanner sc, Order order) {
		
		System.out.println("\nYay, a new customer! We'll need the following information: ");
		
		System.out.println("\nPlease enter the first name of the customer: ");
		String custFName = sc.nextLine();
		
		System.out.println("Please enter the last name of the customer: ");
		String custLName = sc.nextLine();
				
		System.out.println("Please enter the telephone number of the customer: ");
		String custTeleNum = sc.nextLine();
		
		System.out.println("Please enter the email address where the customer resides: ");
		String custEmail = sc.nextLine();
		
		System.out.println("Please enter the street address where the customer resides: ");
		String custStreetAdd = sc.nextLine();
		
		System.out.println("Please enter the suburb where the customer resides: ");
		String custSuburb = sc.nextLine();
		
		System.out.println("Please enter the location (town) where the customer resides: ");
		String custLocation = sc.nextLine();
			
		Customer cust = new Customer (order.getCustID(), custFName, custLName, custTeleNum, custEmail, custStreetAdd, custSuburb, custLocation);
		
		return cust;
		
	}
	
	/**
	 * A method that creates a new Restaurant object by making use of prompts and user input.
	 * 
	 * @param sc, an existing scanner object
	 * @param order, an existing Order object
	 * @return rest, a new Restaurant object
	 */
	public Restaurant createNewRestaurantObject(Scanner sc, Order order) {
		
		System.out.println("\nWow, a new restaurant! We'll need the following information: ");
		
		System.out.println("\nPlease enter the name of the restaurant the customer is ordering from: ");
		String restName = sc.nextLine();
		
		System.out.println("Please enter the location (town) of the restaurant the customer is ordering from: ");
		String restLocation = sc.nextLine();
		
		System.out.println("Please enter the telephone number of the restaurant the customer is ordering from: ");
		String restTeleNum = sc.nextLine();
		
		Restaurant rest = new Restaurant (order.getRestID(), restName, restLocation, restTeleNum);
		
		return rest;
	}
	
	/**
	 * A method that creates a new OrderItems object by making use of prompts and user input.
	 * 
	 * @param sc, an existing scanner object
	 * @param order, an existing Order object
	 * @return orderItems, a new OrderItems object
	 */
	public OrderItems createNewOrderItemsObject(Scanner sc, Order order) {
		
		//Created these ArrayLists because the OrderItems class makes use of ArrayLists
		ArrayList <String> itemNames = new ArrayList<>();
		ArrayList <Integer> itemQtys = new ArrayList<>();
		ArrayList <Double> itemPrices = new ArrayList<>();
		
		String answer = "y";
		int count = 0;
		
		//Forced while loop that continues until the user enters "n" in the last prompt.
		while(answer.equalsIgnoreCase("y")) {
			
			//A counter that adds 1 with every loop.
			//It acts as a counter for the amount of items the user has ordered
			count++;
			
			System.out.println("\nPlease enter the name of the item being ordered: ");
			itemNames.add(sc.nextLine());
			
			System.out.println("Please enter the quantity of the item being ordered: ");
			
			//Method called to checks if the user has entered an integer and returns said integer
			int itemQty = checkQuery.checkIfNum(sc);
			itemQtys.add(itemQty);
			
			System.out.println("Please enter the price of the item being ordered: ");
			
			//Method called to check if the user has entered a double and returns said double
			double itemPrice = checkQuery.checkIfDouble(sc);
			itemPrices.add(itemPrice);
			
			System.out.println("Did the customer order anything else? \nPlease enter either y or n");
			
			//Method called to check if the user has entered either "y" or "n" and returns either "y" or "n" depending on which one the user has entered.
			answer = checkQuery.checkIfYesOrNo(sc);
		}
		
		OrderItems orderItems = new OrderItems (order.getOrderNum(), itemNames, itemQtys, itemPrices, count);
		
		return orderItems;
		
	}
	
	/**
	 * A method that calculates the total price of an order,
	 * depending on the prices and quantities of the items within the OrderItems object.
	 * 
	 * @param orderItems, an existing OrderItems object
	 * @return totalOrderAmt, the total price of the order.
	 */
	public double getTotalOrderAmt(OrderItems orderItems){
		
		double totalOrderAmt = 0;
		
		//Uses the "count" variable within the OrderItems object (acts as a counter for the amount of items the user has ordered).
		for(int i = 0; i < orderItems.getCount(); i++) {
			
			//The price of the items gets multiply with the quantity of the item and then gets added to the "totalOrderAmt" variable with each loop.
			totalOrderAmt += orderItems.getItemPrices().get(i) * orderItems.getItemQtys().get(i);
			
		}
		
		return totalOrderAmt;
	}
	
	/**
	 * Method that inserts an Order object into its relevant table within the database
	 * This method makes use of prepared statement and connection object to connect to the database and execute SQL statements
	 * 
	 * @param order, an existing Order object
	 */
	public void insertNewOrderObject(Order order) {
		try {
			
			//Creates a connection to the specified database
			Connection conn = DBConnection.connectToDB();
			
			//Created a prepared statement in order to enter user input values into the empty value fields of the SQL string
			PreparedStatement statement = conn.prepareStatement("INSERT INTO orders (orderNum, custID, restID, specInstruct, amtToBePaid) VALUES (?, ?, ?, ?, ?)");
			
			//The following sets values below to the "?" fields of the SQL string
			statement.setInt(1, order.getOrderNum());
			statement.setInt(2, order.getCustID());
			statement.setInt(3, order.getRestID());
			statement.setString(4, order.getSpecInstruct());
			statement.setDouble(5, order.getAmtToBePaid());
		
			
			//Calls to method to see if the query was successfully executed.
			checkQuery.checkIfQuerySuccessful(statement, "order", "inserted");
			
			//Closes the prepared statement created earlier.
			statement.close();
			
			//Calls method to close the connection to the database.
			DBConnection.disconnectFromDB(conn);
				
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * Method that inserts a Customer object into its relevant table within the database 
	 * This method makes use of prepared statement and connection object to connect to the database and execute SQL statements
	 * This method is similar to the "insertNewOrderObject" method
	 * 
	 * @param cust, an existing Customer object
	 */
	public void insertNewCustomerObject(Customer cust) {
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = conn.prepareStatement("INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			statement.setInt(1, cust.getID());
			statement.setString(2, cust.getfName());
			statement.setString(3, cust.getlName());
			statement.setString(4, cust.getTeleNum());
			statement.setString(5, cust.getEmail());
			statement.setString(6, cust.getStreetAdd());
			statement.setString(7, cust.getSuburb());
			statement.setString(8, cust.getLocation());
		
			checkQuery.checkIfQuerySuccessful(statement, "customer", "inserted");
			
			statement.close();
			DBConnection.disconnectFromDB(conn);
				
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * Method that inserts a Restaurant object into the relevant table within the database
	 * This method makes use of prepared statement and connection object to connect to the database and execute SQL statements
	 * This method is similar to the "insertNewOrderObject" method
	 * 
	 * @param rest, an existing Restaurant object
	 */
	public void insertNewRestaurantObject(Restaurant rest) {
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = conn.prepareStatement("INSERT INTO restaurants VALUES (?, ?, ?, ?)");
			
			statement.setInt(1, rest.getID());
			statement.setString(2, rest.getName());
			statement.setString(3, rest.getLocation());
			statement.setString(4, rest.getTeleNum());

			checkQuery.checkIfQuerySuccessful(statement, "restaurant", "inserted");
			
			statement.close();
			DBConnection.disconnectFromDB(conn);
				
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Method that inserts a OrderItems object into the relevant table within the database
	 * This method makes use of prepared statement and connection object to connect to the database and execute SQL statements
	 * Since the OrderItems object makes use of ArrayLists, this method uses a for loop to add each item to the database
	 * 
	 * @param orderItems, an existing OrderItems object
	 */
	public void insertNewOrderItemsObject(OrderItems orderItems) {
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			//For loop that inserts an item into the database with each loop
			for(int i = 0; i < orderItems.getCount(); i++) {
				
				//A new statement get created with each loop
				PreparedStatement statement = conn.prepareStatement("INSERT INTO orderItems VALUES (?, ?, ?, ?)");
				
				statement.setInt(1, orderItems.getOrderNum());
				statement.setString(2, orderItems.getItemNames().get(i));
				statement.setInt(3, orderItems.getItemQtys().get(i));
				statement.setDouble(4, orderItems.getItemPrices().get(i));
				
				checkQuery.checkIfQuerySuccessful(statement, "order item", "inserted");
				
				//Each created statement gets closed with each loop
				statement.close();
			}
			
			//Calls method to disconnect from the database
			DBConnection.disconnectFromDB(conn);
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Method that retrieves the location of either a customer or a restaurant relative to the order being inserted
	 * 
	 * @param order, an existing Order object
	 * @param tableName, the name of the table the location is being retrieved from (eg. "customers" or "restaurants")
	 * @param locationType, the type of location being retrieved relative to the table (eg. "custLocation" or "restLocation")
	 * @param iDType, the type of ID being used in the search relative to the table (eg. "custID" or "restID")
	 * @return location, the retrieved location of either the customer or restaurant
	 */
	public String getCustOrRestLocation(Order order, String tableName, String locationType, String iDType) {
		
		String location = "";
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement =  conn.prepareStatement("SELECT " + locationType + " FROM " + tableName + " WHERE " + iDType + " = ?");
			
			//If-else statements that will set the "?" parameter at the end of the PreparedStatement to ID relative to the tableName variable inserted
			//eg. the below if statement will set the ID to the customer ID saved in the Order object
			if(tableName.equals("customers")) {
				statement.setInt(1, order.getCustID());
			}
			
			else if(tableName.equals("restaurants")) {
				statement.setInt(1, order.getRestID());
			}

			ResultSet rs = statement.executeQuery();
			
			//Retrieves the location and sets it to the "location" variable
			while(rs.next()) {
				
				location = rs.getString(tableName + "." + locationType);
			}
			
			//All connections get closed
			statement.close();
			rs.close();
			DBConnection.disconnectFromDB(conn);
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return location;
	}
	
	/**
	 * Method that finds all drivers with the same location as the customer location and then 
	 * gets the smallest load out of the list of drivers.
	 * If the smallest load is equal to -1 then no drivers have been found
	 * 
	 * @param custLocation, the location of the customer retrieved in the "getCustOrRestLocation" method
	 * @return smallestLoad, the smallest load of a driver found with the same location as the customer location or -1 (if -1 gets returned then no driver has been found)
	 * @see getCustOrRestLocation
	 */
	public int getSmallestDriverLoad(String custLocation) {
		
		//Created an ArrayList to add the driver loads to
		ArrayList <Integer> driverLoads = new ArrayList <>();
		
		int smallestLoad = -1;
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = conn.prepareStatement("SELECT driverLoad FROM drivers WHERE driverLocation = ?");
			
			statement.setString(1, custLocation);
			
			ResultSet rs = statement.executeQuery();
			
			//All the results get added to the ArrayList
			while(rs.next()) {
				driverLoads.add(rs.getInt("driverLoad"));
			}
			
			//If the ArrayList is not empty the following will happen
			if(!driverLoads.isEmpty()) {
				
				//The ArrayList gets sorted (the smallest load get moved to the first index of the ArrayList)
				Collections.sort(driverLoads);
				
				//Set the smallestLoad variable to the value at index 0
				smallestLoad = driverLoads.get(0);
			}
			
			//All connections get closed
			statement.close();
			rs.close();
			DBConnection.disconnectFromDB(conn);
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return smallestLoad;
	}
	/**
	 * Method that finds the driver ID of the driver with the smallest load that is in the same location as the customer location
	 * 
	 * @param custLocation, the location of the customer retrieved in the "getCustLocation" method.
	 * @param smallestLoad, the smallest load of a driver found with the same location as the customer location
	 * @return driverID, the ID of the driver with the smallest load in the same location as the customer location
	 * @see getSmallestDriverLoad
	 */
	public int getDriverIdOfSmallestLoad(String custLocation, int smallestLoad) {
		
		int driverID = 0;
		
		try {
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = conn.prepareStatement("SELECT driverID FROM drivers WHERE driverLocation = ? AND driverLoad = ?");
			
			statement.setString(1, custLocation);
			statement.setInt(2, smallestLoad);
			
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				driverID = rs.getInt("driverID");
			}
			
			//All connections get closed
			statement.close();
			rs.close();
			DBConnection.disconnectFromDB(conn);
			
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return driverID;
	}
	
	/**
	 * Method that assigns a driver to an order within the database, based on the driver location and load amount
	 * 
	 * @param order, an existing Order object
	 * @param custLocation, the location of the customer retrieved in the "getCustOrRestLocation" method.
	 * @see getSmallestDriverLoad
	 * @see getDriverIdOfSmallestLoad
	 * @see getCustOrRestLocation
	 */
	public void assignDriverToOrder(Order order, String custLocation) {
		
		//Calls a method that gets the smallest load of a driver relative to the customer location
		int smallestDLoad = getSmallestDriverLoad(custLocation);
		
		//If the method above returns -1 then the following will be printed to the user
		if(smallestDLoad == -1) {
			System.out.println("\nYikes! Looks like there are no drivers in the area of the customers. \nPlease finalise the order to print the corresponding invoice.");
		}
		
		//Else the following will happen
		else {
			
			//Method gets called to find the ID of the Driver with the smallest load relative to the customer location
			int driverID = getDriverIdOfSmallestLoad(custLocation, smallestDLoad);
			
			//The following block of code sets the driverID (of the orders table within the database) to the driverID found in the method called above
			try {
				
				Connection conn = DBConnection.connectToDB();
				
				PreparedStatement statement = conn.prepareStatement("UPDATE orders SET driverID = ? WHERE orderNum = ?");
				
				statement.setInt(1, driverID);
				statement.setInt(2, order.getOrderNum());
				
				checkQuery.checkIfQuerySuccessful(statement, "order's driverID", "updated");
				
				statement.close();
				DBConnection.disconnectFromDB(conn);
				
			}
			
			catch(SQLException e) {
				System.out.println(e);
			}
		}	
	}
}

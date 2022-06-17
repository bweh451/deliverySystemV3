package quickFoodMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Class that contains methods relative to searching for data within the database. 
 *
 * @author Brandon Sherriff
 */
public class SearchQueries {
	
	/**
	 * Created a public static instance of the CheckQueries class in order to use its methods throughout this class.
	 */
	public static CheckQueries checkQuery = new CheckQueries();
	
	/**
	 *Created a public static instance of the DecimalFormat class in order to use its methods throughout this class.
	 */
	public DecimalFormat dFormat = new DecimalFormat("0.00");
	
	/**
	 * Method that searches for an order that the user has chosen
	 * Calls the "searchByOrderNumOrCustName" method in order to execute the correct statement,
	 * depending if the user has chosen to search by the order number of the order of the customer name of the order
	 * 
	 * @param sc, an existing Scanner object
	 * @see searchByOrderNumOrCustName
	 */
	public void searchOrder(Scanner sc){
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = null;
			
			//Calls method that returns the relative statement to what the user chose to search by
			statement = searchByOrderNumOrCustName(sc, statement, conn);
			
			//Checks if the does not equal null, if not then it will create a ResultSet object.
			if(statement != null) {
				ResultSet rs = statement.executeQuery();
				
				//If the ResultSet has nothing in it, the following will be printed.
				if(!rs.next()) {
					System.out.println("\nNo orders have been found!");
					
				}
				
				//Else if will print the following from the ResultSet until the ResultSet is empty.
				else {
					
					do {
						System.out.println("\n" + rs.getInt("orders.orderNum") + ", " + rs.getInt("orders.custID") + ", " + rs.getInt("orders.restID") + ", " + rs.getString("orders.specInstruct")
						
						//The DecimalFormat .format method gets uses to format the price (amtToBePaid) to two decimal places
						+ ", " + dFormat.format(rs.getDouble("orders.amtToBePaid")) + ", " + rs.getString("orders.orderStatus") + ", " + rs.getInt("orders.driverID"));
						
					}
					
					while (rs.next());
					
					statement.close();
				}
			}
			
			DBConnection.disconnectFromDB(conn);
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * Method that creates the relevant statement depending on whether the user has chosen to search for an order by customer name or order number
	 * 
	 * @param sc, an existing Scanner object
	 * @param statement, PreparedStatement that has been initialised with a null value
	 * @param conn, a connection object that gets used to fully initialise the prepared statement
	 * @return statement, a prepared statement relevant to what the user has chosen to search by
	 * @throws SQLException when an SQLException has been found
	 */
	public PreparedStatement searchByOrderNumOrCustName(Scanner sc, PreparedStatement statement, Connection conn) throws SQLException  {
		
		
		System.out.println("What would you like to search by: ");
		System.out.println("1. Customer name");
		System.out.println("2. Order number");
		System.out.println("\nPlease enter your choice:");
		
		int userChoice = checkQuery.checkIfNum(sc);
		
		//The if-else statement here are similar
		//For example in this if statement, if the user chooses to search by customer name the following prompts will get shown to the user.
		//After the user has entered information relative to the prompts an relevant statement gets created.
		if(userChoice == 1) {
			
			System.out.println("Please enter the first name of the customer who's order(s) you would like to search for: ");
			String custFName = sc.nextLine();
			
			System.out.println("Please enter the last name of the customer who's order(s) you would like to search for: ");
			String custLName = sc.nextLine();
			
			statement = conn.prepareStatement("SELECT orders.* FROM orders INNER JOIN customers ON orders.custID = customers.custID WHERE customers.custFName = ? AND customers.custLName = ?");
			
			statement.setString(1, custFName);
			statement.setString(2, custLName);
			
		}
		
		else if(userChoice == 2){
			
			System.out.println("Please enter the order number you would like to search for: ");
			int orderNum = checkQuery.checkIfNum(sc);
			
			statement = conn.prepareStatement("SELECT * FROM orders WHERE orderNum = ?");
			
			statement.setInt(1, orderNum);
			
		}
		
		//If the use has not entered either 1 or 2, then the following will be printed
		else {
			System.out.println("\nInvalid input! Please enter either 1 or 2. \nPlease try again.");
		}
		
		return statement;
		
	}
	
	/**
	 * Method that searches for all orders that have been marked as "Not Finalised" within the database
	 * This method is similar to the "searchOrder" method
	 * 
	 * @see searchOrder
	 */
	public void searchUnfinalisedOrders() {
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM orders WHERE orderStatus = 'Not Finalised'");
			
			//Checks if there are no results in the ResultSet.
			//If there are no results then it will print out the following.
			if(!rs.next()) {
				System.out.println("\nThere are no unfinalised orders.");
				
			}
			
			//Else if will print the following from the ResultSet until the ResultSet is empty.
			else {
				
				do {
					System.out.println(rs.getInt("orders.orderNum") + ", " + rs.getInt("orders.custID") + ", " + rs.getInt("orders.restID") + ", " + rs.getString("orders.specInstruct")
					+ ", " + dFormat.format(rs.getDouble("orders.amtToBePaid")) + ", " + rs.getString("orders.orderStatus") + ", " + rs.getInt("orders.driverID"));
				}
				
				while (rs.next());
			}

			statement.close();
			DBConnection.disconnectFromDB(conn);
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Method that prints all data within the table the user has chosen via user prompts and user input
	 * This method is similar to the "searchOrder" method although it uses if else statements to search and return results for the relevant table the user has chosen
	 * 
	 * @param sc, an existing Scanner object
	 * @see searchOrder
	 */
	public void displayAllFromTable (Scanner sc) {
		
		System.out.println("\nPlease select one of the options below:");
		System.out.println("1. Display all orders");
		System.out.println("2. Display all order items");
		System.out.println("3. Display all customers");
		System.out.println("4. Display all restaurants");
		System.out.println("5. Display all drivers");
		System.out.println("\nPlease enter your choice:");
		
		int userChoice = checkQuery.checkIfNum(sc);
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = null;
			
			//All the if and else-if statements are similar
			//For example in the if statement below if the user has entered "1",
			//it will then print everything in the orders table.
			//Every if-else statement will print all the data within a different table related to the prompts above.
			if(userChoice == 1) {
				
				rs = statement.executeQuery("SELECT * FROM orders");
				
				while(rs.next()) {
					System.out.println(rs.getInt("orderNum") + ", " + rs.getInt("custID") + ", " + rs.getInt("restID") + ", " + rs.getString("specInstruct")
					+ " , " + dFormat.format(rs.getDouble("amtToBePaid")) + ", " + rs.getString("orderStatus") + ", " + rs.getInt("driverID"));
				}
				
				rs.close();
			}
			
			
			else if(userChoice == 2) {
				
				rs = statement.executeQuery("SELECT * FROM orderItems");
				
				while(rs.next()) {
					System.out.println(rs.getInt("orderNum") + ", " + rs.getString("itemName") + ", " 
					+ rs.getInt("itemQty") + ", " + dFormat.format(rs.getDouble("itemPrice"))); 
				}
				
				rs.close();
			}
			
			
			else if(userChoice == 3) {
				
				rs = statement.executeQuery("SELECT * FROM customers");
				
				while(rs.next()) {
					System.out.println(rs.getInt("custID") + ", " + rs.getString("custFName") + ", " + rs.getString("custLName") + ", " 
					+ rs.getString("custTeleNum") + ", " + rs.getString("custEmail") + ", " + rs.getString("custStreetAdd") + ", " 
					+ rs.getString("custSuburb") + ", " + rs.getString("custLocation"));
				}
				
				rs.close();
			}
			
			
			else if(userChoice == 4) {
				
				rs = statement.executeQuery("SELECT * FROM restaurants");
				
				while(rs.next()) {
					System.out.println(rs.getInt("restID") + ", " + rs.getString("restName") + ", " + rs.getString("restLocation") 
					+ ", " + rs.getString("restTeleNum"));
				}
				
				rs.close();
			}
			
			
			else if(userChoice == 5) {
				
				rs = statement.executeQuery("SELECT * FROM drivers");
				
				while(rs.next()) {
					System.out.println(rs.getInt("driverID") + ", " + rs.getString("driverFName") + ", " + rs.getString("driverLName") + ", " + rs.getString("driverLocation")
					 + ", " + rs.getInt("driverLoad"));
				}
		
				rs.close();
			}
			
			//If the user hasn't entered either 1, 2, 3, 4 or 5 then the following will be printed
			else {
				System.out.println("Invalid input! Please enter either 1, 2, 3, 4 or 5. \nPlease try again.");
			}
			
			statement.close();
			DBConnection.disconnectFromDB(conn);
			
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
	}
}

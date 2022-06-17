package quickFoodMS;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class that contains methods relative to checking if certain things are correct
 *
 * @author Brandon Sherriff
 */
public class CheckQueries {
	
	/**
	 * Method that checks if an SQL query was successfully executed
	 * 
	 * @param statement, an existing statement
	 * @param entity, the type of entity being queried (eg. order, customer, restaurant etc.)
	 * @param typeOfQuery, the type of query being executed (eg. insert or update)
	 * @throws SQLException when an SQLException has been found
	 */
	public void checkIfQuerySuccessful(PreparedStatement statement, String entity, String typeOfQuery) throws SQLException {
		
		//If the executeUpdate method returns 1 or greater, then the query has been successfully completed.
		//And it will print out the following.
		if(statement.executeUpdate() >= 1) {
			System.out.println("\nThe " + entity + " has been successfully " + typeOfQuery + "!");
		}
		
		//Else the query was unsuccessful and it will print out the following.
		else {
			System.out.println("\nOops! Something went wrong. \nThe " + entity + " has not been successfully " + typeOfQuery + "!");
		}
	}
	
	/**
	 * Created a method that checks if a record exists within the database
	 * 
	 * @param recordID, the ID of the record being checked (eg. customer ID, restaurant ID, order number etc.)
	 * @param entityName, the name of the entity being checked (eg. orders, customers, restaurants etc.)
	 * @param IdType, the type of ID being checked (eg. "custID", "restID", "orderNum" etc.)
	 * @return bool, a boolean that's either true or false
	 */
	public boolean checkIfRecordExists(int recordID, String entityName, String IdType) {
		
		boolean bool = true;
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + entityName + " WHERE " + IdType + " = ?");
			
			statement.setInt(1, recordID);
			
			ResultSet rs = statement.executeQuery();
			
			//If the ResultSet is empty then the "bool" variable gets set to false.
			if(!rs.next()) {
				
				bool = false;
			}
		
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return bool;
	}

	/**
	 * Method that checks if the user has entered an integer by parsing what they have entered to an integer.
	 * If a NumberFormatException is caught, then the user will be asked to retry and enter a number until they've entered a integer
	 * 
	 * @param sc, an existing Scanner object
	 * @return num, a valid integer the user has entered
	 */
	public int checkIfNum (Scanner sc) {
		int num = 0;
		boolean notInt = true;
		
		while(notInt) {
			
			try {
				num = Integer.parseInt(sc.nextLine());
				notInt = false;
			}
			
			catch(NumberFormatException e) {
				System.out.println("Invalid input! Please enter a number: ");
			}
		}
			
		return num;
	}
	
	/**
	 * Method that checks if the user has entered either "y" or "n"
	 * This method gets called within the createNewOrderItemsObject method in the InsertQueries class.
	 * 
	 * @param sc, an existing Scanner object
	 * @return yesOrNo, a valid response the user has entered (either "y" or "n")
	 */
	public String checkIfYesOrNo(Scanner sc) {
		
		String yesOrNo = sc.nextLine();
		boolean notYesOrNo = true;
		
		while(notYesOrNo) {
			
			if(yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("n")) {
				notYesOrNo = false;
			}
			
			else {
				System.out.println("Invalid input! Please enter either 'y' or 'n'.");
				yesOrNo = sc.nextLine();
			}
		}

		return yesOrNo;
	}
	
	/**
	 * Method that checks if the user has entered a double by parsing what they have entered to a double
	 * The double then gets rounded to two decimal places
	 * If a NumberFormatException is caught, then the user will be asked to retry and enter a number until they've entered a double
	 * 
	 * @param sc, an existing Scanner object
	 * @return num, a valid double the user has entered
	 */
	public double checkIfDouble(Scanner sc) {
		
		double num = 0;
		boolean notDouble = true;

		while(notDouble) {
			
			try {
				
				num = Double.parseDouble(sc.nextLine());
				BigDecimal formatDouble = new BigDecimal(num).setScale(2, RoundingMode.HALF_UP);
				num = formatDouble.doubleValue();
				notDouble = false;
			}
			
			catch(NumberFormatException e) {
				System.out.println("Invalid input! Please enter a number: ");
			}
			
		}
		
		return num;
	}
	
	/**
	 * Method that checks if the order the user has selected has a driver assigned to it or not
	 * If the method returns 0 then no driver has been found for the order
	 * 
	 * @param orderNum, the order number or the order the invoice is being generated from 
	 * (entered in the "finaliseOrder" method and passed through the "writeInvoice" method)
	 * 
	 * @return driverID, either the ID of the driver found or 0 (if the method returns 0, then no driverID has been found)
	 */
	public int checkIfOrderHasDriver (int orderNum) {
		
		int driverID = 0;
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = conn.prepareStatement("SELECT driverID FROM orders WHERE orderNum = ?");
			
			statement.setInt(1, orderNum);
			
			ResultSet rs = statement.executeQuery();
			
			//If there are results then the driverID gets set to the ID that has been found
			while(rs.next()) {
				driverID = rs.getInt("driverID");
			}
		
		}
		
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return driverID;
	}
	
}

//I used the following resource in order to correctly round a double to two decimal places:
//mkyon.com. 2021. Java – Display double in 2 decimal places. [online] Available at: <https://mkyong.com/java/java-display-double-in-2-decimal-points/#:~:text=format(“%25.,double%20to%202%20decimal%20places.> [Accessed 24 May 2022].

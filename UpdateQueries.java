package quickFoodMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class that contains methods relative to updating data inside certain tables within the database 
 *
 * @author Brandon Sherriff
 */
public class UpdateQueries {
	
	/**
	 * Created a public static instance of the CheckQueries class in order to use its methods throughout this class.
	 */
	public static CheckQueries checkQuery = new CheckQueries();
	
	/**
	 * This method can be seen as the final method related to updating the data of a customer within the customers table inside of the database
	 * This method calls other method related to updating the data within the customers table
	 * 
	 * @param sc, an existing scanner object
	 * @see CheckQueries#checkIfNum
	 * @see CheckQueries#checkIfRecordExists
	 */
	public void updateCustomer(Scanner sc) {
		
		//Prompts user to enter the project number of the project they'd like to update.
		System.out.println("Please enter the customer ID of the customer you would like to update: ");
		
		//Checks if the user has entered an integer, this method gets used often when the user is expected to enter a number.
		int custToUpdate = checkQuery.checkIfNum(sc);

		//Calls method to check if the project already exists.
		boolean custExists = checkQuery.checkIfRecordExists(custToUpdate, "customers", "custID");
		
		//If the customer exists then the following methods will be called.
		if(custExists) {
			
			int chosenField = chooseWhatToUpdate(sc);
			checkWhatToUpdateAndDoUpdate(sc, chosenField, custToUpdate);	
		}
		
		//If the customer does not exist then the following will be printed out to the user.
		else {
			System.out.println("The customer ID you have entered does not exist within the database. \nPlease try again.");
		}
	}
	
	/**
	 * Method that consists of prompts that ask the user what information they'd like to update of the customer they have chosen
	 * 
	 * @param sc, an existing scanner object
	 * @return userChoice, the number the user has entered from the list of prompts
	 */
	public int chooseWhatToUpdate(Scanner sc) {
		
		System.out.println("\nWhich of the following would you like to update: ");
		System.out.println("1. Customer's ID");
		System.out.println("2. Customer's first name");
		System.out.println("3. Customer's last name");
		System.out.println("4. Customer's telephone number");
		System.out.println("5. Customer's email address");
		System.out.println("6. Customer's street address");
		System.out.println("7. Customer's suburb");
		System.out.println("8. Customer's location (town)");
		System.out.println("\nPlease enter your choice:");
		
		int userChoice = checkQuery.checkIfNum(sc);
	
		return userChoice;
	}
	
	/**
	 * Method that consist of if-else statements that gives the user a specific prompt of what to enter depending on
	 * what they have chosen in the "choosWhatToUpdate" method
	 * This method updates the information of a customer, to the new information the user has entered
	 * 
	 * @param sc, an existing Scanner object
	 * @param chosenField, the number the user has entered in the "chooseWhatToUpdate" method
	 * @param custToUpdate, the customer ID of the customer the user wants to update (the user has entered this in the "updateCustomer" method)
	 * @see updateCustomer
	 * @see chooseWhatToUpdate
	 * @see doUpdateForInts
	 * @see doUpdateForStrings
	 * @see CheckQueries#checkIfNum
	 */
	public void checkWhatToUpdateAndDoUpdate(Scanner sc, int chosenField, int custToUpdate) {

		//Each prompt that requires the user to enter a number will call the "checkIfNum" method
		//If the user has chosen to update something that requires a number, then the "doUpdateForInts" method will be called
		//If the user has chosen to update something that requires a text (like a string), then the "doUpdateForStrings" method will be called
		//These methods will be explained in more detail below
		if(chosenField == 1) {
			System.out.println("Please enter the new customer ID: ");
			int newcustID = checkQuery.checkIfNum(sc);
			doUpdateForInts(newcustID, "custID", custToUpdate);
		}
		
		else if(chosenField == 2) {
			System.out.println("Please enter the new first name of the customer: ");
			String newCustFName = sc.nextLine();
			doUpdateForStrings(newCustFName, "custFName", custToUpdate);
		}
		
		else if(chosenField == 3) {
			System.out.println("Please enter the new last name of the customer: ");
			String newCustLName = sc.nextLine();
			doUpdateForStrings(newCustLName, "custLName", custToUpdate);
		}
		
		else if(chosenField == 4) {
			System.out.println("Please enter the new telephone number of the customer: ");
			String newCustTeleNum = sc.nextLine();
			doUpdateForStrings(newCustTeleNum, "custTeleNum", custToUpdate);
		}
		
		else if(chosenField == 5) {
			System.out.println("Please enter the new email address of the customer: ");
			String newCustEmail = sc.nextLine();
			doUpdateForStrings(newCustEmail, "custEmail", custToUpdate);
		}
		
		else if(chosenField == 6) {
			System.out.println("Please enter the new street address where the customer resides: ");
			String newCustStreetAdd = sc.nextLine();
			doUpdateForStrings(newCustStreetAdd, "custStreetAdd", custToUpdate);
		}
		
		else if(chosenField == 7) {
			System.out.println("Please enter the new suburb where the customer resides: ");
			String newCustSuburb = sc.nextLine();
			doUpdateForStrings(newCustSuburb, "custSuburb", custToUpdate);
		}
		
		else if(chosenField == 8) {
			System.out.println("Please enter the new location (town) where the customer resides: ");
			String newCustLocation = sc.nextLine();
			doUpdateForStrings(newCustLocation, "custLocation", custToUpdate);
		}
		
		//If the user has not entered either numbers 1 - 8 then the following will get printed
		else {
			System.out.println("Invalid input! Please enter the numbers 1 - 8. \nPlease try again.");
		}
	}
	
	/**
	 * Method that inserts the updated data into the database if the data is of type int
	 * 
	 * @param newDetails, the updated details the user has entered
	 * @param fieldName, the field in the customers table the user wants to insert the updated details into
	 * @param custToUpdate, the customer ID of the customer the user wants to update
	 */
	public void doUpdateForInts(int newDetails, String fieldName, int custToUpdate) {
	
			
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = conn.prepareStatement("UPDATE customers SET " + fieldName + " = ? WHERE custID = ?");
			
			statement.setInt(1, newDetails);
			statement.setInt(2, custToUpdate);
			
			checkQuery.checkIfQuerySuccessful(statement, "customer", "udpated");
			
			statement.close();
		
			
			DBConnection.disconnectFromDB(conn);
			
		}
		
		catch(SQLException e){
			System.out.println(e);
		}
		
	}
	
	/**
	 * Method that inserts the updated data into the database if the data is of type String
	 * 
	 * @param newDetails, the updated details the user has entered
	 * @param fieldName, the field in the customers table the user wants to insert the updated details into
	 * @param custToUpdate, the project number of the project the user wants to update
	 */
	public void doUpdateForStrings(String newDetails, String fieldName, int custToUpdate) {
		
		
		try {
			
			Connection conn = DBConnection.connectToDB();
			
			PreparedStatement statement = conn.prepareStatement("UPDATE customers SET " + fieldName + " = ? WHERE custID = ?");
			
			statement.setString(1, newDetails);
			statement.setInt(2, custToUpdate);

			checkQuery.checkIfQuerySuccessful(statement, "customer", "updated");
			
			statement.close();
			DBConnection.disconnectFromDB(conn);
			
		}
		
		catch(SQLException e){
			System.out.println(e);
		}
		
	}
	
	/**
	 * Method that sets the order status of a selected order to "Finalised" and then generates an invoice with all the necessary details
	 * 
	 * @param sc, an existing Scanner object
	 * @see Invoice#writeInvoice
	 */
	public void finaliseOrder(Scanner sc) {
		
		System.out.println("Please enter the order number of the order you would like to finalise:");
		
		//Calls method to check if the user has entered an integer
		int orderToFinalise = checkQuery.checkIfNum(sc);
		
		//Calls a method to see if the order user as chosen exists
		boolean orderExists = checkQuery.checkIfRecordExists(orderToFinalise, "orders", "orderNum");
		
		//If the order does not exists the following will be printed
		if(!orderExists) {
			System.out.println("\nThe order does not exists within the database.");
		}
		
		//Else the order status of the order will be set to "Finalised" and the relevant invoice will be generated
		else {
			
			try {
				
				Connection conn = DBConnection.connectToDB();
				
				PreparedStatement statement = conn.prepareStatement("UPDATE orders SET orderStatus = 'Finalised' WHERE orderNum = ?");
				
				statement.setInt(1, orderToFinalise);

				checkQuery.checkIfQuerySuccessful(statement, "order", "finalised");
				
				statement.close();
				DBConnection.disconnectFromDB(conn);
			}
			
			catch(SQLException e){
				System.out.println(e);
			}
			
			//An invoice object gets created in order to use its methods
			Invoice invoiceWriter = new Invoice();
			
			//Calls a method to write the necessary details to the invoice
			invoiceWriter.writeInvoice(orderToFinalise);
			
			//The following gets printed after the invoice has been written
			System.out.println("Please have a look at the written invoice.");
		}
	}
}

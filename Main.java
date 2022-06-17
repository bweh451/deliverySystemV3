package quickFoodMS;

import java.util.Scanner;

/**
 * Main class
 * @author Brandon Sherriff
 */
public class Main {
	
	/**
	 * Main method that executes methods being called,
	 * depending on what the user has chosen
	 *
	 * @param args, the command line arguments
	 */
	public static void main(String[] args) {
		
		//Created try-catch block to manage exceptions
		try {
			
			//Created a the following objects in order to use their methods.
			InsertQueries insert = new InsertQueries();
			UpdateQueries update = new UpdateQueries();
			SearchQueries search = new SearchQueries();
			CheckQueries check = new CheckQueries ();
			
			//Created a scanner object to read user input.
			Scanner scan = new Scanner(System.in);
			
			//Initialised variable to 0, will be used to show what the user chose in the menu.
			int userChoice = 0;
			
			//Do-while loop that prints out the following menu while the userChoice is not equal to 0.
			do {
				
				System.out.println("\nPlease select one of the options below:");
				System.out.println("1. Enter a new order");
				System.out.println("2. Update customer information");
				System.out.println("3. Search for an order");
				System.out.println("4. Search all unfinalised orders");
				System.out.println("5. Finalise an order");
				System.out.println("6. Display all information");
				System.out.println("0. Exit");
				System.out.println("\nPlease enter your choice:");
				
				//Checks if the user has entered an integer and returns said integer.
				userChoice = check.checkIfNum(scan);
				
				
				//The following if and else-if statements determines which query to execute by the choice the user has made.
				//For example the below query will insert a new order and order items (if the user has not selected an existing restaurant or customer,
				//it will also insert a new customer and restaurant).
				if(userChoice == 1) {
					insert.insertNewEntries(scan);
				}
				
				else if(userChoice == 2) {
					update.updateCustomer(scan);
				}
				
				else if(userChoice == 3) {
					search.searchOrder(scan);
				}
			
				else if(userChoice == 4) {
					search.searchUnfinalisedOrders();
				}
				
				else if(userChoice == 5) {
					update.finaliseOrder(scan);
				}
				
				else if(userChoice == 6) {
					search.displayAllFromTable(scan);
				}
				
				//If the user presses 0, then the following will be printed.
				else if (userChoice == 0) {
					System.out.println("\nProgram exited! See you soon!");
				}
				
				//If the user did not enter a number from 0 to 6 then the following will be printed to the user.
				else {
					System.out.println("\nInvalid number! Please enter either 1, 2, 3, 4, 5, 6 or 0. \nPlease try again.");
				}

			}
			 //If the user enters 0 the loop will be terminated.
			 while (userChoice != 0);
			
			//Closed scanner object.
			scan.close();
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
			
	}

}

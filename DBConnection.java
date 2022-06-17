package quickFoodMS;
import java.sql.*;
/**
 * Class that contains methods relative to connecting and disconnecting to and from database. 
 *
 * @author Brandon Sherriff
 */
public class DBConnection {

	//Created static variable that will be passed through a parameters for the connection object.
	static String url = "jdbc:mysql://localhost:3306/QuickFoodMS?&useSSL=false";
	static String user = "otheruser";
	static String password = "swordfish";
	
	/**
	 * Created a method creates a connection to the specific database being used
	 * @return connection, a connection to the specified database
	 */
	public static Connection connectToDB(){
		
		//Set the connection object to null.
		Connection connection = null;
		
		//Created a try-catch block to manage SQLExceptions
		try {
			
			//Connects to the QuickFoodMS database by using the getConnected method from the driver manager class.
			 connection = DriverManager.getConnection(url, user, password);
			
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		//Returns the connection.
		return connection;

	}
	
	/**
	 * Created a method that allow the connection to the database to be terminated
	 * @param connection, an existing connection
	 */
	public static void disconnectFromDB(Connection connection) {
		
		//Created a try-catch block to manage SQLExceptions.
		try{
			
			//Checks if the connection is active.
			//If so then it will close the connection.
			if (connection != null) {
				connection.close();
			}
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
			
	}
	
}

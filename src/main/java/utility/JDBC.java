package utility;

import java.sql.Connection;
import java.sql.DriverManager;

/** Abstract Class JDBC is used to open and close the connection to the database.
 * It contains two methods, one to open the connection and one to close the connection,
 * to the client_schedule MySQL database.
 * @author Kush Mirchandani*/
public abstract class JDBC {

    /** The protocol used to connect to the database. */
    private static final String protocol = "jdbc";

    /** The vendor of the database being connected to. */
    private static final String vendor = ":mysql:";

    /** The location of the database being connected to. */
    private static final String location = "//localhost/";

    /** The name of the database being connected to. */
    private static final String databaseName = "client_schedule";

    /** The string of the URL for the database being connected to. */
    private static final String jdbcURL = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";

    /** The driver used to connect to the database. */
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    /** The name of the user logging into the database. */
    private static final String userName = "sqlUser";

    /** The password needed to login to the database. */
    private static final String password = "Passw0rd!";

    /** Create a Connection variable to connect to the database. */
    public static Connection connection;

    /** Opens the connection to the database.
     * This method opens the connection between the Java program and the client_schedule MySQL database.
     * It displays a message in the console when the connection has been made. */
    public static void  openConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection Successful");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Closes the connection to the database.
     * This method closes the connection between the Java program and the client_schedule MySQL database.
     * It displays a message in the console when the connection has been closed. */
    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Connection Closed");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;
import utility.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class UserDao is used to perform a number of actions for managing users.
 * It contains an observable list of all users created in the application, and
 * a method for getting an observable list of all users.  It also contains
 * a method to find a user whose name matches a string input into the application.
 * @author Kush Mirchandani*/
public abstract class UserDao {

    /** declare and initialize an observable list for all users */
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    // getUser isn't used.
    /*public static void getUser() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String userPassword = rs.getString("Password");
            System.out.print(userId + " | ");
            System.out.print(userName + " | ");
            System.out.print(userPassword + "\n");
        }
    }*/

    /** This method gets all user objects that have been created.
     * @return the observable list of all users */
    public static ObservableList<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String userPassword = rs.getString("Password");

            User userResult = new User(userId, userName, userPassword);
            allUsers.add(userResult);
        }
            return  allUsers;
    }

    /** This method iterates through every user in the all users list and adds a user to the namedUser list
     * whose name field match the string passed in.
     * @param userName the string to search for.
     * @return the user containing the string passed in */
    public static ObservableList<User> lookupUser(String userName) {
        ObservableList<User> namedUser = FXCollections.observableArrayList();

        for (User user : allUsers) {
            if (user.getName().equals(userName)) {
                namedUser.add(user);
            }
        }
        return namedUser;
    }
}

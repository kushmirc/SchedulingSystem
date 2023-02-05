package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;
import utility.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {

    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    public static void getUser() throws SQLException {
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
    }

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

            //System.out.println(allUsers);
        }
            return  allUsers;

    }
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

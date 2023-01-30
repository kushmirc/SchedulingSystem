package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import utility.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerDao {

    /*public static void select() throws SQLException {
        String sql = "SELECT * FROM fruits";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int fruitId = rs.getInt("Fruit_ID");
            String fruitName = rs.getString("Fruit_Name");
            System.out.print(fruitId + " | " );
            System.out.print(fruitName + "\n");
        }
    }*/

    public static void getCustomer() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPostalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            int customerDivisionId = rs.getInt("Division_ID");
            System.out.print(customerId + " | ");
            System.out.print(customerName + " | ");
            System.out.print(customerAddress + " | ");
            System.out.print(customerPostalCode + " | ");
            System.out.print(customerPhone + " | ");
            System.out.print(customerDivisionId + "\n");
        }
    }

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPostalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            int customerDivisionId = rs.getInt("Division_ID");

            Customer customerResult = new Customer(customerId, customerName, customerAddress, customerPostalCode, customerPhone, customerDivisionId);
            allCustomers.add(customerResult);

            System.out.println(allCustomers);
        }
            return  allCustomers;

    }

}

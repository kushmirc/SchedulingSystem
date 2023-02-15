package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import utility.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class CustomersDao is used to perform a number of actions for managing customers.
 * It contains a method for getting an observable list of all customers.  It also contains
 * methods to insert, update and delete customers from the client_schedule MySQL database.
 * @author Kush Mirchandani*/
public abstract class CustomerDao {

    // getCustomer isn't used.
    /*public static void getCustomer() throws SQLException {
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
    }*/

    /** This method gets all customer objects that have been created.
     * @return the observable list of all customers */
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
            //System.out.println(allCustomers);
        }
            return  allCustomers;
    }

    /** This method inserts a new appointment record into the appointments table of the client_schedule MySQL database.
     * @param name the name of the customer.
     * @param address the address of the customer.
     * @param postalCode the postal code of the customer.
     * @param phone the phone number of the customer.
     * @param divisionId the division ID associated with the customer.
     * @return the number of rows inserted */
    public static int insertCustomer(String name, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method updates an existing customer record with each column value passed into it.
     * @param customerId the customer ID of the customer.
     * @param name the name of the customer.
     * @param address the address of the customer.
     * @param postalCode the postal code of the customer.
     * @param phone the phone number of the customer.
     * @param divisionId the division ID associated with the customer.
     * @return the number of rows updated */
    public static int updateCustomer(int customerId, String name, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method deletes a customer record from the customers table of the client_schedule MySQL database.
     * @param customerId the customer ID of the customer.
     * @return the number of rows deleted */
    public static int deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}

package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import utility.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AppointmentDao {



    public static void getAppointment() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            System.out.print(appointmentId + " | ");
            System.out.print(appointmentTitle + " | ");
            System.out.print(appointmentDescription + " | ");
            System.out.print(appointmentLocation + " | ");
            System.out.print(appointmentType + " | ");
            System.out.print(appointmentStart + " | ");
            System.out.print(appointmentEnd + " | ");
            System.out.print(customerId + " | ");
            System.out.print(userId + " | ");
            System.out.print(contactId + "\n");
        }
    }

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId);
            allAppointments.add(appointmentResult);

            //System.out.println(allAppointments);
        }
            return  allAppointments;

    }

    public static int insertAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int updateAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static ObservableList<Appointment> allAppointmentsByType = FXCollections.observableArrayList();

    public static ObservableList<Appointment> appointmentsByType(String selectedType) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Type = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, selectedType);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId);
            allAppointmentsByType.add(appointmentResult);
        }
        return allAppointmentsByType;
    }

    public static ObservableList<Appointment> allAppointmentsByMonth = FXCollections.observableArrayList();

    public static ObservableList<Appointment> appointmentsByMonth(String selectedLocalDateTime) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE SUBSTRING(Start,6,2) = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, selectedLocalDateTime);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId);
            allAppointmentsByMonth.add(appointmentResult);
        }
        //System.out.println(allAppointmentsByCustomerId.size());
        return allAppointmentsByMonth;
    }

    public static ObservableList<Appointment> allAppointmentsByContact = FXCollections.observableArrayList();

    public static ObservableList<Appointment> appointmentsByContact(String selectedContact) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, selectedContact);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId);
            allAppointmentsByContact.add(appointmentResult);
        }
        return allAppointmentsByContact;
    }

    public static ObservableList<Appointment> allAppointmentsByCustomerId = FXCollections.observableArrayList();

    public static ObservableList<Appointment> appointmentsByCustomerId(int selectedCustomer) throws SQLException {
        String sql = "SELECT appointments.* FROM appointments, customers WHERE appointments.Customer_ID = customers.Customer_ID AND customers.Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, selectedCustomer);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId);
             allAppointmentsByCustomerId.add(appointmentResult);
        }
        //System.out.println(allAppointmentsByCustomerId.size());
        return allAppointmentsByCustomerId;
    }

}

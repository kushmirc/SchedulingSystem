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
            Timestamp appointmentStart = rs.getTimestamp("Start");
            Timestamp appointmentEnd = rs.getTimestamp("End");
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
            Timestamp appointmentStart = rs.getTimestamp("Start");
            Timestamp appointmentEnd = rs.getTimestamp("End");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId);
            allAppointments.add(appointmentResult);

            //System.out.println(allAppointments);
        }
            return  allAppointments;

    }

}

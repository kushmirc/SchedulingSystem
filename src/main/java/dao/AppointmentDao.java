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
import java.time.ZoneId;
import java.time.ZonedDateTime;

/** Class AppointmentsDao is used to perform a number of actions for managing appointments.
 * It contains methods for getting observable lists of all appointments, and for appointments
 * starting within seven days and thirty days. It contains methods to insert, update and
 * delete appointments from the client_schedule MySQL database. It also contains methods to
 * return lists of appointments filtered by type, month, contact, and customer ID.
 * @author Kush Mirchandani*/
public abstract class AppointmentDao {

// getAppointment isn't used.
    /*public static void getAppointment() throws SQLException {
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
    }*/

    /** This method gets all appointment objects that have been created.
     * @return the observable list of all appointments */
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
            LocalDateTime appointmentStartLdt = rs.getTimestamp("Start").toLocalDateTime();
            //ZonedDateTime appointmentStartZonedUtc = appointmentStartLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentStartZonedLocal = appointmentStartZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentStartLdtLocal = appointmentStartZonedLocal.toLocalDateTime();
            LocalDateTime appointmentEndLdt = rs.getTimestamp("End").toLocalDateTime();
            //ZonedDateTime appointmentEndZonedUtc = appointmentEndLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentEndZonedLocal = appointmentEndZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentEndLdtLocal = appointmentEndZonedLocal.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStartLdt, appointmentEndLdt, customerId, userId, contactId);
            allAppointments.add(appointmentResult);
        }
            return  allAppointments;
    }

    /** This method gets all appointment objects that are scheduled to start within the next seven days.
     * @return the observable list of filtered appointments */
    public static ObservableList<Appointment> getSevenDaysAppointments() throws SQLException {
        ObservableList<Appointment> sevenDaysAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Start <= UTC_TIMESTAMP() + INTERVAL 7 DAY";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartLdt = rs.getTimestamp("Start").toLocalDateTime();
            //ZonedDateTime appointmentStartZonedUtc = appointmentStartLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentStartZonedLocal = appointmentStartZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentStartLdtLocal = appointmentStartZonedLocal.toLocalDateTime();
            LocalDateTime appointmentEndLdt = rs.getTimestamp("End").toLocalDateTime();
            //ZonedDateTime appointmentEndZonedUtc = appointmentEndLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentEndZonedLocal = appointmentEndZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentEndLdtLocal = appointmentEndZonedLocal.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStartLdt, appointmentEndLdt, customerId, userId, contactId);
            sevenDaysAppointments.add(appointmentResult);
        }
        return  sevenDaysAppointments;
    }

    /** This method gets all appointment objects that are scheduled to start within the next thirty days.
     * @return the observable list of filtered appointments */
    public static ObservableList<Appointment> getThirtyDaysAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Start <= UTC_TIMESTAMP() + INTERVAL 30 DAY";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartLdt = rs.getTimestamp("Start").toLocalDateTime();
            //ZonedDateTime appointmentStartZonedUtc = appointmentStartLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentStartZonedLocal = appointmentStartZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentStartLdtLocal = appointmentStartZonedLocal.toLocalDateTime();
            LocalDateTime appointmentEndLdt = rs.getTimestamp("End").toLocalDateTime();
            //ZonedDateTime appointmentEndZonedUtc = appointmentEndLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentEndZonedLocal = appointmentEndZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentEndLdtLocal = appointmentEndZonedLocal.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStartLdt, appointmentEndLdt, customerId, userId, contactId);
            allAppointments.add(appointmentResult);
        }
        return  allAppointments;
    }

    /** This method inserts a new appointment record into the appointments table of the client_schedule MySQL database.
     * @param title the title of the appointment.
     * @param description the description of the appointment.
     * @param location the location of the appointment.
     * @param type the type of the appointment.
     * @param start the start date and time of the appointment.
     * @param end the end date and time of the appointment.
     * @param customerId the customer ID associated with the appointment.
     * @param userId the user ID associated with the appointment.
     * @param contactId the contact ID associated with the appointment.
     * @return the number of rows inserted */
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

    /** This method updates an existing appointment record with each column value passed into it.
     * @param appointmentId the appointment ID of the appointment.
     * @param title the title of the appointment.
     * @param description the description of the appointment.
     * @param location the location of the appointment.
     * @param type the type of the appointment.
     * @param start the start date and time of the appointment.
     * @param end the end date and time of the appointment.
     * @param customerId the customer ID associated with the appointment.
     * @param userId the user ID associated with the appointment.
     * @param contactId the contact ID associated with the appointment.
     * @return the number of rows updated */
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

    /** This method deletes an appointment record from the appointments table of the client_schedule MySQL database.
     * @param appointmentId the appointment ID of the appointment.
     * @return the number of rows deleted */
    public static int deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** declare and initialize an observable list for all appointments by type */
    public static ObservableList<Appointment> allAppointmentsByType = FXCollections.observableArrayList();

    /** This method gets all appointment objects whose type matches the value passed in.
     * @param selectedType the appointment type to filter for.
     * @return the observable list of filtered appointments */
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
            LocalDateTime appointmentStartLdt = rs.getTimestamp("Start").toLocalDateTime();
            //ZonedDateTime appointmentStartZonedUtc = appointmentStartLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentStartZonedLocal = appointmentStartZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentStartLdtLocal = appointmentStartZonedLocal.toLocalDateTime();
            LocalDateTime appointmentEndLdt = rs.getTimestamp("End").toLocalDateTime();
            //ZonedDateTime appointmentEndZonedUtc = appointmentEndLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentEndZonedLocal = appointmentEndZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentEndLdtLocal = appointmentEndZonedLocal.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStartLdt, appointmentEndLdt, customerId, userId, contactId);
            allAppointmentsByType.add(appointmentResult);
        }
        return allAppointmentsByType;
    }

    /** declare and initialize an observable list for all appointments by month */
    public static ObservableList<Appointment> allAppointmentsByMonth = FXCollections.observableArrayList();
    /** This method gets all appointment objects whose month portion of the LocalDateTime matches the value passed in.
     * @param selectedLocalDateTime the 2-character String value (01 - 12) for the appointment month to filter for.
     * @return the observable list of filtered appointments */
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
            LocalDateTime appointmentStartLdt = rs.getTimestamp("Start").toLocalDateTime();
            //ZonedDateTime appointmentStartZonedUtc = appointmentStartLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentStartZonedLocal = appointmentStartZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentStartLdtLocal = appointmentStartZonedLocal.toLocalDateTime();
            LocalDateTime appointmentEndLdt = rs.getTimestamp("End").toLocalDateTime();
            //ZonedDateTime appointmentEndZonedUtc = appointmentEndLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentEndZonedLocal = appointmentEndZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentEndLdtLocal = appointmentEndZonedLocal.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStartLdt, appointmentEndLdt, customerId, userId, contactId);
            allAppointmentsByMonth.add(appointmentResult);
        }
        //System.out.println(allAppointmentsByCustomerId.size());
        return allAppointmentsByMonth;
    }

    /** declare and initialize an observable list for all appointments by contact */
    public static ObservableList<Appointment> allAppointmentsByContact = FXCollections.observableArrayList();
    /** This method gets all appointment objects whose associated contact ID matches the value passed in.
     * @param selectedContact the appointment contact ID to filter for.
     * @return the observable list of filtered appointments */
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
            LocalDateTime appointmentStartLdt = rs.getTimestamp("Start").toLocalDateTime();
            //ZonedDateTime appointmentStartZonedUtc = appointmentStartLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentStartZonedLocal = appointmentStartZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentStartLdtLocal = appointmentStartZonedLocal.toLocalDateTime();
            LocalDateTime appointmentEndLdt = rs.getTimestamp("End").toLocalDateTime();
            //ZonedDateTime appointmentEndZonedUtc = appointmentEndLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentEndZonedLocal = appointmentEndZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentEndLdtLocal = appointmentEndZonedLocal.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStartLdt, appointmentEndLdt, customerId, userId, contactId);
            allAppointmentsByContact.add(appointmentResult);
        }
        return allAppointmentsByContact;
    }

    /** declare and initialize an observable list for all appointments by customer ID */
    public static ObservableList<Appointment> allAppointmentsByCustomerId = FXCollections.observableArrayList();
    /** This method gets all appointment objects whose associated customer ID matches the value passed in.
     * @param selectedCustomer the appointment customer ID to filter for.
     * @return the observable list of filtered appointments */
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
            LocalDateTime appointmentStartLdt = rs.getTimestamp("Start").toLocalDateTime();
            //ZonedDateTime appointmentStartZonedUtc = appointmentStartLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentStartZonedLocal = appointmentStartZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentStartLdtLocal = appointmentStartZonedLocal.toLocalDateTime();
            LocalDateTime appointmentEndLdt = rs.getTimestamp("End").toLocalDateTime();
            //ZonedDateTime appointmentEndZonedUtc = appointmentEndLdtUtc.atZone(ZoneId.of("UTC"));
            //ZonedDateTime appointmentEndZonedLocal = appointmentEndZonedUtc.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            //LocalDateTime appointmentEndLdtLocal = appointmentEndZonedLocal.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            Appointment appointmentResult = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStartLdt, appointmentEndLdt, customerId, userId, contactId);
             allAppointmentsByCustomerId.add(appointmentResult);
        }
        return allAppointmentsByCustomerId;
    }

}

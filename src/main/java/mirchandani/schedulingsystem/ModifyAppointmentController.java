package mirchandani.schedulingsystem;

import dao.AppointmentDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import utility.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/** Class ModifyAppointmentController controls ModifyAppointment.fxml. It allows users to
 * modify appointments and input a new appointment title, description, location, customer ID,
 * user ID, contact name, type, and start and end dates and times with text fields, combo boxes,
 * and date pickers. The combo boxes are set to the values for the appointment being modified,
 * and are populated when the screen is initialized. The appointment ID is displayed but cannot
 * be modified.
 * @author Kush Mirchandani*/
public class ModifyAppointmentController implements Initializable {

    /** declares an appointment variable so the attributes for an appointment can be accessed */
    private static Appointment loadedAppointment;

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;

    @FXML
    private TextField appointmentIDTxt;

    @FXML
    private ComboBox<String> apptContactCmb;

    @FXML
    private ComboBox<String> apptCustomerIDCmb;

    @FXML
    private TextField apptDescriptionTxt;

    @FXML
    private DatePicker apptEndTimeDt;

    @FXML
    private ComboBox<String> apptEndTimeHHCmb;

    @FXML
    private ComboBox<String> apptEndTimeMMCmb;

    @FXML
    private ComboBox<String> apptEndTimeSSCmb;

    @FXML
    private ComboBox<String> apptLocationCmb1;

    @FXML
    private ComboBox<String> apptLocationCmb2;

    @FXML
    private DatePicker apptStartTimeDt;

    @FXML
    private ComboBox<String> apptStartTimeHHCmb;

    @FXML
    private ComboBox<String> apptStartTimeMMCmb;

    @FXML
    private ComboBox<String> apptStartTimeSSCmb;

    @FXML
    private TextField apptTitleTxt;

    @FXML
    private ComboBox<String> apptTypeCmb;

    @FXML
    private ComboBox<String> apptUserIDCmb;

    /** Sets the value for the loadedAppointment variable.
     * Sets the value for the loadedAppointment variable to the value of the appointment passed in.
     * @param selectedAppointment the appointment passed in. */
    public static void updateAppointment(Appointment selectedAppointment) {
        loadedAppointment = selectedAppointment;
    }

    /** Cancel button clicked.
     * Exits the Modify Appointment screen and opens the Main Screen.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Save button clicked.
     * Saves the modified Appointment by calling the updateAppointment method in the AppointmentDao class.
     * Closes the Modify Appointment screen and opens the Main Screen when clicked. Accepts the date and time
     * of the appointment input by the user and converts it to UTC when saved to the client_schedule database.
     * Displays error messages and stops running if the appointment time is outside of business hours (in eastern
     * U.S. time) or if the appointment time overlaps with any existing appointment times.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionSaveAppointment(ActionEvent event) throws IOException, SQLException {
        ObservableList<Appointment> allAppointments = AppointmentDao.getAllAppointments();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //System.out.println(startRaw);
        LocalDateTime startLdt = LocalDateTime.parse(apptStartTimeDt.getValue() + " " + apptStartTimeHHCmb.getValue() + ":" + apptStartTimeMMCmb.getValue() + ":" + apptStartTimeSSCmb.getValue(), formatter);
        //System.out.println(startLdt);
        ZonedDateTime startZonedLocal = startLdt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        //System.out.println(startZoned);
        ZonedDateTime startZonedUtc = startZonedLocal.withZoneSameInstant(ZoneId.of("UTC"));
        //System.out.println(utcstartZoned);
        ZonedDateTime startZonedEst = startZonedLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime startLdtUtc = startZonedUtc.toLocalDateTime();

        LocalDateTime endLdt = LocalDateTime.parse(apptEndTimeDt.getValue() + " " + apptEndTimeHHCmb.getValue() + ":" + apptEndTimeMMCmb.getValue() + ":" + apptEndTimeSSCmb.getValue(), formatter);
        ZonedDateTime endZonedLocal = endLdt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime endZonedUtc = endZonedLocal.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime endZonedEst = endZonedLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime endLdtUtc = endZonedUtc.toLocalDateTime();

        LocalTime businessOpenTime = LocalTime.of(8,0);
        LocalTime businessCloseTime = LocalTime.of(22,0);

        if((startZonedEst.toLocalTime().isBefore(businessOpenTime)) || (startZonedEst.toLocalTime().isAfter(businessCloseTime))|| (endZonedEst.toLocalTime().isBefore(businessOpenTime)) || (endZonedEst.toLocalTime().isAfter(businessCloseTime))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointment");
            alert.setContentText("Appointments may only be scheduled during business hours (8:00am to 10:00pm EST).");
            alert.showAndWait();
            return;
        }

        for (Appointment appointment : allAppointments) {
            if ((startLdt.isBefore(appointment.getEnd())) && (endLdt.isAfter(appointment.getStart()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment");
                alert.setHeaderText("Overlapping Appointment");
                alert.setContentText("There is at least one existing appointment time overlapping this one. Please adjust your desired appointment time.");
                alert.showAndWait();
                return;
            }
        }

        String contact = apptContactCmb.getValue();
        String sql = "SELECT Contact_ID "
                + "FROM contacts "
                + "WHERE Contact_Name = \"" + contact + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();

        AppointmentDao.updateAppointment(Integer.parseInt(appointmentIDTxt.getText()), apptTitleTxt.getText(), apptDescriptionTxt.getText(), apptLocationCmb2.getValue(), apptTypeCmb.getValue(), startLdtUtc, endLdtUtc, Integer.valueOf(apptCustomerIDCmb.getValue()), Integer.parseInt(apptUserIDCmb.getValue()), rs.getInt("Contact_ID"));

        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Selection made in the first Location combo box.
     * Populates the second Location combo box with the state names associated
     * with the country selected in the first combo box.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionSelectLocation1(ActionEvent event) {
        initializeLocation2();
    }

    /** This method returns the contact name associated with the loadedappointment.
     * Appointment objects store an associated contact ID, but not the contact name,
     * so this method returns the contact name of the loadedappoinment.
     * It's called when the ModifyAppointment screen is opened.*/
    private String getContactFromContactId() throws SQLException {

        int contactId = loadedAppointment.getContactId();

        String sql = "SELECT Contact_Name "
                + "FROM contacts "
                + "WHERE Contact_ID = \"" + contactId + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        //System.out.println(rs.getString("Division"));
        return rs.getString("Contact_Name");
    }

    /** This method populates the second Location combo box.
     * Populates the second Location combo box with the state names associated
     * with the country displayed in the first combo box. */
    private void getLocation1FromLocation2() {
        try {
            String location2 = loadedAppointment.getLocation();

            String sql = "SELECT Country "
                    + "FROM first_level_divisions, countries "
                    + "WHERE first_level_divisions.Country_ID = countries.Country_ID "
                    + "AND first_level_divisions.Division = \"" + location2 + "\"";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            rs.next();
            apptLocationCmb1.setValue((rs.getString("Country")));
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This method populates the first Location combo box.
     * Populates the first Location combo box with the names of all countries contained
     * in the Country column of the countries table in the client_schedule MySQL database.
     * It's called when the ModifyAppointment screen is opened.*/
    private void initializeLocation1() {
        try{
            String sql = "SELECT Country FROM countries";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                apptLocationCmb1.getItems().add(rs.getString("Country"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This method populates the second Location combo box.
     * Populates the second Location combo box with the state names associated
     * with the country selected in the first combo box. */
    private void initializeLocation2() {
        try{
            String location1 = apptLocationCmb1.getValue();

            String sql = "SELECT first_level_divisions.Division "
                    + "FROM first_level_divisions, countries "
                    + "WHERE first_level_divisions.Country_ID = countries.Country_ID "
                    + "AND countries.Country = \"" + location1 + "\"";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            apptLocationCmb2.getItems().clear();

            while(rs.next()) {
                apptLocationCmb2.getItems().add(rs.getString("Division"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This method populates the Customer ID combo box.
     * Populates the Customer ID combo box with the values of all Customer IDs contained
     * in the Customer_ID column of the customers table in the client_schedule MySQL database.
     * It's called when the ModifyAppointment screen is opened.*/
    private void initializeCustomerId() {
        try{
            String sql = "SELECT Customer_ID FROM customers ORDER BY Customer_ID ASC";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                apptCustomerIDCmb.getItems().add(rs.getString("Customer_ID"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This method populates the User ID combo box.
     * Populates the User ID combo box with the values of all User IDs contained
     * in the User_ID column of the users table in the client_schedule MySQL database.
     * It's called when the ModifyAppointment screen is opened.*/
    private void initializeUserId() {
        try{
            String sql = "SELECT User_ID FROM users ORDER BY User_ID ASC";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                apptUserIDCmb.getItems().add(rs.getString("User_ID"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This method populates the Contact combo box.
     * Populates the Contact combo box with the values of all contact names contained
     * in the Contact_Name column of the contacts table in the client_schedule MySQL database.
     * It's called when the ModifyAppointment screen is opened.*/
    private void initializeContact() {
        try{
            String sql = "SELECT Contact_Name FROM contacts ORDER BY Contact_Name ASC";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                apptContactCmb.getItems().add(rs.getString("Contact_Name"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This method populates the Type combo box.
     * Populates the Type combo box with the values of all appointment types contained
     * in the Type column of the appointments table in the client_schedule MySQL database.
     * It's called when the ModifyAppointment screen is opened.*/
    private void initializeType() {
        try{
            String sql = "SELECT DISTINCT Type FROM appointments ORDER BY Type ASC";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                apptTypeCmb.getItems().add(rs.getString("Type"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This method populates the Start Time and End Time HH, MM & SS combo boxes.
     * Populates the Start Time and End Time HH, MM & SS combo boxes with each possible
     * two-digit hour, minute or second value.
     * It's called when the ModifyAppointment screen is opened.*/
    private void initializeTimes() {
        apptStartTimeHHCmb.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");

        apptStartTimeMMCmb.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60");

        apptStartTimeSSCmb.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60");

        apptEndTimeHHCmb.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");

        apptEndTimeMMCmb.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60");

        apptEndTimeSSCmb.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60");
    }

    /** This is the initialize method.
     * This is the first method that gets called when the scene is set to the ModifyAppointment Screen.
     * The text fields and combo boxes are set to the values for the appointment being modified. This method calls the
     * initialization methods to populate the Location, Customer ID, User ID, Contact,Type, and Start and End time combo boxes.
     * @param url the location of ModifyAppointment.fxml
     * @param resourceBundle the name of ModifyAppointment.fxml*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentIDTxt.setText(String.valueOf(loadedAppointment.getId()));
        apptTitleTxt.setText(String.valueOf(loadedAppointment.getTitle()));
        apptDescriptionTxt.setText(String.valueOf(loadedAppointment.getDescription()));
        apptLocationCmb2.setValue(String.valueOf(loadedAppointment.getLocation()));
        apptTypeCmb.setValue(String.valueOf(loadedAppointment.getType()));
        apptStartTimeDt.setValue(LocalDate.parse(((CharSequence)String.valueOf(loadedAppointment.getStart())).subSequence(0,10)));
        apptStartTimeHHCmb.setValue((String.valueOf(loadedAppointment.getStart())).substring(11,13));
        apptStartTimeMMCmb.setValue((String.valueOf(loadedAppointment.getStart())).substring(14,16));
        try {
        apptStartTimeSSCmb.setValue((String.valueOf(loadedAppointment.getStart())).substring(17,19));}
        catch (Exception e) {apptStartTimeSSCmb.setValue("00");}
        apptEndTimeDt.setValue(LocalDate.parse(((CharSequence)String.valueOf(loadedAppointment.getEnd())).subSequence(0,10)));
        apptEndTimeHHCmb.setValue((String.valueOf(loadedAppointment.getEnd())).substring(11,13));
        apptEndTimeMMCmb.setValue((String.valueOf(loadedAppointment.getEnd())).substring(14,16));
        try {
        apptEndTimeSSCmb.setValue((String.valueOf(loadedAppointment.getEnd())).substring(17,19));}
        catch (Exception e) {apptEndTimeSSCmb.setValue("00");}
        apptCustomerIDCmb.setValue(String.valueOf(loadedAppointment.getCustomerId()));
        apptUserIDCmb.setValue(String.valueOf(loadedAppointment.getUserId()));
        try {
            apptContactCmb.setValue(String.valueOf(getContactFromContactId()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        getLocation1FromLocation2();
        initializeLocation1();
        initializeLocation2();
        initializeCustomerId();
        initializeUserId();
        initializeContact();
        initializeType();
        initializeTimes();
    }
}


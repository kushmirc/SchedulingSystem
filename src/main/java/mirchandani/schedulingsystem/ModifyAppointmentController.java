package mirchandani.schedulingsystem;

import dao.AppointmentDao;
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

public class ModifyAppointmentController implements Initializable {

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

    @FXML
    void onActionSelectLocation1(ActionEvent event) {
        initializeLocation2();
    }

    public static void updateAppointment(Appointment selectedAppointment) {
        loadedAppointment = selectedAppointment;
        //System.out.println(loadedAppointment);
    }

    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void onActionSaveAppointment(ActionEvent event) throws IOException, SQLException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String startRaw = apptStartTimeDt.getValue() + " " + apptStartTimeHHCmb.getValue() + ":" + apptStartTimeMMCmb.getValue() + ":" + apptStartTimeSSCmb.getValue();
        //System.out.println(startRaw);
        LocalDateTime startLdt = LocalDateTime.parse(apptStartTimeDt.getValue() + " " + apptStartTimeHHCmb.getValue() + ":" + apptStartTimeMMCmb.getValue() + ":" + apptStartTimeSSCmb.getValue(), formatter);
        //System.out.println(startLdt);
        ZonedDateTime startZonedLocal = startLdt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        //System.out.println(startZoned);
        ZonedDateTime startZonedUtc = startZonedLocal.withZoneSameInstant(ZoneId.of("UTC"));
        //System.out.println(utcstartZoned);
        ZonedDateTime startZonedEst = startZonedLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
        //LocalDateTime startLdtUtc = startZonedUtc.toLocalDateTime();


        LocalDateTime endLdt = LocalDateTime.parse(apptEndTimeDt.getValue() + " " + apptEndTimeHHCmb.getValue() + ":" + apptEndTimeMMCmb.getValue() + ":" + apptEndTimeSSCmb.getValue(), formatter);
        ZonedDateTime endZonedLocal = endLdt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime endZonedUtc = endZonedLocal.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime endZonedEst = endZonedLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
        //LocalDateTime endLdtUtc = endZonedUtc.toLocalDateTime();

        LocalTime businessOpenTime = LocalTime.of(8,0);
        //LocalDate businessOpenDate = startZonedEst.toLocalDate();
        //LocalDateTime businessOpen = LocalDateTime.of(businessOpenDate,businessOpenTime);
        LocalTime businessCloseTime = LocalTime.of(22,0);
        //LocalDate businessCloseDate = endZonedEst.toLocalDate();
        //LocalDateTime businessClose = LocalDateTime.of(businessCloseDate,businessCloseTime);

        if((startZonedEst.toLocalTime().isBefore(businessOpenTime)) || (startZonedEst.toLocalTime().isAfter(businessCloseTime))|| (endZonedEst.toLocalTime().isBefore(businessOpenTime)) || (endZonedEst.toLocalTime().isAfter(businessCloseTime))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointment");
            alert.setContentText("Appointments may only be scheduled during business hours (8:00am to 10:00pm EST).");
            alert.showAndWait();
            return;
        }

        String contact = apptContactCmb.getValue();
        String sql = "SELECT Contact_ID "
                + "FROM contacts "
                + "WHERE Contact_Name = \"" + contact + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();

        //System.out.println(endStamp);

        AppointmentDao.updateAppointment(Integer.parseInt(appointmentIDTxt.getText()), apptTitleTxt.getText(), apptDescriptionTxt.getText(), apptLocationCmb2.getValue(), apptTypeCmb.getValue(), AddAppointmentController.ZonedToLdtUtc.zonedToLdtUtc(startZonedUtc), AddAppointmentController.ZonedToLdtUtc.zonedToLdtUtc(endZonedUtc), Integer.valueOf(apptCustomerIDCmb.getValue()), Integer.parseInt(apptUserIDCmb.getValue()), rs.getInt("Contact_ID"));

        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

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

    private void getLocation1FromLocation2() {
        try {
            String location2 = loadedAppointment.getLocation();
            //System.out.println(apptLocationCmb2.getValue());

            String sql = "SELECT Country "
                    + "FROM first_level_divisions, countries "
                    + "WHERE first_level_divisions.Country_ID = countries.Country_ID "
                    + "AND first_level_divisions.Division = \"" + location2 + "\"";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //apptLocationCmb2.getItems().clear();

            rs.next();
            apptLocationCmb1.setValue((rs.getString("Country")));
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //System.out.println((String.valueOf(loadedAppointment.getStart())).substring(17,19));

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


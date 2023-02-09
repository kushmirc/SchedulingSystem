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
import java.time.LocalDate;
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
    public void onActionSaveAppointment(ActionEvent event) throws IOException {

        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
        apptStartTimeSSCmb.setValue((String.valueOf(loadedAppointment.getStart())).substring(17,19));
        apptEndTimeDt.setValue(LocalDate.parse(((CharSequence)String.valueOf(loadedAppointment.getEnd())).subSequence(0,10)));
        apptEndTimeHHCmb.setValue((String.valueOf(loadedAppointment.getEnd())).substring(11,13));
        apptEndTimeMMCmb.setValue((String.valueOf(loadedAppointment.getEnd())).substring(14,16));
        apptEndTimeSSCmb.setValue((String.valueOf(loadedAppointment.getEnd())).substring(17,19));
        apptCustomerIDCmb.setValue(String.valueOf(loadedAppointment.getCustomerId()));
        apptUserIDCmb.setValue(String.valueOf(loadedAppointment.getUserId()));
        apptContactCmb.setValue(String.valueOf(loadedAppointment.getContactId()));

        getLocation1FromLocation2();
        initializeLocation1();
        initializeLocation2();

    }
}
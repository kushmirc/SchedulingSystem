package mirchandani.schedulingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utility.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {

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
            String location2 = apptLocationCmb2.getValue();
            //System.out.println(customerStateCmb.getValue());

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
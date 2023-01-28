package mirchandani.schedulingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;

    @FXML
    private ComboBox<?> apptContactCmb;

    @FXML
    private ComboBox<?> apptCustomerIDCmb;

    @FXML
    private TextField apptDescriptionTxt;

    @FXML
    private DatePicker apptEndTimeDt;

    @FXML
    private ComboBox<?> apptEndTimeHHCmb;

    @FXML
    private ComboBox<?> apptEndTimeMMCmb;

    @FXML
    private ComboBox<?> apptEndTimeSSCmb;

    @FXML
    private ComboBox<?> apptLocationCmb1;

    @FXML
    private ComboBox<?> apptLocationCmb2;

    @FXML
    private DatePicker apptStartTimeDt;

    @FXML
    private ComboBox<?> apptStartTimeHHCmb;

    @FXML
    private ComboBox<?> apptStartTimeMMCmb;

    @FXML
    private ComboBox<?> apptStartTimeSSCmb;

    @FXML
    private TextField apptTitleTxt;

    @FXML
    private ComboBox<?> apptTypeCmb;

    @FXML
    private ComboBox<?> apptUserIDCmb;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
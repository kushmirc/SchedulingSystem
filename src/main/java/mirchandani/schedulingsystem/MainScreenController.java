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

public class MainScreenController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;
    @FXML
    private ToggleGroup appointmentTG;

    @FXML
    private RadioButton byCustomerRBtn;

    @FXML
    private RadioButton byMonthRBtn;

    @FXML
    private RadioButton byWeekRBtn;

    @FXML
    private TableColumn<?, ?> customerAddressCol;

    @FXML
    private TableColumn<?, ?> customerAddressCol1;

    @FXML
    private TableColumn<?, ?> customerDivisionIDCol;

    @FXML
    private TableColumn<?, ?> customerDivisionIDCol1;

    @FXML
    private TableColumn<?, ?> customerDivisionIDCol11;

    @FXML
    private TableColumn<?, ?> customerDivisionIDCol111;

    @FXML
    private TableColumn<?, ?> customerDivisionIDCol1111;

    @FXML
    private TableColumn<?, ?> customerIDCol;

    @FXML
    private TableColumn<?, ?> customerIDCol1;

    @FXML
    private TableColumn<?, ?> customerNameCol;

    @FXML
    private TableColumn<?, ?> customerNameCol1;

    @FXML
    private TableColumn<?, ?> customerPhoneCol;

    @FXML
    private TableColumn<?, ?> customerPhoneCol1;

    @FXML
    private TableColumn<?, ?> customerPostalCodeCol;

    @FXML
    private TableColumn<?, ?> customerPostalCodeCol1;

    @FXML
    private TableView<?> customersTableView;

    @FXML
    private TableView<?> customersTableView1;

    @FXML
    private RadioButton veiwAllRBtn;

    @FXML
    public void onActionAddAppointment(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddCustomer(ActionEvent event) {

    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionReports(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppointment(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomer(ActionEvent event) {

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
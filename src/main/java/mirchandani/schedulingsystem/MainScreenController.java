package mirchandani.schedulingsystem;

import dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ToggleGroup appointmentTG;

    @FXML
    private TableColumn<?, ?> apptContactIdCol;

    @FXML
    private TableColumn<?, ?> apptCustomerIdCol;

    @FXML
    private TableColumn<?, ?> apptDescriptionCol;

    @FXML
    private TableColumn<?, ?> apptEndCol;

    @FXML
    private TableColumn<?, ?> apptIdCol;

    @FXML
    private TableColumn<?, ?> apptLocationCol;

    @FXML
    private TableColumn<?, ?> apptStartCol;

    @FXML
    private TableColumn<?, ?> apptTitleCol;

    @FXML
    private TableColumn<?, ?> apptTypeCol;

    @FXML
    private RadioButton byCustomerRBtn;

    @FXML
    private RadioButton byMonthRBtn;

    @FXML
    private RadioButton byWeekRBtn;

    @FXML
    private TableColumn<Customer, String> customerAddressCol;

    @FXML
    private TableColumn<Customer, Integer> customerDivisionIdCol;

    @FXML
    private TableColumn<Customer, Integer> customerIdCol;

    @FXML
    private TableColumn<Customer, String> customerNameCol;

    @FXML
    private TableColumn<Customer, String> customerPhoneCol;

    @FXML
    private TableColumn<Customer, String> customerPostalCodeCol;

    @FXML
    private TableView<Customer> customersTableView;

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
    public void onActionAddCustomer(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void onActionDeleteAppointment(ActionEvent event) {

    }

    @FXML
    public void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    public void onActionReports(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ReportScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void onActionUpdateAppointment(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void onActionUpdateCustomer(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customersTableView.setItems(CustomerDao.getAllCustomers());

            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            customerDivisionIdCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        } catch (
                Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
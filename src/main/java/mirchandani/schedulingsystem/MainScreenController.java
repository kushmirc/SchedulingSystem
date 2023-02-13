package mirchandani.schedulingsystem;

import dao.AppointmentDao;
import dao.CustomerDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
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
    private TableColumn<Appointment, Integer> apptContactIdCol;

    @FXML
    private TableColumn<Appointment, Integer> apptCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> apptDescriptionCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptEndCol;

    @FXML
    private TableColumn<Appointment, Integer> apptIdCol;

    @FXML
    private TableColumn<Appointment, String> apptLocationCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptStartCol;

    @FXML
    private TableColumn<Appointment, String> apptTitleCol;

    @FXML
    private TableColumn<Appointment, String> apptTypeCol;

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
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private RadioButton veiwAllRBtn;

    @FXML
    private Label customersExLbl;

    @FXML
    private Label appointmentsExLbl;

    DisplayNotification deleteCustomer = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Customer");
        alert.setHeaderText("Delete");
        alert.setContentText("Are you sure you want to delete this customer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            CustomerDao.deleteCustomer(customersTableView.getSelectionModel().getSelectedItem().getId());
            customersExLbl.setText("Customer deleted");
        } else {
            customersExLbl.setText("Customer not deleted");
        }
    };

    @FXML
    void onActionViewAllRBtn(ActionEvent event) {
        initializeAppointments();
    }

    @FXML
    void onActionByWeekRBtn(ActionEvent event) {
        try {
            appointmentsTableView.setItems(AppointmentDao.getSevenDaysAppointments());

            apptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            apptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            apptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            apptCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            apptContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        } catch (
                Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    void onActionByMonthRBtn(ActionEvent event) {
        try {
            appointmentsTableView.setItems(AppointmentDao.getThirtyDaysAppointments());

            apptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            apptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            apptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            apptCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            apptContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        } catch (
                Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


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
    public void onActionDeleteAppointment(ActionEvent event) throws SQLException {

        appointmentsExLbl.setText("");

        if(appointmentsTableView.getSelectionModel().getSelectedItem() == null) {
            appointmentsExLbl.setText("Please select an appointment");
            return;}

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Appointment");
        alert.setHeaderText("Delete");
        alert.setContentText("Are you sure you want to delete this appointment?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            AppointmentDao.deleteAppointment(appointmentsTableView.getSelectionModel().getSelectedItem().getId());
            appointmentsExLbl.setText("Appointment deleted");
        } else {
            appointmentsExLbl.setText("Appointment not deleted");
        }

        initializeAppointments();
    }

    @FXML
    public void onActionDeleteCustomer(ActionEvent event) throws SQLException {

        customersExLbl.setText("");

        if(customersTableView.getSelectionModel().getSelectedItem() == null) {
            customersExLbl.setText("Please select a customer");
            return;}

        ObservableList<Customer> allCustomers = CustomerDao.getAllCustomers();
        //System.out.println(allCustomers);
        ObservableList<Appointment> allAppointments = AppointmentDao.getAllAppointments();
        //System.out.println(allAppointments);
        //System.out.println(customersTableView.getSelectionModel().getSelectedItem().getId());

        for (Appointment appointment : allAppointments) {
            if ((customersTableView.getSelectionModel().getSelectedItem().getId()) == appointment.getCustomerId()) {
                //System.out.println("Match Found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer");
                alert.setHeaderText("Delete");
                alert.setContentText("All appointments for this customer must be deleted before the customer can be deleted.");
                alert.showAndWait();
                return;
            }
        }

            deleteCustomer.confirmation();

           /* Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Customer");
            alert.setHeaderText("Delete");
            alert.setContentText("Are you sure you want to delete this customer?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                CustomerDao.deleteCustomer(customersTableView.getSelectionModel().getSelectedItem().getId());
                customersExLbl.setText("Customer deleted");
            } else {
                customersExLbl.setText("Customer not deleted");
            } */

            initializeCustomers();

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

        appointmentsExLbl.setText("");

        if(appointmentsTableView.getSelectionModel().getSelectedItem() == null) {
            appointmentsExLbl.setText("Please select a customer");
            return;}

        Appointment clickedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        ModifyAppointmentController.updateAppointment(clickedAppointment);
        //System.out.println(appointmentsTableView.getSelectionModel().getSelectedItem());
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void onActionUpdateCustomer(ActionEvent event) throws IOException {

        customersExLbl.setText("");

        if(customersTableView.getSelectionModel().getSelectedItem() == null) {
            customersExLbl.setText("Please select a customer");
            return;}

        Customer clickedCustomer = customersTableView.getSelectionModel().getSelectedItem();
        ModifyCustomerController.updateCustomer(clickedCustomer);

        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private void initializeCustomers() {
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

    private void initializeAppointments() {
        try {
            appointmentsTableView.setItems(AppointmentDao.getAllAppointments());

            apptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            apptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            apptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            apptCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            apptContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        } catch (
                Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeCustomers();
        initializeAppointments();

    }
}
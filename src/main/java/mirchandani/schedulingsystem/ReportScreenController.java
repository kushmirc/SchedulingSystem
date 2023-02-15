package mirchandani.schedulingsystem;

import dao.AppointmentDao;
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
import utility.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/** Class ReportScreenController controls ReportScreen.fxml. It displays four appointment table views.
 * These table views display all appointments when filtered by appointment type, month, contact, or customer,
 * respectively. There are combo boxes allowing the user to select the values to filter by. Each table view
 * also displays a count of the number of appointments returned by the filter.
 * @author Kush Mirchandani*/
public class ReportScreenController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;

    @FXML
    private ComboBox<String> appointmentsByContactCmb;

    @FXML
    private TextField appointmentsByContactCountTxt;

    @FXML
    private ComboBox<String> appointmentsByCustomerCmb;

    @FXML
    private TextField appointmentsByCustomerCountTxt1;

    @FXML
    private ComboBox<String> appointmentsByMonthCmb;

    @FXML
    private TextField appointmentsByMonthCountTxt;

    @FXML
    private ComboBox<String> appointmentsByTypeCmb;

    @FXML
    private TextField appointmentsByTypeCountTxt;

    @FXML
    private TableColumn<Appointment, Integer> apptByContactContactIdCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByContactCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> apptByContactDescriptionCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptByContactEndCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByContactIdCol;

    @FXML
    private TableColumn<Appointment, String> apptByContactLocationCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptByContactStartCol;

    @FXML
    private TableColumn<Appointment, String> apptByContactTitleCol;

    @FXML
    private TableColumn<Appointment, String> apptByContactTypeCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByCustomerContactIdCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByCustomerCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> apptByCustomerDescriptionCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptByCustomerEndCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> apptByCustomerLocationCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptByCustomerStartCol;

    @FXML
    private TableColumn<Appointment, String> apptByCustomerTitleCol;

    @FXML
    private TableColumn<Appointment, String> apptByCustomerTypeCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByMonthContactIdCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByMonthCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> apptByMonthDescriptionCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptByMonthEndCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByMonthIdCol;

    @FXML
    private TableColumn<Appointment, String> apptByMonthLocationCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptByMonthStartCol;

    @FXML
    private TableColumn<Appointment, String> apptByMonthTitleCol;

    @FXML
    private TableColumn<Appointment, String> apptByMonthTypeCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByTypeContactIdCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByTypeCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> apptByTypeDescriptionCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptByTypeEndCol;

    @FXML
    private TableColumn<Appointment, Integer> apptByTypeIdCol;

    @FXML
    private TableColumn<Appointment, String> apptByTypeLocationCol;

    @FXML
    private TableColumn<Appointment, Timestamp> apptByTypeStartCol;

    @FXML
    private TableColumn<Appointment, String> apptByTypeTitleCol;

    @FXML
    private TableColumn<Appointment, String> apptByTypeTypeCol;

    @FXML
    private TableView<Appointment> appointmentsByTypeTableView;

    @FXML
    private TableView<Appointment> appointmentsByMonthTableView;

    @FXML
    private TableView<Appointment> appointmentsByContactTableView;

    @FXML
    private TableView<Appointment> appointmentsByCustomerTableView;

    @FXML
    private Button returnToMainBtn;

    /** Selection made in the Appointments by Type combo box.
     * Populates the table view columns with the values from the appointments retrieved
     * by calling the appointmentsByType method from the AppointmentDao class.
     * Sets the count text field to the number of appointments retrieved.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionSelectType(ActionEvent event) throws SQLException {

        AppointmentDao.allAppointmentsByType.clear();
        appointmentsByTypeTableView.setItems(AppointmentDao.appointmentsByType(appointmentsByTypeCmb.getValue()));

        appointmentsByTypeCountTxt.setText(String.valueOf(AppointmentDao.allAppointmentsByType.size()));

        apptByTypeIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptByTypeTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptByTypeDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptByTypeLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptByTypeTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptByTypeStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptByTypeEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptByTypeCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptByTypeContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }

    /** Selection made in the Appointments by Month combo box.
     * Populates the table view columns with the values from the appointments retrieved
     * by calling the appointmentsByMonth method from the AppointmentDao class.
     * Sets the count text field to the number of appointments retrieved.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionSelectMonth(ActionEvent event) throws SQLException {

        AppointmentDao.allAppointmentsByMonth.clear();
        appointmentsByMonthTableView.setItems(AppointmentDao.appointmentsByMonth(appointmentsByMonthCmb.getValue().substring(0,2)));

        appointmentsByMonthCountTxt.setText(String.valueOf((AppointmentDao.allAppointmentsByMonth.size())));

        apptByMonthIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptByMonthTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptByMonthDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptByMonthLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptByMonthTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptByMonthStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptByMonthEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptByMonthCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptByMonthContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }

    /** Selection made in the Appointments by Contact combo box.
     * Populates the table view columns with the values from the appointments retrieved
     * by calling the appointmentsByContact method from the AppointmentDao class.  Converts the
     * contact name from the combo box to its associated contact ID to be populated in the table view.
     * Sets the count text field to the number of appointments retrieved.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionSelectContact(ActionEvent event) throws SQLException {

        String contact = appointmentsByContactCmb.getValue();

        AppointmentDao.allAppointmentsByContact.clear();

        String sql = "SELECT Contact_ID "
                + "FROM contacts "
                + "WHERE Contact_Name = \"" + contact + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();

        appointmentsByContactTableView.setItems((AppointmentDao.appointmentsByContact(rs.getString("Contact_ID"))));

        appointmentsByContactCountTxt.setText(String.valueOf(AppointmentDao.allAppointmentsByContact.size()));

        apptByContactIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptByContactTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptByContactDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptByContactLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptByContactTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptByContactStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptByContactEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptByContactCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptByContactContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }

    /** Selection made in the Appointments by Customer combo box.
     * Populates the table view columns with the values from the appointments retrieved
     * by calling the appointmentsByCustomerId method from the AppointmentDao class.
     * Sets the count text field to the number of appointments retrieved.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionSelectCustomer(ActionEvent event) throws SQLException {

        AppointmentDao.allAppointmentsByCustomerId.clear();

        String customerIdAndName = appointmentsByCustomerCmb.getValue();
        String customerIdString = customerIdAndName.split(" - ")[0];
        appointmentsByCustomerTableView.setItems(AppointmentDao.appointmentsByCustomerId(Integer.parseInt(customerIdString)));

        appointmentsByCustomerCountTxt1.setText(String.valueOf(AppointmentDao.allAppointmentsByCustomerId.size()));

        apptByCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptByCustomerTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptByCustomerDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptByCustomerLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptByCustomerTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptByCustomerStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptByCustomerEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptByCustomerCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptByCustomerContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }

    /** Return to Main button clicked.
     * Exits the Reports screen and opens the Main Screen.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Populates the Appointments by Type combo box.
     * Populates the Appointments by Type combo box with the appointment
     * types found among all appointments scheduled. Only adds an appointment type
     * if it hasn't already been added to the combo box to prevent duplicates.
     * It's called when the Report Screen is opened*/
    private void initializeAppointmentsByTypeCmb() {
        try{
            String sql = "SELECT Type FROM appointments";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                if (appointmentsByTypeCmb.getItems().contains(rs.getString("Type"))) {}
                else {appointmentsByTypeCmb.getItems().add(rs.getString("Type"));}
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Populates the Appointments by Month combo box.
     * Populates the Appointments by Month combo box with string values for each month
     * in a year. It's represented by the two-digit month, and the month name separated by a hyphen.
     * It's called when the Report Screen is opened*/
    private void initializeAppointmentsByMonthCmb() {
        appointmentsByMonthCmb.getItems().addAll(
                "01 - January", "02 - February", "03 - March", "04 - April", "05 - May", "06 - June", "07 - July", "08 - August", "09 - September", "10 - October", "11 - November", "12 - December");
    }
    private void initializeAppointmentsByContactCmb() {
        try{
            String sql = "SELECT Contact_Name FROM contacts";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                appointmentsByContactCmb.getItems().add(rs.getString("Contact_Name"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Populates the Appointments by Customer combo box.
     * Populates the Appointments by Customer combo box with the customer ID's of all customers
     * from the customers table in the client_schedule MySQL database.
     * It represents customers with both the customer ID and name, separated by a hyphen.
     * It's called when the Report Screen is opened*/
    private void initializeAppointmentsByCustomerCmb() {
        try{
            String sql = "SELECT Customer_ID, Customer_Name FROM customers";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                appointmentsByCustomerCmb.getItems().add((rs.getString("Customer_ID") + " - " + rs.getString("Customer_Name")));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This is the initialize method.
     * This is the first method that gets called when the scene is set to the Report Screen.
     * Calls the initialization methods to populate the appointments by type, month, contact,
     * and customer combo boxes.
     * @param url the location of ReportScreen.fxml
     * @param resourceBundle the name of ReportScreen.fxml*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeAppointmentsByTypeCmb();
        initializeAppointmentsByMonthCmb();
        initializeAppointmentsByContactCmb();
        initializeAppointmentsByCustomerCmb();
    }
}
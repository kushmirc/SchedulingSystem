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

    @FXML
    void onActionSelectType(ActionEvent event) throws SQLException {

        appointmentsByTypeTableView.setItems(AppointmentDao.appointmentsByType(appointmentsByTypeCmb.getValue()));

        System.out.println(AppointmentDao.allAppointmentsByType.size());

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
    @FXML
    void onActionSelectMonth(ActionEvent event) throws SQLException {

        //System.out.println(appointmentsByMonthCmb.getValue().substring(0,2));

        appointmentsByMonthTableView.setItems(AppointmentDao.appointmentsByMonth(appointmentsByMonthCmb.getValue().substring(0,2)));

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

    @FXML
    void onActionSelectContact(ActionEvent event) throws SQLException {

        String contact = appointmentsByContactCmb.getValue();
        //System.out.println(contact);

        String sql = "SELECT Contact_ID "
                + "FROM contacts "
                + "WHERE Contact_Name = \"" + contact + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        //System.out.println(rs.getString("Contact_ID"));

        appointmentsByContactTableView.setItems((AppointmentDao.appointmentsByContact(rs.getString("Contact_ID"))));

        //rs.next();
        //System.out.println(rs.getString("Division"));
        //return rs.getString("Division");

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



    @FXML
    void onActionSelectCustomer(ActionEvent event) throws SQLException {

        /*String customer = appointmentsByCustomerCmb.getValue();

        String sql = "SELECT Customer_ID "
                + "FROM customers "
                + "WHERE Customer_Name = \"" + customer + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();

        appointmentsByCustomerTableView.setItems(AppointmentDao.appointmentsByCustomerId(rs.getInt("Customer_ID")));*/
        String customerIdAndName = appointmentsByCustomerCmb.getValue();
        String customerIdString = customerIdAndName.split(" - ")[0];
        appointmentsByCustomerTableView.setItems(AppointmentDao.appointmentsByCustomerId(Integer.parseInt(customerIdString)));

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

    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private void initializeAppointmentsByTypeCmb() {
        try{
            String sql = "SELECT Type FROM appointments";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {

                if (appointmentsByTypeCmb.getItems().contains(rs.getString("Type"))) {}
                else {appointmentsByTypeCmb.getItems().add(rs.getString("Type"));}

                //appointmentsByTypeCmb.getItems().add(rs.getString("Type"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void initializeAppointmentsByMonthCmb() {
        /*try{
            String sql = "SELECT Start FROM appointments";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                appointmentsByMonthCmb.getItems().add(rs.getString("Start").substring(5,7));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }*/

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeAppointmentsByTypeCmb();
        initializeAppointmentsByMonthCmb();
        initializeAppointmentsByContactCmb();
        initializeAppointmentsByCustomerCmb();



    }
}
package mirchandani.schedulingsystem;

import dao.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Class AddCustomerController controls AddCustomer.fxml. It allows users to
 * create customers and input a customer name, address, postal code, phone number,
 * and division ID, with text fields and combo boxes. The combo boxes are populated
 * when the screen is initialized. When customers are saved, they are assigned a customer ID
 * by the system.
 * @author Kush Mirchandani*/
public class AddCustomerController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;

    @FXML
    private TextField customerAddressTxt;

    @FXML
    private ComboBox<String> customerCountryCmb;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private TextField customerPhoneTxt;

    @FXML
    private TextField customerPostalCodeTxt;

    @FXML
    private ComboBox<String> customerStateCmb;

    /** Cancel button clicked.
     * Exits the Add Customer screen and opens the Main Screen.
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
     * Saves the new customer by calling the insertCustomer method in the CustomerDao class.
     * Closes the Add Customer screen and opens the Main Screen when clicked.
     * @param event the item on the GUI that triggers the action */
    @FXML
    public void onActionSaveCustomer(ActionEvent event) throws IOException, SQLException {

        String state = customerStateCmb.getValue();

        String sql = "SELECT Division_ID "
                + "FROM first_level_divisions "
                + "WHERE Division = \"" + state + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();

        CustomerDao.insertCustomer(customerNameTxt.getText(), customerAddressTxt.getText(), customerPostalCodeTxt.getText(), customerPhoneTxt.getText(), rs.getInt("Division_ID"));

        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Selection made in the Country combo box.
     * Populates the State combo box with the state names associated
     * with the country selected in the country combo box.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionSelectCountry(ActionEvent event) {
        initializeCity();
    }

    /** This method populates the Country combo box.
     * Populates the Country combo box with the names of all countries contained
     * in the Country column of the countries table in the client_schedule MySQL database.
     * It's called when the AddCustomer screen is opened.*/
    private void initializeCountry() {
        try{
            String sql = "SELECT Country FROM countries";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                customerCountryCmb.getItems().add(rs.getString("Country"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This method populates the State combo box.
     * Populates the State combo box with the state names associated
     * with the country selected in the Country combo box. */
    private void initializeCity() {
        try{
            String country = customerCountryCmb.getValue();

            String sql = "SELECT first_level_divisions.Division "
                    + "FROM first_level_divisions, countries "
                    + "WHERE first_level_divisions.Country_ID = countries.Country_ID "
                    + "AND countries.Country = \"" + country + "\"";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            customerStateCmb.getItems().clear();

            while(rs.next()) {
                customerStateCmb.getItems().add(rs.getString("Division"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This is the initialize method.
     * This is the first method that gets called when the scene is set to the AddCustomer Screen.
     * Calls the initialization method to populate the Country combo box.
     * @param url the location of AddCustomer.fxml
     * @param resourceBundle the name of AddCustomer.fxml*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeCountry();
    }
}
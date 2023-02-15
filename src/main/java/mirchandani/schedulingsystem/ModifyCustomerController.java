package mirchandani.schedulingsystem;

import dao.CustomerDao;
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
import model.Customer;
import utility.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Class ModifyCustomerController controls ModifyCustomer.fxml. It allows users to
 * modify a customer's information and input a new customer name, address, postal code,
 * phone number, and division ID with text fields and combo boxes. The combo boxes are set
 * to the values for the customer being modified, and are populated when the screen is initialized.
 * The customer ID is displayed but cannot be modified.
 * @author Kush Mirchandani*/
public class ModifyCustomerController implements Initializable {

    /** declares a customer variable so the attributes for a customer can be accessed */
    private static Customer loadedCustomer;

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;

    @FXML
    private TextField customerAddressTxt;

    @FXML
    private ComboBox<String> customerCountryCmb;

    @FXML
    private TextField customerIDTxt;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private TextField customerPhoneTxt;

    @FXML
    private TextField customerPostalCodeTxt;

    @FXML
    private ComboBox<String> customerStateCmb;

    /** Cancel button clicked.
     * Exits the Modify Customer screen and opens the Main Screen.
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
     * Saves the new customer by calling the updateCustomer method in the CustomerDao class.
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

        CustomerDao.updateCustomer(Integer.parseInt(customerIDTxt.getText()), customerNameTxt.getText(), customerAddressTxt.getText(), customerPostalCodeTxt.getText(), customerPhoneTxt.getText(), rs.getInt("Division_ID"));

        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Sets the value for the loadedCustomer variable.
     * Sets the value for the loadedCustomer variable to the value of the customer passed in.
     * @param selectedCustomer the customer passed in. */
    public static void updateCustomer(Customer selectedCustomer) {
        loadedCustomer = selectedCustomer;
    }

    /** Selection made in the Country combo box.
     * Populates the State combo box with the state names associated
     * with the country selected in the country combo box.
     * @param event the item on the GUI that triggers the action */
    @FXML
    void onActionSelectCountry(ActionEvent event) {
        initializeCity();
    }

    /** This method returns the state name associated with the loadedcustomer.
     * Customer objects store an associated Division ID, but not the divison's (state's) name,
     * so this method returns the state name of the loadedcustomer.
     * It's called when the ModifyCustomer screen is opened.*/
    private String getStateFromDivisionId() throws SQLException {

        int divisionId = loadedCustomer.getDivisionId();

        String sql = "SELECT Division "
                + "FROM first_level_divisions "
                + "WHERE Division_ID = \"" + divisionId + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        //System.out.println(rs.getString("Division"));
        return rs.getString("Division");
    }

    //This is an alternative way to accomplish the same thing as getStateFromDivisionId
    /*private String getStateFromDivisionId2(Customer customer) throws SQLException {
        int divisionId = customer.getDivisionId();

        String sql = "SELECT Division "
                + "FROM first_level_divisions "
                + "WHERE Division_ID = \"" + divisionId + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getString("Division");
    }*/

    /** This method populates the Country combo box.
     * Populates the Country combo box with the country name associated
     * with the state name displayed in the first combo box.
     * It's called when the ModifyCustomer screen is opened.*/
    private void getCountryFromState() {
try {
    String state = customerStateCmb.getValue();
    //System.out.println(customerStateCmb.getValue());

    String sql = "SELECT Country "
            + "FROM first_level_divisions, countries "
            + "WHERE first_level_divisions.Country_ID = countries.Country_ID "
            + "AND first_level_divisions.Division = \"" + state + "\"";

    PreparedStatement ps = JDBC.connection.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    rs.next();
        customerCountryCmb.setValue((rs.getString("Country")));

}
catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}
    }

    /** This method populates the Country combo box.
     * Populates the Country combo box with the names of all countries contained
     * in the Country column of the countries table in the client_schedule MySQL database.
     * It's called when the ModifyCustomer screen is opened.*/
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
     * This is the first method that gets called when the scene is set to the ModifyCustomer Screen.
     * The text fields and combo boxes are set to the values for the customer being modified.
     * This method calls the initialization methods to populate the Country and State combo boxes.
     * @param url the location of AddCustomer.fxml
     * @param resourceBundle the name of AddCustomer.fxml*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIDTxt.setText(String.valueOf(loadedCustomer.getId()));
        customerNameTxt.setText(String.valueOf(loadedCustomer.getName()));
        customerAddressTxt.setText(String.valueOf(loadedCustomer.getAddress()));
        customerPostalCodeTxt.setText(String.valueOf(loadedCustomer.getPostalCode()));
        customerPhoneTxt.setText(String.valueOf(loadedCustomer.getPhone()));
        try {
            customerStateCmb.setValue(String.valueOf(getStateFromDivisionId()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        getCountryFromState();
        initializeCountry();
        initializeCity();

        //this implements the getStateFromDivisionId2 option
        /*try {
            customerStateCmb.setValue(getStateFromDivisionId2(loadedCustomer));
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
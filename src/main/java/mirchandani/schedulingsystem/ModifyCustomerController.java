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
import model.Customer;
import utility.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {

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

    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void onActionSaveCustomer(ActionEvent event) throws IOException, SQLException {

        String state = customerStateCmb.getValue();

        String sql = "SELECT Division_ID "
                + "FROM first_level_divisions "
                + "WHERE Division = \"" + state + "\"";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();

        CustomerDao.updateCustomer(customerNameTxt.getText(), customerAddressTxt.getText(), customerPostalCodeTxt.getText(), customerPhoneTxt.getText(), rs.getInt("Division_ID"));

        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public static void updateCustomer(Customer selectedCustomer) {
        loadedCustomer = selectedCustomer;
    }

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

        //this implements the getStateFromDivisionId2 option
        /*try {
            customerStateCmb.setValue(getStateFromDivisionId2(loadedCustomer));
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
package mirchandani.schedulingsystem;

import dao.CustomerDao;
import dao.FruitsQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    public TextField getCustomerAddressTxt() {
        return customerAddressTxt;
    }

    public ComboBox<String> getCustomerCountryCmb() {
        return customerCountryCmb;
    }

    public TextField getCustomerNameTxt() {
        return customerNameTxt;
    }

    public TextField getCustomerPhoneTxt() {
        return customerPhoneTxt;
    }

    public TextField getCustomerPostalCodeTxt() {
        return customerPostalCodeTxt;
    }

    public ComboBox<String> getCustomerStateCmb() {
        return customerStateCmb;
    }

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

        CustomerDao.insertCustomer(customerNameTxt.getText(), customerAddressTxt.getText(), customerPostalCodeTxt.getText(), customerPhoneTxt.getText(), rs.getInt("Division_ID"));

        //get the stage from the event's source widget
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSelectCountry(ActionEvent event) {
        initializeCity();
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeCountry();




    }

}
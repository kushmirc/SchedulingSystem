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

public class ReportScreenController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;

    @FXML
    private ComboBox<?> appointmentsByContactCmb;

    @FXML
    private TextField appointmentsByContactCountTxt;

    @FXML
    private ComboBox<?> appointmentsByCustomerCmb1;

    @FXML
    private TextField appointmentsByCustomerCountTxt1;

    @FXML
    private ComboBox<?> appointmentsByMonthCmb;

    @FXML
    private TextField appointmentsByMonthCountTxt;

    @FXML
    private ComboBox<?> appointmentsByTypeCmb;

    @FXML
    private TextField appointmentsByTypeCountTxt;

    @FXML
    private TableColumn<?, ?> apptByContactContactIDCol;

    @FXML
    private TableColumn<?, ?> apptByContactCustomerIDCol;

    @FXML
    private TableColumn<?, ?> apptByContactDescriptionCol;

    @FXML
    private TableColumn<?, ?> apptByContactEndCol;

    @FXML
    private TableColumn<?, ?> apptByContactIDCol;

    @FXML
    private TableColumn<?, ?> apptByContactLocationCol;

    @FXML
    private TableColumn<?, ?> apptByContactStartCol;

    @FXML
    private TableColumn<?, ?> apptByContactTitleCol;

    @FXML
    private TableColumn<?, ?> apptByContactTypeCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerContactIDCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerCustomerIDCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerDescriptionCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerEndCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerIDCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerLocationCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerStartCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerTitleCol;

    @FXML
    private TableColumn<?, ?> apptByCustomerTypeCol;

    @FXML
    private TableColumn<?, ?> apptByMonthContactIDCol;

    @FXML
    private TableColumn<?, ?> apptByMonthCustomerIDCol;

    @FXML
    private TableColumn<?, ?> apptByMonthDescriptionCol;

    @FXML
    private TableColumn<?, ?> apptByMonthEndCol;

    @FXML
    private TableColumn<?, ?> apptByMonthIDCol;

    @FXML
    private TableColumn<?, ?> apptByMonthLocationCol;

    @FXML
    private TableColumn<?, ?> apptByMonthStartCol;

    @FXML
    private TableColumn<?, ?> apptByMonthTitleCol;

    @FXML
    private TableColumn<?, ?> apptByMonthTypeCol;

    @FXML
    private TableColumn<?, ?> apptByTypeContactIDCol;

    @FXML
    private TableColumn<?, ?> apptByTypeCustomerIDCol;

    @FXML
    private TableColumn<?, ?> apptByTypeDescriptionCol;

    @FXML
    private TableColumn<?, ?> apptByTypeEndCol;

    @FXML
    private TableColumn<?, ?> apptByTypeIDCol;

    @FXML
    private TableColumn<?, ?> apptByTypeLocationCol;

    @FXML
    private TableColumn<?, ?> apptByTypeStartCol;

    @FXML
    private TableColumn<?, ?> apptByTypeTitleCol;

    @FXML
    private TableColumn<?, ?> apptByTypeTypeCol;

    @FXML
    private TableView<?> customersTableView1;

    @FXML
    private TableView<?> customersTableView11;

    @FXML
    private TableView<?> customersTableView12;

    @FXML
    private TableView<?> customersTableView121;

    @FXML
    private Button returnToMainBtn;

    @FXML
    public void onActionDisplayMainScreen(ActionEvent event) throws IOException {
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
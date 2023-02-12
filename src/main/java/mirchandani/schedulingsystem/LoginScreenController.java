package mirchandani.schedulingsystem;

import dao.AppointmentDao;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    /** declares a stage variable */
    Stage stage;

    /** declares a scene variable */
    Parent scene;
    @FXML
    private Button loginBtn;

    @FXML
    private Label passwordLbl;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Label usernameLbl;

    @FXML
    private TextField usernameTxt;

    @FXML
    private Label welcomeLbl;

    @FXML
    private Label timeZoneLbl1;

    @FXML
    private Label timeZoneLbl2;

    @FXML
    private Label usernameExLbl;

    @FXML
    private Label passwordExLbl;


    @FXML
    public  void onActionLogin(ActionEvent event) throws IOException, SQLException {
        UserDao.getAllUsers();
        usernameExLbl.setVisible(false);
        passwordExLbl.setVisible(false);
        ObservableList<User> searchedUser;

        searchedUser = UserDao.lookupUser(usernameTxt.getText());

        if (searchedUser.size() == 0) {
            usernameExLbl.setVisible(true);
        } else if (!Objects.equals(searchedUser.get(0).getPassword(), passwordTxt.getText())) {
            passwordExLbl.setVisible(true);
        } else {

            upcomingAppointments();
            //get the stage from the event's source widget
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    public void language() {

        try{
            ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());

            if(Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr"));
            System.out.println(rb.getString("hello") + " " + rb.getString("world"));

            welcomeLbl.setText(rb.getString("Welcome"));
            usernameLbl.setText(rb.getString("Username"));
            passwordLbl.setText(rb.getString("Password"));
            loginBtn.setText(rb.getString("Login"));
            timeZoneLbl1.setText(rb.getString("Time_Zone"));
            usernameExLbl.setText(rb.getString("Username_not_found"));
            passwordExLbl.setText(rb.getString("Incorrect_password"));


        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void zone() {
        try {
            ZoneId zone = ZoneId.systemDefault();
            timeZoneLbl2.setText(String.valueOf(zone));
        } catch (
                Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void upcomingAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = AppointmentDao.getAllAppointments();
        LocalDateTime currentLdt = LocalDateTime.now();

        for (Appointment appointment : allAppointments) {
            if ((appointment.getStart().isBefore(currentLdt.plusMinutes(15))) && (appointment.getStart().isAfter(currentLdt))){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment");
                alert.setHeaderText("Upcoming Appointment");
                alert.setContentText("There is at least one appointment coming up within 15 minutes from now.");
                alert.showAndWait();
                return;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        language();
        zone();
        usernameExLbl.setVisible(false);
        passwordExLbl.setVisible(false);

    }
}
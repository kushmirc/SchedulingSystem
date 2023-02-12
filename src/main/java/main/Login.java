package mirchandani.schedulingsystem;

import dao.AppointmentDao;
import dao.CustomerDao;
import dao.FruitsQuery;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import utility.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.RBMain;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();

        //LocalDateTime currentLdt = LocalDateTime.now();
        //System.out.println(currentLdt);


        //AppointmentDao.appointmentsByCustomerId(2);

        //FruitsQuery.insert("Cherries", 1);

            //FruitsQuery.delete(7);
        //int rowsAffected = FruitsQuery.delete(7);

        /*if(rowsAffected > 0){
            System.out.println("Delete Successful");
        } else {
            System.out.println("Delete Failed");
        }*/

        //CustomerDao.getCustomer();
        //UserDao.getAllUsers();
        //AppointmentDao.getAppointment();

        launch();
        JDBC.closeConnection();

    }
}
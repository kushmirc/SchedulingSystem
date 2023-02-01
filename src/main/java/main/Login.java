package mirchandani.schedulingsystem;

import dao.CustomerDao;
import dao.FruitsQuery;
import utility.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.RBMain;


import java.io.IOException;
import java.sql.SQLException;
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
        //JDBC.openConnection();

        /*int rowsAffected = FruitsQuery.delete(7);

        if(rowsAffected > 0){
            System.out.println("Delete Successful");
        } else {
            System.out.println("Delete Failed");
        }*/

        //CustomerDao.getAllCustomers();

        RBMain.language();
        RBMain.zone();

        //launch();
        //JDBC.closeConnection();

    }
}
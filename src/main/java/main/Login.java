package mirchandani.schedulingsystem;

import helper.FruitsQuery;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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


        int rowsAffected = FruitsQuery.insert("Cherries", 1);

        if(rowsAffected > 0){
            System.out.println("Insert Successful");
        } else {
            System.out.println("Insert Failed");
        }


        launch();
        JDBC.closeConnection();
    }
}
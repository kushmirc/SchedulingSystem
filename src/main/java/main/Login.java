package mirchandani.schedulingsystem;

import utility.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

/** Class Login is the first class that runs when the application is run.
 * Class Login contains the main method, starts the stage for JavaFX, and initializes JavaFX.
 * It also opens and closes the connection to the MySQL database.
 * @author Kush Mirchandani */
public class Login extends Application {

    /** The start method for the JavaFX stage.
     * This starts the JavaFX stage, and sets the first scene to LoginScreen.fxml
     * @param stage the stage to start */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    /** This is the main method.
     * This is the first method that gets called when the program runs.
     * It first opens the connection to the MySQL database, then launches JavaFX,
     * and closes the connection to the database when the application is stopped.*/
    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();

        launch();

        JDBC.closeConnection();
    }
}
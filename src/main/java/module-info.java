module mirchandani.schedulingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens mirchandani.schedulingsystem to javafx.fxml;
    exports mirchandani.schedulingsystem;
}
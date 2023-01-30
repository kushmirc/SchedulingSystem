module mirchandani.schedulingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens mirchandani.schedulingsystem to javafx.fxml;
    opens model to javafx.base;
    exports mirchandani.schedulingsystem;
}
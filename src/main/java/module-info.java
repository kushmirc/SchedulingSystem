module mirchandani.schedulingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens mirchandani.schedulingsystem to javafx.fxml;
    exports mirchandani.schedulingsystem;
}
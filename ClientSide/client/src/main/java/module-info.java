module com.example.ihatethis {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ihatethis to javafx.fxml;
    exports com.example.ihatethis;
}
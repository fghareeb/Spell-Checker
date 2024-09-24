module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics; // This is added to explicitly require the javafx.graphics module

    opens com.example to javafx.fxml;

    exports com.example;
}

module com.example.proje2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proje2 to javafx.fxml;
    exports com.example.proje2;
}
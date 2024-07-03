module com.example.client {
    requires javafx.fxml;
    requires javafx.controls;

    requires org.json;
    requires java.sql;


    opens com.example.client to javafx.fxml;
    exports com.example.client;

}
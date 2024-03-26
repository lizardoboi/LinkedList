module com.example.linkedlist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.linkedlist to javafx.fxml;
    exports com.example.linkedlist;
}
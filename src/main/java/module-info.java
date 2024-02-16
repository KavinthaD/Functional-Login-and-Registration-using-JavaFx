module com.example.tut1_des {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tut1_des to javafx.fxml;
    exports com.example.tut1_des;
}
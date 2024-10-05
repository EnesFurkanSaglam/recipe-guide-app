module com.efs.recipeguideapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.efs.recipeguideapp to javafx.fxml;
    exports com.efs.recipeguideapp;
}
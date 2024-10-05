module com.efs.recipeguideapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.efs.recipeguideapp to javafx.fxml;
    exports com.efs.recipeguideapp;
    exports com.efs.recipeguideapp.Database;
    opens com.efs.recipeguideapp.Database to javafx.fxml;
    exports com.efs.recipeguideapp.Classes;
    opens com.efs.recipeguideapp.Classes to javafx.fxml;
}
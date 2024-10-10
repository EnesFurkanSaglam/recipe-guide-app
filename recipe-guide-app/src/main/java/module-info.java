module com.efs.recipeguideapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.efs.recipeguideapp to javafx.fxml;
    exports com.efs.recipeguideapp;
    exports com.efs.recipeguideapp.DAO;
    opens com.efs.recipeguideapp.DAO to javafx.fxml;
    exports com.efs.recipeguideapp.Entity;
    opens com.efs.recipeguideapp.Entity to javafx.fxml;
    exports com.efs.recipeguideapp.GUI;
    opens com.efs.recipeguideapp.GUI to javafx.fxml;

}
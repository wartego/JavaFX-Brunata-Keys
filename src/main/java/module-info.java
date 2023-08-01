module pl.wartego.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;


    requires org.controlsfx.controls;
    requires java.sql;
    requires mysql.connector.j;
    requires spring.security.crypto;

    opens pl.wartego.javafxtest to javafx.fxml;
    exports pl.wartego.javafxtest;
}
module pl.wartego.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires mysql.connector.j;

    opens pl.wartego.javafxtest to javafx.fxml;
    exports pl.wartego.javafxtest;
}
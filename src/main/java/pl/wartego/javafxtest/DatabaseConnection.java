package pl.wartego.javafxtest;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection() throws IOException {

        FileReader reader = new FileReader("src/main/resources/database.properties");
        Properties properties = new Properties();
        properties.load(reader);

        String datebaseDriver = properties.getProperty("jdbc.driver.class.name");
        String url = properties.getProperty("jdbc.url");
        String databaseUserName = properties.getProperty("db.username");
        String databasePassword = properties.getProperty("db.password");


        try {
            Class.forName(datebaseDriver);
            databaseLink = DriverManager.getConnection(url, databaseUserName, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;

    }
}

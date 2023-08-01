package pl.wartego.javafxtest;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection() throws IOException {
        Properties properties = new Properties();
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream("database.properties")) {
            properties.load(stream);
        }



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

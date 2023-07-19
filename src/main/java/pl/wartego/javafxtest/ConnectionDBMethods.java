package pl.wartego.javafxtest;

import java.io.IOException;
import java.sql.Connection;

public class ConnectionDBMethods {
    private static Connection connectDB;
    public static Connection getDataBaseConnect() throws IOException {
        try {
            connectDB = new DatabaseConnection().getConnection();
        }
        catch(Exception e){
            e.printStackTrace();
            }
        return connectDB;
    }


}

package pl.wartego.javafxtest;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConnectionDBMethods {
    private static Connection connectDB;
    public static Connection getDataBaseConnect() {
        try {
            connectDB = new DatabaseConnection().getConnection();
        }
        catch(Exception e){
            e.printStackTrace();
            }
        return connectDB;
    }
    public static int getCountRows() throws SQLException {
        connectDB = getDataBaseConnect();
        int queryResultInt = 0;

        String countAllRecords = "SELECT count(*) as 'VALUE' FROM encryptionkeys;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(countAllRecords);
            while (queryResult.next()){
                queryResultInt = queryResult.getInt("VALUE");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectDB.close();
        }
        return queryResultInt;
    }


}

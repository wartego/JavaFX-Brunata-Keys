package pl.wartego.javafxtest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class RegistryPageController {
private boolean userExist;
    @FXML
    public static Connection connectDB;
    @FXML
    private TextField newLogin;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField newFirstName;
    @FXML
    private TextField newLastName;

    @FXML
    private Button registerButton;
    @FXML
    private Label labelMessageIfUserExist;

    @FXML
    protected void userRegistry(ActionEvent event) throws SQLException {
        connectDB = ConnectionDBMethods.getDataBaseConnect();
        String insertNewUserQuery = ("INSERT INTO user (login,password,FirstName,LastName) VALUES (?,?,?,?);");
     try{
         PreparedStatement preparedStatement = connectDB.prepareStatement(insertNewUserQuery);

         preparedStatement.setString(1,newLogin.getText());
         preparedStatement.setString(2,PasswordValidation.HashPasswordUnderRegistryInDatabase(newPassword.getText()));
         preparedStatement.setString(3,newFirstName.getText());
         preparedStatement.setString(4, newLastName.getText());

         //check If user exist already in database
         if(userExist){
             labelMessageIfUserExist.setText("Please choose different login!");
         } else{
             int queryResult = preparedStatement.executeUpdate();
             SceneController.switchToSceneRegisterSuccess(event);
         }
         //switch to login Page

     } catch (SQLException e){
         e.getSQLState();
     } catch (IOException e) {
         throw new RuntimeException(e);
     } finally {
         connectDB.close();
     }
    }

    @FXML
    protected boolean checkIfUserExist() throws SQLException {
        int queryResultInt = 2;
        connectDB = ConnectionDBMethods.getDataBaseConnect();
        String countLogin = ("SELECT COUNT(*) AS 'LOGINEXIST' FROM user where login = ?");
        try {
            PreparedStatement statement = connectDB.prepareStatement(countLogin);
            statement.setString(1,newLogin.getText());
            ResultSet queryResult = statement.executeQuery();
            while (queryResult.next()) {
                queryResultInt = queryResult.getInt("LOGINEXIST");
                System.out.println(queryResultInt);

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            connectDB.close();
        }
        if (queryResultInt == 1){
            labelMessageIfUserExist.setText("User already exist!!");
            labelMessageIfUserExist.setStyle("-fx-text-fill: #ff0117");
            userExist = true;
            return true;
        } else {
            labelMessageIfUserExist.setText("User is available!!");
            labelMessageIfUserExist.setStyle("-fx-text-fill: #60ff00;-fx-font-size: 20px");
            userExist = false;
            return false;
        }
    }
}

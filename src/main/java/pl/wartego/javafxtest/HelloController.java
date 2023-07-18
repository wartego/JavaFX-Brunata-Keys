package pl.wartego.javafxtest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;


    @FXML
    protected void setCancelButtonOnClick(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void loginButtonOnClick(ActionEvent e) throws IOException {

        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            loginMessageLabel.setText("You try to login");
            validateLogin(); // call Method
        } else {
            loginMessageLabel.setText("Please input login and password first!");
            setUserAndPasswordFieldBlank();
        }
    }

    private void setUserAndPasswordFieldBlank() {
        usernameTextField.setText("");
        passwordTextField.setText("");
    }

    @FXML
    protected void validateLogin() throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = String.format("SELECT count(1) FROM User where login = '%s' AND password = '%s'", usernameTextField.getText(), passwordTextField.getText());

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setStyle("-fx-text-fill: #00ff1d");
                    loginMessageLabel.setText("Welcome!");
                    Stage currentStage = (Stage)loginMessageLabel.getScene().getWindow();
                    currentStage.close(); // close current scene
                    openKeyPage();
                } else {
                    loginMessageLabel.setText("Invalid Login. Please try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void openKeyPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KeyPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stageNew = new Stage();
            stageNew.setScene(new Scene(root));
            stageNew.initStyle(StageStyle.TRANSPARENT);
            stageNew.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
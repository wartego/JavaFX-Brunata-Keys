package pl.wartego.javafxtest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;


public class HelloController {

    public static Connection connectDB;
    private Stage stage;
    private Scene scene;
    private ActionEvent eventAction;
    public static String loginUser;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    protected void setCancelButtonOnClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void loginButtonOnClick(ActionEvent event) throws SQLException {

        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            loginMessageLabel.setText("You try to login");
            validateLogin(event); // call Method
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
    protected void validateLogin(ActionEvent event) throws SQLException {
        connectDB = ConnectionDBMethods.getDataBaseConnect();
        eventAction = event;
        //returning Hashed Password
        String getPasswordByUser = String.format("SELECT password FROM user where login = '%s'", usernameTextField.getText());
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getPasswordByUser);
            while (queryResult.next()) {
                if (PasswordValidation.ifHashMatchToPassword(queryResult.getString(1), passwordTextField.getText())) {
                    Stage currentStage = (Stage) loginMessageLabel.getScene().getWindow();
                    loginUser = usernameTextField.getText();
                    currentStage.close(); // close current scene
                    openKeyPage(event);

                } else {
                    loginMessageLabel.setText("Invalid Login. Please try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.close();
        }
    }


    @FXML
    protected void keyEnterPressed(KeyEvent event) throws SQLException {
        switch (event.getCode()) {
            case ENTER -> loginButtonOnClick(eventAction);
        }
    }

    protected void openKeyPage(ActionEvent event) throws IOException {
        SceneController.switchToSceneKeyPage(event);
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KeyPage.fxml"));
//            Parent root = fxmlLoader.load();
//            Stage stageNew = new Stage();
//            stageNew.setScene(new Scene(root));
//            stageNew.initStyle(StageStyle.TRANSPARENT);
//            stageNew.show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    @FXML
    public void switchToSceneRegistry(ActionEvent event) throws IOException {
        SceneController.switchToSceneRegistry(event);
    }


}
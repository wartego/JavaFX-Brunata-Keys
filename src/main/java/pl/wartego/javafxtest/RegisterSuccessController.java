package pl.wartego.javafxtest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class RegisterSuccessController {
    @FXML
    private Label infoLabel;

    @FXML
    private Button nextButton;

    @FXML
    protected void goToLoginPage(ActionEvent event) throws IOException {
        SceneController.switchToSceneHello(event);
    }

}

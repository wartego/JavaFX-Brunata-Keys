package pl.wartego.javafxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class KeyPageController {
    @FXML
    private Button buttonClose;
    @FXML
    protected void buttonCloseAction() throws IOException {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();

}


}

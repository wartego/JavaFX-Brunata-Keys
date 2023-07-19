package pl.wartego.javafxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class KeyPageController {
    @FXML
    private Button buttonClose;

    @FXML
    private Button generateButton;

    @FXML
    private TextField textKeyBefore;

    @FXML
    private TextField textKeyAfter;
    @FXML
    protected void buttonCloseAction() throws IOException {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void buttonGenerateAction() {
        SequanceChange sequanceChange = new SequanceChange();
        String keyAfterChanges = sequanceChange.changeSequence(textKeyBefore.getText());

        textKeyAfter.setText(keyAfterChanges);
    }
}

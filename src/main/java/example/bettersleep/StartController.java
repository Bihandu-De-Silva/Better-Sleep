package example.bettersleep;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private TextField NameField;

    @FXML
    private AnchorPane StartPane;

    @FXML
    private void handleStartButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputScreen.fxml"));
            Parent root = loader.load();

            InputController inputController = loader.getController();
            inputController.setUserName(NameField.getText().trim());

            Stage stage = (Stage) StartPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


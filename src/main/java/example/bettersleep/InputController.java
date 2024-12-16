package example.bettersleep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputController {

    @FXML
    private TextField SleptTime;

    @FXML
    private TextField WakeupTime;

    @FXML
    private Text GreetingText;

    @FXML
    private Button CalculateButton;

    public void setUserName(String userName) {

        GreetingText.setText("Good Night, " + userName + "!");
    }

    @FXML
    void initialize() {
        CalculateButton.setOnAction(event -> handleCalculateButtonClick());
    }

    private void handleCalculateButtonClick() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H.mm");
            LocalTime sleepTime = LocalTime.parse(SleptTime.getText().trim(), formatter);
            LocalTime wakeTime = LocalTime.parse(WakeupTime.getText().trim(), formatter);

            long sleepDurationMinutes = java.time.Duration.between(sleepTime, wakeTime).toMinutes();
            if (sleepDurationMinutes < 0) {
                sleepDurationMinutes += 24 * 60;
            }
            long hours = sleepDurationMinutes / 60;
            long minutes = sleepDurationMinutes % 60;
            String duration = hours + "h " + minutes + "m";

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultScreen.fxml"));
            Parent root = loader.load();
            ResultController resultController = loader.getController();
            resultController.setSleepData(duration);

            Stage stage = (Stage) CalculateButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format! Use HH.mm (e.g., 23.30).");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package example.bettersleep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class ResultController {

    @FXML
    private Text SleepDurationText;

    @FXML
    private Text SleepFeedbackText;

    @FXML
    private Button BackButton;

    public void setSleepData(String duration) {
        SleepDurationText.setText("Duration: " + duration);
        String[] parts = duration.split("h |m");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        double totalHours = hours + (minutes / 60.0);
        if (totalHours >= 7 && totalHours <= 9) {
            SleepFeedbackText.setText("You had enough sleep. Great job!");
        } else if (totalHours < 8) {
            SleepFeedbackText.setText("You had insufficient sleep. Try to rest more.");
        } else {
            SleepFeedbackText.setText("You overslept. Keep a consistent sleep schedule.");
        }
    }

    @FXML
    void initialize() {
        BackButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InputScreen.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) BackButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}




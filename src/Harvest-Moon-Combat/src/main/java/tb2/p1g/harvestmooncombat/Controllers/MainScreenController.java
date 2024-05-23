package tb2.p1g.harvestmooncombat.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainScreenController {

    public static Stage primaryStage;
    public static AnchorPane root;

    @FXML
    private ImageView newGameButton;

    @FXML
    private void initialize() {
        // Start the blinking effect
        startBlinkingEffect();
    }

    private void startBlinkingEffect() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> newGameButton.setOpacity(0)),
                new KeyFrame(Duration.seconds(1), e -> newGameButton.setOpacity(1))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void startNewGame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/GameScreen.fxml"));
            AnchorPane mainGameRoot = fxmlLoader.load();
            Scene mainGameScene = new Scene(mainGameRoot, 600, 600); // Adjust dimensions as needed

            root = mainGameRoot;
            primaryStage.setScene(mainGameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage stage) {
        MainScreenController.primaryStage = stage;
    }
}
package tb2.p1g.harvestmooncombat.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

public class MainScreenController {
    
    @FXML
    private ImageView newGameButton;

    @FXML
    private void initialize() {
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
        ViewFactory.LoadGameScreen();
    }
}

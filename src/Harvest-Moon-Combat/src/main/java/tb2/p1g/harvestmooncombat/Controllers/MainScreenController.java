package tb2.p1g.harvestmooncombat.Controllers;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

import javax.swing.text.View;


public class MainScreenController {

    public static Stage primaryStage;
    public static AnchorPane root;

    @FXML
    private ImageView newGameButton;

    @FXML
    private AnchorPane parentPane;

    @FXML
    private void initialize() {
        newGameButtonBlink();
    }

    private void newGameButtonBlink() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> newGameButton.setOpacity(0)),
                new KeyFrame(Duration.seconds(1), e -> newGameButton.setOpacity(1))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void startNewGame() {

        Pane blackPane = new Pane();
        blackPane.setStyle("-fx-background-color: black;");
        blackPane.setPrefSize(600, 600);
        blackPane.setOpacity(0);

        ViewFactory.Root.getChildren().add(blackPane);

        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(0.5), blackPane);
        fadeInTransition.setToValue(1);

        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(0.5), blackPane);
        fadeOutTransition.setToValue(0);

        fadeInTransition.setOnFinished(event -> {
            fadeOutTransition.play();
            ViewFactory.LoadGameScreen();
        });

        fadeOutTransition.setOnFinished(event -> {
            ViewFactory.Root.getChildren().remove(blackPane);
        });

        fadeInTransition.play();
    }
}

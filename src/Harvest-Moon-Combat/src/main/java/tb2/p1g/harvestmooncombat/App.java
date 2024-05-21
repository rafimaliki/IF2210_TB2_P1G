package tb2.p1g.harvestmooncombat;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tb2.p1g.harvestmooncombat.Controllers.GameScreenController;
import tb2.p1g.harvestmooncombat.Models.GameManager;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class App extends Application {

    public static Stage PrimaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        GameManager gameManager = new GameManager();
        System.out.println("Tes");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/GameScreen.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/Bear.css")).toExternalForm());
        stage.setTitle("Harvest Moon Combat");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        this.PrimaryStage = stage;

        GameScreenController controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);
        controller.shuffleDeck();
        addRandomBearPane(root);
    }

    private void addRandomBearPane(AnchorPane root) {
        Pane bear = new Pane();
        bear.getStyleClass().add("bear");
        bear.setPrefSize(100, 100); // Adjust the size as needed

        root.getChildren().add(bear);
        movePaneInSnakePattern(bear, root);
    }

    private void movePaneInSnakePattern(Pane bear, AnchorPane root) {
        Random random = new Random();
        final int[] direction = {0}; // 0: right, 1: down, 2: left, 3: up
        final boolean[] angry = {false};

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    // 10% chance to change the bear's mood
                    if (random.nextDouble() < 0.1) {
                        angry[0] = !angry[0];
                        bear.getStyleClass().removeAll("bear", "angrybear");
                        bear.getStyleClass().add(angry[0] ? "angrybear" : "bear");
                    }

                    double x = bear.getLayoutX();
                    double y = bear.getLayoutY();

                    // Move in the current direction
                    switch (direction[0]) {
                        case 0: // right
                            x += 5;
                            break;
                        case 1: // down
                            y += 5;
                            break;
                        case 2: // left
                            x -= 5;
                            break;
                        case 3: // up
                            y -= 5;
                            break;
                    }

                    // Check if the new position is within the root bounds
                    if (x < 10) x = 10;
                    if (y < 90) y = 90;
                    if (x > 360) x = 360;
                    if (y > 450) y = 450;

                    // Set the new position
                    bear.setLayoutX(x);
                    bear.setLayoutY(y);

                    // Randomly change direction occasionally
                    if (random.nextDouble() < 0.1) { // 10% chance to change direction
                        direction[0] = (direction[0] + 1 + random.nextInt(3)) % 4;
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }
}

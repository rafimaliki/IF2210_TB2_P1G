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
import tb2.p1g.harvestmooncombat.Components.AngryBear;


import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class App extends Application {

    public static Stage PrimaryStage;
    public static AnchorPane Root;

    @Override
    public void start(Stage stage) throws IOException {
        this.PrimaryStage = stage;

        GameManager gameManager = GameManager.getInstance();
        gameManager.startGame();


        System.out.println("Tes");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/GameScreen.fxml"));
        AnchorPane root = fxmlLoader.load();
        this.Root = root;
        Scene scene = new Scene(root, 600, 600);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/Bear.css")).toExternalForm());

        stage.setTitle("Harvest Moon Combat");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        GameScreenController controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);
//        controller.shuffleDeck();
//        AngryBear.addRandomBear(root);
    }

    public static void main(String[] args) {
        launch();
    }
}

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
import tb2.p1g.harvestmooncombat.Controllers.MainScreenController;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.SimpanInterface;
import tb2.p1g.harvestmooncombat.Models.MuatInterface;

import tb2.p1g.harvestmooncombat.PluginLoader.PluginLoader;
import tb2.p1g.harvestmooncombat.Components.AngryBear;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class App extends Application {

    public static Stage PrimaryStage;
    public static AnchorPane Root;

    @Override
    public void start(Stage stage) throws IOException {

        GameManager gameManager = GameManager.getInstance();
        gameManager.startGame();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/MainScreen.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 600);

        MainScreenController controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);

        stage.setTitle("Harvest Moon Combat");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

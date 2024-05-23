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
import tb2.p1g.harvestmooncombat.Models.SimpanInterface;
import tb2.p1g.harvestmooncombat.Models.MuatInterface;

import tb2.p1g.harvestmooncombat.PluginLoader.PluginLoader;
import tb2.p1g.harvestmooncombat.Components.AngryBear;


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
        this.PrimaryStage = stage;
        String filepath = System.getProperty("user.dir") + "/src/main/java/tb2/p1g/harvestmooncombat/Plugins/";
        File directory = new File(filepath);

        // Check if the specified path points to a directory
        if (directory.isDirectory()) {
            // Get a list of all files in the directory
            File[] files = directory.listFiles();

            // Iterate over the files and print their names
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                }
            }
        } else {
            System.out.println("Specified path is not a directory: " + filepath);
        }
        PluginLoader pluginLoader = new PluginLoader();
        try{
            pluginLoader.loadPlugins(filepath);
            List<MuatInterface> muatplug = pluginLoader.getMuatPlugins();
            List<SimpanInterface> simpanplug = pluginLoader.getSimpanPlugins();

            for(MuatInterface mi : muatplug){
                mi.loadGameState("Ini plugin");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


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

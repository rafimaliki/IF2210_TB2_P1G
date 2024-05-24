package tb2.p1g.harvestmooncombat;
import javafx.application.Application;

import javafx.stage.Stage;
import tb2.p1g.harvestmooncombat.Audio.AudioFactory;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        GameManager gameManager = GameManager.getInstance();
        gameManager.startGame();

        ViewFactory.setPrimaryStage(stage);

        String farmAudio = getClass().getResource("/Audio/Farm.mp3").toExternalForm();
        String bearAudio = getClass().getResource("/Audio/BearAttack.mp3").toExternalForm();

        AudioFactory.addAudio("farm", farmAudio);
        AudioFactory.addAudio("bear", bearAudio);

        AudioFactory.playAudio("farm");
        ViewFactory.LoadMainScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}

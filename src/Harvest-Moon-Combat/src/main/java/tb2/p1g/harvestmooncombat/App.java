package tb2.p1g.harvestmooncombat;
import javafx.application.Application;

import javafx.stage.Stage;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        GameManager gameManager = GameManager.getInstance();
        gameManager.startGame();

        ViewFactory vf = new ViewFactory();
        vf.setPrimaryStage(stage);

        ViewFactory.LoadMainScreen();
    }

    public static void main(String[] args) {
        launch();
    }
}

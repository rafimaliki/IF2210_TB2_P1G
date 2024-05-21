package tb2.p1g.harvestmooncombat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tb2.p1g.harvestmooncombat.Controllers.GameScreenController;

import java.io.IOException;
//import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class App extends Application {

    public static Stage PrimaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/GameScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/Cards.css")).toExternalForm());
        stage.setTitle("Harvest Moon Combat");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        this.PrimaryStage = stage;

        GameScreenController controller = fxmlLoader.getController(); // Change 'loader' to 'fxmlLoader'
        controller.setPrimaryStage(stage); // Change 'primaryStage' to 'stage'
        controller.shuffleDeck();
    }

    public static void main(String[] args) {
        launch();
    }
}

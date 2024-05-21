package tb2.p1g.harvestmooncombat.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tb2.p1g.harvestmooncombat.App;
import tb2.p1g.harvestmooncombat.Components.Draggables;

import tb2.p1g.harvestmooncombat.Components.ActiveDeck;
import tb2.p1g.harvestmooncombat.Controllers.ShuffleScreenController;


public class ViewFactory {

    public static void ShowShuffleScreen(Stage primaryStage, ActiveDeck activeDeck) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/ShuffleScreen.fxml"));
            Parent root = fxmlLoader.load();

            ShuffleScreenController controller = fxmlLoader.getController();
            controller.setActiveDeck(activeDeck);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);
            stage.initStyle(StageStyle.TRANSPARENT);

            Scene scene = new Scene(root);
            stage.setScene(scene);

//            stage.setOnShown(event -> {
//                stage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - stage.getWidth() / 2);
//                stage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - stage.getHeight() / 2);
//            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

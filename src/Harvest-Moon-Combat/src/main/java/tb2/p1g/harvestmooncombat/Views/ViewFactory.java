package tb2.p1g.harvestmooncombat.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tb2.p1g.harvestmooncombat.App;

import tb2.p1g.harvestmooncombat.Components.ActiveDeck;
import tb2.p1g.harvestmooncombat.Components.Card;
import tb2.p1g.harvestmooncombat.Controllers.TokoScreenController;
import tb2.p1g.harvestmooncombat.Controllers.CardDetailController;
import tb2.p1g.harvestmooncombat.Controllers.ShuffleScreenController;

import java.util.Objects;


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
            scene.setFill(null);
            stage.setScene(scene);

            stage.setOnShown(event -> {
                stage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - stage.getWidth() / 2);
                stage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - stage.getHeight() / 2 + 10);
            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowCardDetail(String slotIndex) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/CardDetailScreen.fxml"));
            Parent root = fxmlLoader.load();

            CardDetailController controller = fxmlLoader.getController();
            controller.setCard(slotIndex);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(App.PrimaryStage);
            stage.initStyle(StageStyle.TRANSPARENT);


            Scene scene = new Scene(root);
            scene.setFill(null);
            scene.getStylesheets().add(Objects.requireNonNull(ViewFactory.class.getResource("/Styles/Cards.css")).toExternalForm());
            stage.setScene(scene);

            stage.setOnShown(event -> {
                stage.setX(App.PrimaryStage.getX() + App.PrimaryStage.getWidth() / 2 - stage.getWidth() / 2);
                stage.setY(App.PrimaryStage.getY() + App.PrimaryStage.getHeight() / 2 - stage.getHeight() / 2 + 10);
            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowTokoScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/TokoScreen.fxml"));
            Parent root = fxmlLoader.load();

            TokoScreenController controller = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(App.PrimaryStage);
            stage.initStyle(StageStyle.TRANSPARENT);

            Scene scene = new Scene(root);
//            scene.setFill(null);
            stage.setScene(scene);

            scene.getStylesheets().add(Objects.requireNonNull(ViewFactory.class.getResource("/Styles/Cards.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(ViewFactory.class.getResource("/Styles/Bear.css")).toExternalForm());
            stage.setScene(scene);

            stage.setOnShown(event -> {
                stage.setX(App.PrimaryStage.getX() + App.PrimaryStage.getWidth() / 2 - stage.getWidth() / 2);
                stage.setY(App.PrimaryStage.getY() + App.PrimaryStage.getHeight() / 2 - stage.getHeight() / 2 + 10);
            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

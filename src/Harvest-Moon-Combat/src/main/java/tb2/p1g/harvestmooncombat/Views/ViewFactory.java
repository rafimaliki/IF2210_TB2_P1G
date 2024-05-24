package tb2.p1g.harvestmooncombat.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tb2.p1g.harvestmooncombat.App;

import tb2.p1g.harvestmooncombat.Controllers.CardDetailController;

import java.io.IOException;
import java.util.Objects;

public class ViewFactory {

    public static Stage PrimaryStage;
    public static AnchorPane Root;

    public void setPrimaryStage(Stage stage) {
        PrimaryStage = stage;
    }

    public static void LoadMainScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/MainScreen.fxml"));
            Root = fxmlLoader.load();
            Scene scene = new Scene(Root, 600, 600);

            PrimaryStage.setTitle("Harvest Moon Combat");
            PrimaryStage.setResizable(false);
            PrimaryStage.setScene(scene);
            PrimaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LoadGameScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ViewFactory.class.getResource("/Fxml/GameScreen.fxml"));
            AnchorPane mainGameRoot = fxmlLoader.load();

            Scene mainGameScene = new Scene(mainGameRoot, 600, 600); // Adjust dimensions as needed
            mainGameScene.getStylesheets().add(ViewFactory.class.getResource("/Styles/Bear.css").toExternalForm());

            PrimaryStage.setScene(mainGameScene);
            Root = mainGameRoot;

            ViewFactory.ShowShuffleScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ShowShuffleScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/ShuffleScreen.fxml"));
            Parent ModalRoot = fxmlLoader.load();

            Stage ModalStage = new Stage();

            ModalStage.initModality(Modality.APPLICATION_MODAL);
            ModalStage.initStyle(StageStyle.TRANSPARENT);
            ModalStage.initOwner(PrimaryStage);

            Scene ModalScene = new Scene(ModalRoot);
            ModalScene.setFill(null);
            ModalStage.setScene(ModalScene);

            ModalStage.setOnShown(event -> {
                ModalStage.setX(PrimaryStage.getX() + PrimaryStage.getWidth() / 2 - ModalStage.getWidth() / 2);
                ModalStage.setY(PrimaryStage.getY() + PrimaryStage.getHeight() / 2 - ModalStage.getHeight() / 2 + 10);
            });

            ModalStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowCardDetail(String slotIndex) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/CardDetailScreen.fxml"));
            Parent ModalRoot = fxmlLoader.load();

            CardDetailController controller = fxmlLoader.getController();

            Stage ModalStage = new Stage();
            ModalStage.initOwner(PrimaryStage);
            ModalStage.initModality(Modality.APPLICATION_MODAL);
            ModalStage.initStyle(StageStyle.TRANSPARENT);

            controller.setCard(slotIndex);
            controller.setStage(ModalStage);

            Scene ModalScene = new Scene(ModalRoot);
            ModalScene.setFill(null);
            ModalScene.getStylesheets().add(Objects.requireNonNull(ViewFactory.class.getResource("/Styles/Cards.css")).toExternalForm());

            ModalStage.setScene(ModalScene);

            ModalStage.setOnShown(event -> {
                ModalStage.setX(PrimaryStage.getX() + PrimaryStage.getWidth() / 2 - ModalStage.getWidth() / 2);
                ModalStage.setY(PrimaryStage.getY() + PrimaryStage.getHeight() / 2 - ModalStage.getHeight() / 2 + 10);
            });

            ModalStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowTokoScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/TokoScreen.fxml"));
            Parent ModalRoot = fxmlLoader.load();

            Stage ModalStage = new Stage();
            ModalStage.initModality(Modality.APPLICATION_MODAL);
            ModalStage.initOwner(PrimaryStage);
            ModalStage.initStyle(StageStyle.TRANSPARENT);

            Scene ModalScene = new Scene(ModalRoot);
            ModalStage.setScene(ModalScene);

            ModalScene.getStylesheets().add(Objects.requireNonNull(ViewFactory.class.getResource("/Styles/Cards.css")).toExternalForm());
            ModalScene.getStylesheets().add(Objects.requireNonNull(ViewFactory.class.getResource("/Styles/Bear.css")).toExternalForm());
            ModalStage.setScene(ModalScene);

            ModalStage.setOnShown(event -> {
                ModalStage.setX(PrimaryStage.getX() + PrimaryStage.getWidth() / 2 - ModalStage.getWidth() / 2);
                ModalStage.setY(PrimaryStage.getY() + PrimaryStage.getHeight() / 2 - ModalStage.getHeight() / 2 + 10);
            });

            ModalStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowLoadScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/MuatScreen.fxml"));
            loadModalRoot(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowSaveScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/SimpanScreen.fxml"));
            loadModalRoot(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowPlugginScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/PlugginScreen.fxml"));
            loadModalRoot(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowEndScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/EndScreen.fxml"));
            loadModalRoot(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadModalRoot(FXMLLoader fxmlLoader) throws IOException {
        Parent ModalRoot = fxmlLoader.load();

        Stage ModalStage = new Stage();
        ModalStage.initModality(Modality.APPLICATION_MODAL);
        ModalStage.initOwner(PrimaryStage);
        ModalStage.initStyle(StageStyle.TRANSPARENT);

        Scene ModalScene = new Scene(ModalRoot);
        ModalScene.setFill(null);
        ModalScene.getStylesheets().add(Objects.requireNonNull(ViewFactory.class.getResource("/Styles/Cards.css")).toExternalForm());
        ModalStage.setScene(ModalScene);

        ModalStage.setOnShown(event -> {
            ModalStage.setX(PrimaryStage.getX() + PrimaryStage.getWidth() / 2 - ModalStage.getWidth() / 2);
            ModalStage.setY(PrimaryStage.getY() + PrimaryStage.getHeight() / 2 - ModalStage.getHeight() / 2 + 10);
        });

        ModalStage.show();
    }

}

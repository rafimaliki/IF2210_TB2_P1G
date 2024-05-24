package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import tb2.p1g.harvestmooncombat.Components.Draggables;

import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

import java.util.HashMap;
import java.util.Map;

public class GameScreenController {

    @FXML public Label guldenPlayer2;
    @FXML public Label guldenPlayer1;

    @FXML private GridPane activeDeckGrid, ladangGrid;
    @FXML private Pane beruangBox;

    @FXML private Label turnNumber, player1Name, player2Name;
    @FXML private Button ladangKuButton, ladangLawanButton;

    private Draggables draggables;
    private int playerTurn = 1;

    private final Map<String, Button> buttonList = new HashMap<>();
    GameManager gameManager = GameManager.getInstance();

    @FXML
    public void initialize() {
        draggables = new Draggables(activeDeckGrid, ladangGrid);
        Thread refreshThread = new Thread(draggables);
        refreshThread.start();

        guldenPlayer1.setText(String.valueOf(gameManager.getPlayerOne().getGulden()));
        guldenPlayer2.setText(String.valueOf(gameManager.getPlayerTwo().getGulden()));

        buttonList.put("ladangKuButton", ladangKuButton);
        buttonList.put("ladangLawanButton", ladangLawanButton);

        clickButton("ladangKuButton");

        player1Name.setStyle("-fx-background-color: #50C878;");
        beruangBox.setVisible(false);

        gameManager.setBeruangBox(beruangBox);
    }

    @FXML
    protected void nextButtonAction() {
        if(gameManager.isBearAttackInProgress()){
            System.out.println("Beruang sedang menyerang!");
            return;
        }

        if (!gameManager.getIsRunning()){
            System.out.println("Game is not running");
            return;
        }

        turnNumber.setText(String.valueOf(Integer.parseInt(turnNumber.getText()) + 1));

        draggables.getActiveDeck().clearCards();
        draggables.getLadang().clearCards();

        playerTurn = playerTurn == 1 ? 2 : 1;

        unclickButtons();
        clickButton("ladangKuButton");

        if (playerTurn == 1) {
            player1Name.setStyle("-fx-background-color: #50C878;");
            player2Name.setStyle("-fx-background-color: none;");
        } else {
            player1Name.setStyle("-fx-background-color: none;");
            player2Name.setStyle("-fx-background-color: #50C878;");
        }
        gameManager.nextTurn();

        if (gameManager.getIsRunning()){
            gameManager.getCurrentPlayer().getLadang().displayLadang();
            gameManager.getCurrentPlayer().getDeckAktif().displayInfoDeck();
            gameManager.getCurrentPlayer().getLadang().displayDataKartuLadang();
            ViewFactory.ShowShuffleScreen();
        } else {
            ViewFactory.ShowEndScreen();
            turnNumber.setText("END");
        }
    }

    @FXML
    protected  void tokoButtonAction(){
        System.out.println("Toko button clicked");

        if(gameManager.isBearAttackInProgress()){
            System.out.println("Beruang sedang menyerang!");
            return;
        }

        ViewFactory.ShowTokoScreen();
    }

    @FXML
    protected void ladangKuButtonAction(){
        System.out.println("Ladang ku button clicked");

        if(gameManager.isBearAttackInProgress()){
            System.out.println("Beruang sedang menyerang!");
            return;
        }

        gameManager.setLadang(gameManager.getCurrentPlayer().getLadang());
        gameManager.setViewLawan();

        unclickButtons();
        clickButton("ladangKuButton");
    }

    @FXML
    protected void ladangLawanButtonAction(){
        System.out.println("Ladang lawan button clicked");

        if(gameManager.isBearAttackInProgress()){
            System.out.println("Beruang sedang menyerang!");
            return;
        }

        gameManager.inverseLadang();
        gameManager.setViewLawan();

        unclickButtons();
        clickButton("ladangLawanButton");
    }

    @FXML
    protected void saveButtonAction(){
        System.out.println("Save button clicked");

        if(gameManager.isBearAttackInProgress()){
            System.out.println("Beruang sedang menyerang!");
            return;
        }

        ViewFactory.ShowSaveScreen();
    }

    @FXML
    protected void loadButtonAction(){
        System.out.println("Load button clicked");

        if(gameManager.isBearAttackInProgress()){
            System.out.println("Beruang sedang menyerang!");
            return;
        }

        ViewFactory.ShowLoadScreen();
    }

    @FXML
    protected void pluginButtonAction(){
        System.out.println("Plugin button clicked");

        if(gameManager.isBearAttackInProgress()){
            System.out.println("Beruang sedang menyerang!");
            return;
        }

        ViewFactory.ShowPlugginScreen();
    }

    protected void unclickButtons() {
        for (Button button : buttonList.values()) {
            button.setStyle("-fx-background-color: #eee6e6;");
        }
    }

    protected void clickButton(String buttonName) {
        unclickButtons();
        buttonList.get(buttonName).setStyle("-fx-background-color: #50C878;");
    }
}
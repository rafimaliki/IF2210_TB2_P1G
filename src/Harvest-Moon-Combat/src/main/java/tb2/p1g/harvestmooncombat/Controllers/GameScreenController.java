package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import tb2.p1g.harvestmooncombat.Components.Draggables;

import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javafx.stage.Stage;


public class GameScreenController {

    @FXML private GridPane activeDeckGrid;
    @FXML private GridPane ladangGrid;
    @FXML private Label turnNumber, player1Name, player2Name;
    @FXML private Button ladangKuButton, ladangLawanButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private Draggables draggables;

    // variabel seharusnya di taro di kelas game state,
    private int playerTurn = 1;
    private int ladangShow = 1;
    // dan map ini bisa di bikin kelas sendiri
    private Map<Integer, Map<String, Pane>> LadangSave = new HashMap<>(2);
    private Map<Integer, Map<String, Pane>> ActiveDeckSave = new HashMap<>(2);

    @FXML
    public void initialize() {
        draggables = new Draggables(activeDeckGrid, ladangGrid);

        LadangSave.put(1, new HashMap<>());
        LadangSave.put(2, new HashMap<>());
        ActiveDeckSave.put(1, new HashMap<>());
        ActiveDeckSave.put(2, new HashMap<>());

        // set bg color for Ladanku
        ladangKuButton.setStyle("-fx-background-color: #50C878;");
        player1Name.setStyle("-fx-background-color: #50C878;");
    }

    @FXML
    public void shuffleDeck() {
//        draggables.getActiveDeck().shuffle();
        System.out.println("Open shuffle screen");
        ViewFactory.ShowShuffleScreen(this.primaryStage, draggables.getActiveDeck());
    }

    @FXML
    protected void nextTurn() {
        turnNumber.setText(String.valueOf(Integer.parseInt(turnNumber.getText()) + 1));

        ActiveDeckSave.put(playerTurn, draggables.getActiveDeck().saveCards());
        LadangSave.put(ladangShow, draggables.getLadang().saveCards());

        draggables.getActiveDeck().clearCards();
        draggables.getLadang().clearCards();

        playerTurn = playerTurn == 1 ? 2 : 1;
        ladangShow = playerTurn;

        draggables.loadActiveDeck(ActiveDeckSave.get(playerTurn));
        draggables.loadLadang(LadangSave.get(playerTurn));

        ladangKuButton.setStyle("-fx-background-color: #50C878;");
        ladangLawanButton.setStyle("-fx-background-color: #eee6e6;");

        if (playerTurn == 1) {
            player1Name.setStyle("-fx-background-color: #50C878;");
            player2Name.setStyle("-fx-background-color: none;");
        } else {
            player1Name.setStyle("-fx-background-color: none;");
            player2Name.setStyle("-fx-background-color: #50C878;");
        }

        shuffleDeck();
    }

    @FXML
    protected void loadLadangLawan(){
        if (ladangShow != playerTurn) return;

        LadangSave.put(ladangShow, draggables.getLadang().saveCards());
        draggables.getLadang().clearCards();

        ladangShow = playerTurn == 1 ? 2 : 1;
        draggables.loadLadang(LadangSave.get(ladangShow));

        ladangKuButton.setStyle("-fx-background-color: #eee6e6;");
        ladangLawanButton.setStyle("-fx-background-color: #50C878;");
    }

    @FXML
    protected void loadLadangKu(){
        if (ladangShow == playerTurn) return;

        LadangSave.put(ladangShow, draggables.getLadang().saveCards());
        draggables.getLadang().clearCards();

        ladangShow = playerTurn;
        draggables.loadLadang(LadangSave.get(ladangShow));

        ladangKuButton.setStyle("-fx-background-color: #50C878;");
        ladangLawanButton.setStyle("-fx-background-color: #eee6e6;");
    }

    @FXML
    protected void toggleGridView() {
        if (activeDeckGrid.isVisible()) {
            activeDeckGrid.setVisible(false);
            ladangGrid.setVisible(false);
        } else {
            activeDeckGrid.setVisible(true);
            ladangGrid.setVisible(true);
        }
    }



}

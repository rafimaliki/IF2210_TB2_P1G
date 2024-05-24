package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import tb2.p1g.harvestmooncombat.Components.Draggables;

import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.KartuProduk;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;
import tb2.p1g.harvestmooncombat.Components.Card;
import tb2.p1g.harvestmooncombat.Models.SeranganBeruang;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javafx.stage.Stage;

import javax.swing.text.View;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class GameScreenController {

    @FXML public Label GuldenPlayer2;
    @FXML public Label GuldenPlayer1;

    @FXML private AnchorPane root;
    @FXML private GridPane activeDeckGrid, ladangGrid;
    @FXML private Pane beruangBox;

    @FXML private Label turnNumber, player1Name, player2Name;
    @FXML private Button ladangKuButton, ladangLawanButton, tokoButton, saveButton, loadButton, pluginButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private Draggables draggables;
    private Thread refreshThread;
    // variabel seharusnya di taro di kelas game state,
    private int playerTurn = 1;
    private int ladangShow = 1;

    private Map<Integer, Map<String, Card>> LadangSave = new HashMap<>(2);
    private Map<Integer, Map<String, Card>> ActiveDeckSave = new HashMap<>(2);
    private final Map<String, Button> buttonList = new HashMap<>();

    @FXML
    public void initialize() {
        draggables = new Draggables(activeDeckGrid, ladangGrid);
        refreshThread = new Thread(draggables);
        refreshThread.start();

        GuldenPlayer1.setText(String.valueOf(GameManager.getInstance().getPlayerOne().getGulden()));
        GuldenPlayer2.setText(String.valueOf(GameManager.getInstance().getPlayerTwo().getGulden()));



        LadangSave.put(1, new HashMap<>());
        LadangSave.put(2, new HashMap<>());
        ActiveDeckSave.put(1, new HashMap<>());
        ActiveDeckSave.put(2, new HashMap<>());

        buttonList.put("ladangKuButton", ladangKuButton);
        buttonList.put("ladangLawanButton", ladangLawanButton);
        buttonList.put("tokoButton", tokoButton);
        buttonList.put("saveButton", saveButton);
        buttonList.put("loadButton", loadButton);
        buttonList.put("pluginButton", pluginButton);

        setButtonClicked("ladangKuButton");
        player1Name.setStyle("-fx-background-color: #50C878;");
        beruangBox.setVisible(false);

        GameManager gm = GameManager.getInstance();
        gm.setBeruangBox(beruangBox);

//        ViewFactory.ShowMainScreen();
    }

    @FXML
    protected void nextButtonAction() {
        GameManager gm = GameManager.getInstance();
        if(gm.isBearAttackInProgress()){
            System.out.println("Bear is attacking, cannot end turn");
            return;
        }

        if (!gm.getIsRunning()){
            System.out.println("Game is not running");
            return;
        }

        if(gm.isBearAttackInProgress()){
            System.out.println("Bear is attacking, cannot end turn");
            return;
        }

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
        gm.nextTurn();

        if (gm.getIsRunning()){
            gm.getCurrentPlayer().getLadang().displayLadang();
            gm.getCurrentPlayer().getDeckAktif().displayInfoDeck();
            gm.getCurrentPlayer().getLadang().displayDataKartuLadang();
            ViewFactory.ShowShuffleScreen();
        } else {
            ViewFactory.ShowEndScreen();
            turnNumber.setText("END");
        }
    }

    @FXML
    protected  void tokoButtonAction(){
        GameManager gm = GameManager.getInstance();
        if(gm.isBearAttackInProgress()){
            System.out.println("Bear is attacking, cannot end turn");
            return;
        }
        System.out.println("Toko button clicked");
        ViewFactory.ShowTokoScreen(GuldenPlayer1,GuldenPlayer2);
        refreshGulden();

    }

    public void refreshGulden(){
        GuldenPlayer1.setText(String.valueOf(GameManager.getInstance().getPlayerOne().getGulden()));
        GuldenPlayer2.setText(String.valueOf(GameManager.getInstance().getPlayerTwo().getGulden()));

    }

    @FXML
    protected void ladangKuButtonAction(){
        GameManager gm = GameManager.getInstance();
        if(gm.isBearAttackInProgress()){
            System.out.println("Bear is attacking, cannot end turn");
            return;
        }
        if (ladangShow == playerTurn) return;
        GameManager.getInstance().setLadang(GameManager.getInstance().getCurrentPlayer().getLadang());
        GameManager.getInstance().setViewLawan();

        ladangShow = playerTurn;


        unclickButtons();
        setButtonClicked("ladangKuButton");
    }

    @FXML
    protected void ladangLawanButtonAction(){
        GameManager gm = GameManager.getInstance();
        if(gm.isBearAttackInProgress()){
            System.out.println("Bear is attacking, cannot end turn");
            return;
        }
        if (ladangShow != playerTurn) return;

        GameManager.getInstance().inverseLadang();
        GameManager.getInstance().setViewLawan();
        ladangShow = playerTurn == 1 ? 2 : 1;
//        LadangSave.put(ladangShow, draggables.getLadang().saveCards());
//        draggables.getLadang().clearCards();
//        draggables.loadLadang(LadangSave.get(ladangShow));




        unclickButtons();
        setButtonClicked("ladangLawanButton");
    }

    @FXML
    protected void saveButtonAction(){
        System.out.println("Save button clicked");
        ObjectMapper objectMapper = new ObjectMapper();

        // Write the object to a file
        try {
            objectMapper.writeValue(new File("C:\\Users\\Nicholas R. Sihite\\OneDrive - Institut Teknologi Bandung\\Desktop\\New folder\\save.json"), GameManager.getInstance().getPlayerOne());
            String jsonInString = objectMapper.writeValueAsString(new KartuProduk("LABU"));
            System.out.println(jsonInString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ViewFactory.ShowSaveScreen();
    }

    @FXML
    protected void loadButtonAction(){
        System.out.println("Load button clicked");
        ViewFactory.ShowLoadScreen();
    }

    @FXML
    protected void pluginButtonAction(){
        System.out.println("Plugin button clicked");
        ViewFactory.ShowPlugginScreen();
    }

    protected void unclickButtons() {
        for (Button button : buttonList.values()) {
            button.setStyle("-fx-background-color: #eee6e6;");
        }
    }

    protected void setButtonClicked(String buttonName) {
        unclickButtons();
        buttonList.get(buttonName).setStyle("-fx-background-color: #50C878;");
    }
}

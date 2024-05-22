package tb2.p1g.harvestmooncombat.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import tb2.p1g.harvestmooncombat.Components.ActiveDeck;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Components.Card;
import tb2.p1g.harvestmooncombat.Components.AngryBear;

import java.util.ArrayList;
import java.util.List;


public class TokoScreenController {

    @FXML GridPane activeDeckGrid, tokoRow1, tokoRow2, tokoRow3, sellButtons;
    @FXML AnchorPane root;

    ActiveDeck activeDeck;
    TokoBuyUI tokoBuyUI;
    TokoSellButtonsUI tokoSellButtonsUI;

    public void initialize(){
         GameManager gameManager = GameManager.getInstance();

         // setup deck ui
         this.activeDeck = new ActiveDeck(activeDeckGrid);
         this.activeDeck.refreshCards();

         // setup toko entry ui
         this.tokoBuyUI = new TokoBuyUI();
         this.tokoBuyUI.addEntries(tokoRow1, new String[]{"LABU", "JAGUNG", "STROBERI"});
         this.tokoBuyUI.addEntries(tokoRow2, new String[]{"SUSU", "TELUR", "SIRIP_HIU"});
         this.tokoBuyUI.addEntries(tokoRow3, new String[]{"DAGING_KUDA", "DAGING_BERUANG", "DAGING_DOMBA"});

         // setup sell buttons ui
         this.tokoSellButtonsUI = new TokoSellButtonsUI(sellButtons);

         // setup player gold
         // belum implementasi
//        AngryBear.addRandomBear(root);

    }

    @FXML
    public void keluarButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }
}

class TokoBuyEntryUI {
    private Pane pane;
    private Label harga;
    private Label label;
    private Button button;
    private int stock;

    public TokoBuyEntryUI(Pane pane, Label label, Button button, String cardName) {

        this.pane = pane;
        this.label = label;
        this.harga = (Label) this.pane.getChildren().getFirst();
        this.button = button;
        this.stock = 0; // set dengan value dari method logic
        this.harga.setText("120"); // set dengan value dari method logic

        this.label.setText("Stock: " + this.stock);
        this.setCard(cardName);
        button.setOnAction(e -> {

            // panggil method logic disini
            System.out.println("Beli: " + cardName);

            if (this.stock == 0) {
                System.out.println("Stock habis!");
            }
        });
    }

    public Pane getPane() {
        return pane;
    }

    public void setCard(String cardName) {
//        this.pane.getChildren().clear();
        this.pane.getChildren().add(new Card(cardName));
    }

    public void setLabel(String text) {
        this.label.setText(text);
    }
}

class TokoBuyUI {
    private List<TokoBuyEntryUI> entries;

    public TokoBuyUI(){
        this.entries = new ArrayList<>();
    }

    public void addEntries(GridPane gridPane, String[] cardNames) {

        ObservableList<Node> children = gridPane.getChildren();

        for (int i = 0; i < 3; i++) {
            Pane pane = (Pane) children.get(i);
            Label label = (Label) children.get(i + 3);
            Button button = (Button) children.get(i + 6);

            this.entries.add(new TokoBuyEntryUI(pane, label, button, cardNames[i]));
        }
    }
}

class TokoSellButtonsUI {
    private List<Button> buttons;

    public TokoSellButtonsUI(GridPane buttons){
        this.buttons = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            Button button = (Button) buttons.getChildren().get(i);
            int finalI = i;
            button.setOnAction(e -> {
                // panggil method logic disini
                System.out.println("Jual: d" + finalI);
            });
        }
    }
}

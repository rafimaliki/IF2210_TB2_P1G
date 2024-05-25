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
import tb2.p1g.harvestmooncombat.Models.Config;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Components.Card;
import tb2.p1g.harvestmooncombat.Components.AngryBear;
import tb2.p1g.harvestmooncombat.Models.Toko;
import tb2.p1g.harvestmooncombat.Models.TokoEntry;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

import java.util.ArrayList;
import java.util.List;

public class TokoScreenController {

    @FXML Label money;
    @FXML GridPane activeDeckGrid, tokoRow1, tokoRow2, tokoRow3, sellButtons;
    @FXML AnchorPane root;

    ActiveDeck activeDeck;
    TokoBuyUI tokoBuyUI;
    TokoSellButtonsUI tokoSellButtonsUI;

    public void setMoney(int guldne) {
        money.setText(String.valueOf(guldne));
    }

    public ActiveDeck getActiveDeck() {
        return this.activeDeck;
    }

    public void initialize() {
        GameManager gameManager = GameManager.getInstance();
        setMoney(GameManager.getInstance().getCurrentPlayer().getGulden());
        // setup deck ui
        this.activeDeck = new ActiveDeck(activeDeckGrid);
        this.activeDeck.refreshCards();

        // setup toko entry ui
        this.tokoBuyUI = new TokoBuyUI(this);
        this.tokoBuyUI.addEntries(tokoRow1, new String[] { "LABU", "JAGUNG", "STROBERI" });
        this.tokoBuyUI.addEntries(tokoRow2, new String[] { "SUSU", "TELUR", "SIRIP_HIU" });
        this.tokoBuyUI.addEntries(tokoRow3, new String[] { "DAGING_KUDA", "DAGING_BERUANG", "DAGING_DOMBA" });
        this.tokoBuyUI.refreshTokoUI();

        // setup sell buttons ui
        this.tokoSellButtonsUI = new TokoSellButtonsUI(sellButtons, activeDeck, tokoBuyUI);

        // setup player gold
        setMoney(GameManager.getInstance().getCurrentPlayer().getGulden());
    }

    @FXML
    public void keluarButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();

        ((Label) ViewFactory.Root.lookup("#guldenPlayer1")).setText(String.valueOf(GameManager.getInstance().getPlayerOne().getGulden()));
        ((Label) ViewFactory.Root.lookup("#guldenPlayer2")).setText(String.valueOf(GameManager.getInstance().getPlayerTwo().getGulden()));

        stage.close();
    }
}

class TokoBuyEntryUI {
    private Pane pane;
    private Label harga;
    private Label label;
    private Button button;
    private String nama;
    private int stock;

    public TokoBuyEntryUI(Pane pane, Label label, Button button, String cardName, TokoScreenController controller) {

        this.pane = pane;
        this.label = label;
        this.harga = (Label) this.pane.getChildren().getFirst();
        this.button = button;
        this.nama = cardName;
        this.stock = 0; // set dengan value dari method logic
        this.harga.setText(String.valueOf(Config.mapProduk.get(cardName).getFirst()));

        this.label.setText("Stock: " + this.stock);
        this.setCard(cardName);
        button.setOnAction(e -> {

            // panggil method logic disini

            try {
                System.out.println("Beli: " + cardName);
                GameManager.getInstance().BeliGM(cardName);
                controller.getActiveDeck().refreshCards();
                controller.setMoney(GameManager.getInstance().getCurrentPlayer().getGulden());
                if (this.stock == 1) {
                    updateStock(0);
                }
                refreshEntry();

            } catch (Exception er) {
                System.out.println(er.getMessage());
            }

            if (this.stock == 0) {
                System.out.println("Stock habis!");
            }
        });
    }

    public Pane getPane() {
        return pane;
    }

    public void setCard(String cardName) {
        // this.pane.getChildren().clear();
        this.pane.getChildren().add(new Card(cardName));
    }

    public void updateStock(int stock) {
        this.stock = stock;
        this.label.setText("Stock: " + this.stock);
    }

    public void refreshEntry() {
        TokoEntry sametk = null;
        for (TokoEntry tk : Toko.produkToko) {
            if (tk != null) {
                if (tk.getKartu().getNama().equals(this.nama)) {
                    sametk = tk;
                }

            }
        }
        if (sametk != null) {
            updateStock(sametk.getKuantitas());

        }
    }
    
    public void setLabel(String text) {
        this.label.setText(text);
    }

}

class TokoBuyUI {
    private List<TokoBuyEntryUI> entries;
    private TokoScreenController controller;

    public TokoBuyUI(TokoScreenController control) {
        this.entries = new ArrayList<>();
        this.controller = control;
    }

    public void refreshTokoUI() {
        for (TokoBuyEntryUI tk : entries) {
            tk.refreshEntry();
        }
        controller.setMoney(GameManager.getInstance().getCurrentPlayer().getGulden());
    }

    public void addEntries(GridPane gridPane, String[] cardNames) {

        ObservableList<Node> children = gridPane.getChildren();

        for (int i = 0; i < 3; i++) {
            Pane pane = (Pane) children.get(i);
            Label label = (Label) children.get(i + 3);
            Button button = (Button) children.get(i + 6);
            this.entries.add(new TokoBuyEntryUI(pane, label, button, cardNames[i], controller));

        }
    }
}

class TokoSellButtonsUI {
    private List<Button> buttons;
    private ActiveDeck activeDeck;

    public TokoSellButtonsUI(GridPane buttons, ActiveDeck activeDeck, TokoBuyUI tokoUI) {
        this.buttons = new ArrayList<>();
        this.activeDeck = activeDeck;

        for (int i = 0; i < 6; i++) {
            Button button = (Button) buttons.getChildren().get(i);
            int finalI = i;
            button.setOnAction(e -> {

                String idxjual = 'd' + String.valueOf(finalI);
                try {
                    System.out.println(idxjual);
                    GameManager.getInstance().JualGM(idxjual);
                    // Kalo berhasil refresh deckAktif
                    this.activeDeck.refreshCards();
                    Toko.displayToko();
                    tokoUI.refreshTokoUI();
                } catch (Exception er) {
                    System.out.println(er.getMessage());
                }

            });
        }
    }
}

package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Components.Card;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Kartu;
import java.util.List;
import java.util.ArrayList;


public class CardDetailController {

    @FXML Label namaKartu, deskripsi, itemAktif;
    @FXML Pane cardSlot;

    @FXML
    public void initialize() {
        System.out.println("Card Detail Screen Loaded");
    }

    @FXML
    public void closeButton(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    public void setCard(String slotIdx) {

        int row, col;
        boolean deck;

        if (slotIdx.charAt(0) == 'd') {
            row = 0;
            col = Character.getNumericValue(slotIdx.charAt(1));
            deck = true;

        } else {
            int numberPart = Integer.parseInt(slotIdx.substring(1));
            row = numberPart / 5;
            col = numberPart % 5;
            deck = false;

            System.out.println("Row: " + row + " Col: " + col);
        }

        GameManager gameManager = GameManager.getInstance();

        Kartu kartu;

        if (deck) {
            kartu = gameManager.getCurrentPlayer().getDeckAktif().getKartu(col);
        } else {
            kartu = gameManager.getCurrentLadang().getKartu(row, col);
        }

        List<String> deskripsiKartu = kartu.getInformasi();

        this.namaKartu.setText(deskripsiKartu.get(0));
        this.deskripsi.setText(deskripsiKartu.get(1));
        this.itemAktif.setText(deskripsiKartu.get(2));

        this.cardSlot.getChildren().add(new Card(deskripsiKartu.get(0)));
    }

    public void panenButtonAction(){
        System.out.println("Panen Button Clicked");
    }
}

package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import tb2.p1g.harvestmooncombat.Components.Card;


public class CardDetailController {

    @FXML Label namaKartu;
    Card kartu;

    @FXML
    public void initialize() {
        System.out.println("Card Detail Screen Loaded");
    }

    @FXML
    public void closeButton(ActionEvent event){
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    public void setCard(Card card){
        kartu = card;
        namaKartu.setText(kartu.getCardName());
    }
}

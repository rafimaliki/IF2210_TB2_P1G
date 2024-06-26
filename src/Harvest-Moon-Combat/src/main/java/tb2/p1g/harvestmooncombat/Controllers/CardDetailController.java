package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Components.Card;
import tb2.p1g.harvestmooncombat.Models.Config;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Kartu;
import java.util.List;
import java.util.Objects;


public class CardDetailController {

    @FXML Label namaKartu, deskripsi, itemAktif;
    @FXML Pane cardSlot;
    @FXML Button actionButton;

    private Stage thisStage;

    private String action;

    private int row;
    private int col;
    private  String slotIdx;


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

        boolean deck;
        this.slotIdx = slotIdx;

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
            actionButton.setVisible(false);
        } else {
            kartu = gameManager.getCurrentLadang().getKartu(row, col);
            actionButton.setVisible(true);
        }

        List<String> deskripsiKartu = kartu.getInformasi();

        if (Config.mapProduk.containsKey(kartu.getNama())){
            actionButton.setText("AMBIL");
            action = "AMBIL";
        } else {
            actionButton.setText("PANEN");
            action = "PANEN";
        }

        this.namaKartu.setText(deskripsiKartu.get(0));
        this.deskripsi.setText(deskripsiKartu.get(1));
        this.itemAktif.setText(deskripsiKartu.get(2));

        this.cardSlot.getChildren().add(new Card(deskripsiKartu.get(0)));
    }

    public void actionButtonAction() {

        if (Objects.equals(action, "PANEN")){
            System.out.println("Klik Panen: " + row + " " + col);
            try {
                GameManager.getInstance().PanenGM(this.slotIdx);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            thisStage.close();
        } else {
            System.out.println("Klik Ambil: "+ row + " " + col);
            try{
                GameManager.getInstance().AmbilGM(this.slotIdx);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            thisStage.close();
        }
    }

    public void setStage(Stage stage) {
        this.thisStage = stage;
    }
}

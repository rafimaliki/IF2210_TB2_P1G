package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;


import java.util.List;
import java.util.ArrayList;

import tb2.p1g.harvestmooncombat.Components.ActiveDeck;
import tb2.p1g.harvestmooncombat.Components.Card;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Kartu;

public class ShuffleScreenController {

    @FXML AnchorPane container;
    @FXML GridPane cardShuffleGrid;

    List<Pane> cardShuffleList = new ArrayList<>();
    List<Boolean> isClicked = new ArrayList<>();
    List<Kartu> randomKartu = new ArrayList<>();
    Integer countClicked = 0;
    Integer maxClicked = 0;

    ActiveDeck activeDeck;

    public void initialize() {
        System.out.println("Shuffle screen loaded");
//        container.setStyle("-fx-background-color: rgba(128, 128, 128, 0.2);");

        for (Node pane : cardShuffleGrid.getChildren()) {
            if (pane instanceof Pane) {
                cardShuffleList.add((Pane) pane);
                pane.setId(""+(cardShuffleList.size()-1));
                isClicked.add(false);
                pane.setOnMouseClicked(event -> {
                    maxClicked = 6-activeDeck.countCard();
                    if (maxClicked > 4) maxClicked = 4;
                    if (isClicked.get(Integer.parseInt(pane.getId()))) {
                        pane.setStyle("-fx-background-color: #FFFFFF;");
                        isClicked.set(Integer.parseInt(pane.getId()), false);
                        countClicked--;
                        System.out.print("Membatalkan: ");
                        System.out.println(((Card) ((Pane) pane).getChildren().getFirst()).getCardName());;
                    } else {
                        if (countClicked == maxClicked) return;
                        pane.setStyle("-fx-background-color: #50C878;");
                        isClicked.set(Integer.parseInt(pane.getId()), true);
                        countClicked++;
                        System.out.print("Memilih: ");
                        System.out.println(((Card) ((Pane) pane).getChildren().getFirst()).getCardName());;
                    }
                });
            }

        }
        handleShuffleButtonAction();

        countClicked = 0;
    }

    @FXML
    private void handleConfirmationButtonAction(ActionEvent event) {
//        System.out.println(activeDeck);
        GameManager gameManager = GameManager.getInstance();
        List<Kartu> returnKartu = new ArrayList<>();

        maxClicked = 6-activeDeck.countCard();
        if (maxClicked > 4) maxClicked = 4;
        if (countClicked != maxClicked) return;

        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();

        // add selected card to activeDeck
        for (int i = 0; i < isClicked.size(); i++) {
            if (isClicked.get(i)) {
                gameManager.getCurrentPlayer().getDeckAktif().tambahKartu(this.randomKartu.get(i));
            } else {
                returnKartu.add(randomKartu.get(i));
            }
        }
        gameManager.getCurrentPlayer().getDeckNonAktif().kembalikanKartu(returnKartu, returnKartu.size());
        gameManager.initBearAttack();
    }

    @FXML
    private void handleShuffleButtonAction() {
//
        GameManager gameManager = GameManager.getInstance();
        System.out.println("panggil");

        gameManager.getCurrentPlayer().getDeckNonAktif().kembalikanKartu(this.randomKartu, this.randomKartu.size());
        System.out.println("sisa kartu: " + gameManager.getCurrentPlayer().getDeckNonAktif().getKartuSisa());
        this.randomKartu = gameManager.getCurrentPlayer().getDeckNonAktif().ambil4Kartu();

        int counter = 0;
        for (Pane pane : cardShuffleList) {
            pane.getChildren().clear();
            pane.setStyle("-fx-background-color: #FFFFFF;");
            pane.getChildren().add(new Card(randomKartu.get(counter).getNama()));
            counter++;
        }

        for (int i = 0; i < 4; i++){
            isClicked.set(i, false);

        }
        countClicked = 0;
    }

    public void setActiveDeck(ActiveDeck activeDeck) {
        this.activeDeck = activeDeck;
    }
}

package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;


import java.util.List;
import java.util.ArrayList;

import tb2.p1g.harvestmooncombat.Components.ActiveDeck;
import tb2.p1g.harvestmooncombat.Components.Card;

public class ShuffleScreenController {

    @FXML GridPane cardShuffleGrid;

    List<Pane> cardShuffleList = new ArrayList<>();
    List<Boolean> isClicked = new ArrayList<>();
    Integer countClicked = 0;
    Integer maxClicked = 0;

    ActiveDeck activeDeck;

    public void initialize() {
        System.out.println("Shuffle screen loaded");

        for (Node pane : cardShuffleGrid.getChildren()) {
            if (pane instanceof Pane) {
                cardShuffleList.add((Pane) pane);
                pane.setId(""+(cardShuffleList.size()-1));
                isClicked.add(false);
                pane.setOnMouseClicked(event -> {
                    maxClicked = 6-activeDeck.countCard();
                    if (maxClicked > 2) maxClicked = 2;
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

            handleShuffleButtonAction();
        }

        countClicked = 0;
    }

    @FXML
    private void handleConfirmationButtonAction(ActionEvent event) {
//        System.out.println(activeDeck);

        maxClicked = 6-activeDeck.countCard();
        if (maxClicked > 2) maxClicked = 2;
        if (countClicked != maxClicked) return;

        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();

        // add selected card to activeDeck
        for (int i = 0; i < isClicked.size(); i++) {
            if (isClicked.get(i)) {
                activeDeck.addCard((Card)cardShuffleList.get(i).getChildren().get(0));

            }
        }
    }

    @FXML
    private void handleShuffleButtonAction() {
//        System.out.println("Shuffle button clicked");

        for (int i = 0; i < isClicked.size(); i++){
           isClicked.set(i, false);
        }

        for (Pane pane : cardShuffleList) {
            pane.getChildren().clear();
            pane.getChildren().add(new Card());
            pane.setStyle("-fx-background-color: #FFFFFF;");
        }

        countClicked = 0;
    }

    public void setActiveDeck(ActiveDeck activeDeck) {
        this.activeDeck = activeDeck;
    }
}

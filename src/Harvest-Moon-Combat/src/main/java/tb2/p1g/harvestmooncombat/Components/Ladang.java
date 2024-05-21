package tb2.p1g.harvestmooncombat.Components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.Node;
import tb2.p1g.harvestmooncombat.Components.Card;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Models.Ladang_Logic;
import tb2.p1g.harvestmooncombat.Models.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Ladang {

    private final List<Pane> cards;
    private static final int numRows = 4;
    private static final int numCols = 5;
    private static final int numCards = Ladang.numRows * numCols;
    private Ladang_Logic ladang;



    public Ladang(GridPane ladangGrid) {
        cards = new ArrayList<>(numCards);
        ladang = new Ladang_Logic();
        for (int idx = 0; idx < numCards; idx++) {
            Pane pane = (Pane) ladangGrid.getChildren().get(idx);
            pane.setId("l" + idx);
//            pane.getStyleClass().addAll("card-slot");
            cards.add(pane);
        }
    }
    public void refreshLadang() {
        for (int i = 0; i < numCards; i++) {
            Pane pane = cards.get(i);
            int row = i / numCols;
            int col = i % numCols;
            if (!pane.getChildren().isEmpty() && pane.getChildren().get(0) instanceof Card) {
                Card card = (Card) pane.getChildren().get(0);
                ladang.setLadang(Utility.getKartuObject(card.getCardName()), row, col);
            } else {
                ladang.setLadang(null, row, col);
            }
        }
    }

    public List<Pane> getCards() {
        return cards;
    }

    public void addCard(String key, Card card) {
        cards.get(Integer.parseInt(key.substring(1))).getChildren().add(card);
        String card_name = card.getCardName();
        ladang.addKartu(Utility.getKartuObject(card_name), Integer.parseInt(key.substring(1)) / numCols, Integer.parseInt(key.substring(1)) % numCols);
        ladang.displayLadang();
    }
    public  Ladang_Logic getLadangLogic(){
        return ladang;
    }

    public Pane getCard(String key){
        return cards.get(Integer.parseInt(key.substring(1)));
    }

    public void clearCards() {
        cards.forEach(pane -> {
            pane.getChildren().clear();
        });
    }

    public Map<String, Card> saveCards() {
        Map<String, Card> savedCards = new HashMap<>();
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getChildren().isEmpty() && cards.get(i).getChildren().get(0) instanceof Pane){
                Card pane = (Card) cards.get(i).getChildren().get(0);
                savedCards.put("l" + i, pane);
            }
        }
        return savedCards;
    }
}

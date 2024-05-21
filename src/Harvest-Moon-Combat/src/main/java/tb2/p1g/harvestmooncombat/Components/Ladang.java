package tb2.p1g.harvestmooncombat.Components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.Node;
import tb2.p1g.harvestmooncombat.Components.Card;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Ladang {

    private final List<Pane> cards;
    private static final int numRows = 4;
    private static final int numCols = 5;
    private static final int numCards = Ladang.numRows * numCols;

    public Ladang(GridPane ladangGrid) {
        cards = new ArrayList<>(numCards);

        for (int idx = 0; idx < numCards; idx++) {
            Pane pane = (Pane) ladangGrid.getChildren().get(idx);
            pane.setId("l" + idx);
//            pane.getStyleClass().addAll("card-slot");
            cards.add(pane);
        }
    }

    public List<Pane> getCards() {
        return cards;
    }

    public void addCard(String key, Pane card) {
        cards.get(Integer.parseInt(key.substring(1))).getChildren().add(card);
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

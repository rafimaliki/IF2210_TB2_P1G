package tb2.p1g.harvestmooncombat.Components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tb2.p1g.harvestmooncombat.Components.Card;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Models.DeckAktif;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Utility;
import  tb2.p1g.harvestmooncombat.Models.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveDeck {

    private final List<Pane> cards;
    private static final int numCards = 6;

    public ActiveDeck(GridPane activeDeckGrid) {
        cards = new ArrayList<>(numCards);
        for (int i = 0; i < numCards; i++) {
            Pane pane = (Pane) activeDeckGrid.getChildren().get(i);
            pane.setId("d" + i);
//            pane.getStyleClass().addAll("card-slot");
            cards.add(pane);
        }
    }
    public List<Pane> getCards() {
        return cards;
    }

    public Integer countCard(){
        Integer count = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getChildren().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public Pane getCard(String key){
        return cards.get(Integer.parseInt(key.substring(1)));
    }

    public void addCard(String key, Card card,Integer i) {
        cards.get(Integer.parseInt(key.substring(1))).getChildren().add(card);

    }



    public void addCard(Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getChildren().isEmpty()) {
                cards.get(i).getChildren().add(card);
                GameManager gm = GameManager.getInstance();
                gm.getCurrentPlayer().getDeckAktif().setKartu(Utility.getKartuObject(card.getCardName()),i);
                break;
            }
        }
    }
    public void refreshCards() {

        clearCards();
        DeckAktif deck_aktif = GameManager.getInstance().getDeckAktif();
        for (int i = 0; i < cards.size(); i++) {
            if (deck_aktif.getKartu(i) != null) {
                Card card = new Card(deck_aktif.getKartu(i).getNama());
                cards.get(i).getChildren().add(card);

            }
        }
    }

    public Map<String, Card> saveCards() {
        Map<String, Card> savedCards = new HashMap<>();
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getChildren().isEmpty() && cards.get(i).getChildren().get(0) instanceof Pane){
                Card pane =  (Card)cards.get(i).getChildren().get(0);
                savedCards.put("d" + i, pane);
            }
        }
        return savedCards;
    }
    public Card getCardFromPane(Pane pane) {
        if (!pane.getChildren().isEmpty() && pane.getChildren().get(0) instanceof Card) {
            return (Card) pane.getChildren().get(0);
        }
        return null; // Or handle it appropriately if no Card is found
    }

    public void clearCards() {
        cards.forEach(pane -> {
            pane.getChildren().clear();
        });
    }

    public void shuffle() {System.out.println("Shuffling deck");
        clearCards();
        cards.forEach(pane -> {
            pane.getChildren().add(new Card());
        });
    }
}

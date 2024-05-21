package tb2.p1g.harvestmooncombat.Components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tb2.p1g.harvestmooncombat.Components.Card;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Models.DeckAktif;
import tb2.p1g.harvestmooncombat.Models.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveDeck {

    private final List<Pane> cards;
    private static final int numCards = 6;
    private DeckAktif deckAktif;

    public ActiveDeck(GridPane activeDeckGrid) {
        this.deckAktif = new DeckAktif();
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
        String card_name = card.getCardName();
        deckAktif.setKartu(Utility.getKartuObject(card_name),i);

    }


    public void addCard(Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getChildren().isEmpty()) {
                cards.get(i).getChildren().add(card);
                String nama_kartu = card.getCardName();
                deckAktif.setKartu(Utility.getKartuObject(nama_kartu),i);
                break;
            }
        }
        deckAktif.displayInfoDeck();
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

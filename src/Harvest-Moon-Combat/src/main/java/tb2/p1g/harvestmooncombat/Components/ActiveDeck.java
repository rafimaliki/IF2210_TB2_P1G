package tb2.p1g.harvestmooncombat.Components;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Models.DeckAktif;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Utility;

import java.util.HashMap;
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


    public Pane getCard(String key){
        return cards.get(Integer.parseInt(key.substring(1)));
    }

    public void refreshCards() {

        clearCards();
        DeckAktif deck_aktif = GameManager.getInstance().getDeckAktif();
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(deck_aktif.getKartu(i));
            System.out.println(deck_aktif);
            if (deck_aktif.getKartu(i) != null) {

                Card card = new Card(deck_aktif.getKartu(i).getNama());
                cards.get(i).getChildren().add(card);

            }
        }
    }

    public void clearCards() {
        cards.forEach(pane -> {
            pane.getChildren().clear();
        });
    }

}

package tb2.p1g.harvestmooncombat.Components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.Node;
import tb2.p1g.harvestmooncombat.Components.Card;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.LadangLogic;
import tb2.p1g.harvestmooncombat.Models.Utility;

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


    public Pane getCard(String key){
        return cards.get(Integer.parseInt(key.substring(1)));
    }

    public void clearCards() {
        cards.forEach(pane -> {
            pane.getChildren().clear();
        });
    }

    public void refreshLadang(){
        clearCards();
        LadangLogic ladang = GameManager.getInstance().getCurrentLadang();
        for (int i = 0; i <numCards ; i++) {
            int row = i / numCols;
            int col = i % numCols;
            if(ladang.getKartu(row,col) != null){
                Card card = new Card(ladang.getKartu(row,col).getNama());
                cards.get(i).getChildren().add(card);
            }
        }

    }
}

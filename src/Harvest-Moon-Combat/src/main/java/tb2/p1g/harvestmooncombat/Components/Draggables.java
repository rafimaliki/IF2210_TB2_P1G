package tb2.p1g.harvestmooncombat.Components;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Player;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

import javax.swing.text.View;
import java.util.Map;


public class Draggables {

    private static ActiveDeck activeDeck;
    private static Ladang ladang;

    public Draggables(GridPane activeDeckGrid, GridPane ladangGrid){
        activeDeck = new ActiveDeck(activeDeckGrid);
        for (Pane pane : activeDeck.getCards()) {Draggables.setupDragAndDrop(pane);}

        ladang = new Ladang(ladangGrid);
        for (Pane pane : ladang.getCards()) {Draggables.setupDragAndDrop(pane);}
    }

    public ActiveDeck getActiveDeck() {
        return activeDeck;
    }

    public Ladang getLadang() {
        return ladang;
    }

    public void loadActiveDeck(Map<String, Card> playerActiveDeck) {
        int i = 0;
        System.out.println("Load Active deck");
        for (Map.Entry<String, Card> entry : playerActiveDeck.entrySet()) {
            activeDeck.addCard(entry.getKey(), entry.getValue());
            i++;
        }
    }

    public void loadLadang(Map<String, Card> playerLadang) {
        System.out.println("Load Ladang");
        for (Map.Entry<String, Card> entry : playerLadang.entrySet()) {
            ladang.addCard(entry.getKey(), entry.getValue());
        }
    }

    private static void setupDragAndDrop(Pane pane) {
        pane.setOnDragDetected(event -> {
            if (!pane.getChildren().isEmpty()) {

//              System.out.println("Source: " + pane.getId());
                Dragboard db = pane.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();

                content.putString(pane.getId());
                db.setContent(content);


//                Image snapshot = pane.getChildren().getFirst().snapshot(null, null);

                SnapshotParameters snapshotParams = new SnapshotParameters();
                snapshotParams.setFill(Color.TRANSPARENT);
                WritableImage snapshot = pane.getChildren().getFirst().snapshot(snapshotParams, null);

                db.setDragView(snapshot, pane.getWidth() / 2, pane.getHeight() / 2);
            }
            event.consume();
        });

        pane.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.ANY);
            event.consume();
        });

        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();

            if (db.hasString()) {
                String id = db.getString();
                Node node;

                if (id.charAt(0) == 'd') {
                    node = activeDeck.getCard(id);
                } else {

                    node = ladang.getCard(id);
                }

                if (node instanceof Pane sourcePane) {

                    if (pane.getChildren().isEmpty()){
                        if (sourcePane.getId().charAt(0) == 'l' && pane.getId().charAt(0) == 'd') {
                        }
                        else {
                        pane.getChildren().add(sourcePane.getChildren().getFirst());
                        }
                    }
                    else if (sourcePane.getId().charAt(0) == 'd' && pane.getId().charAt(0) == 'd' && pane.getChildren().getFirst() != sourcePane.getChildren().getFirst()){
                        System.out.println("Swapping");
                        Pane temp = new Pane();
                        temp.getChildren().add(sourcePane.getChildren().getFirst());
                        sourcePane.getChildren().clear();
                        sourcePane.getChildren().add(pane.getChildren().getFirst());
                        pane.getChildren().clear();
                        pane.getChildren().add(temp.getChildren().getFirst());
                    }
                    else if (pane.getChildren().getFirst() != sourcePane.getChildren().getFirst()){
                        if (sourcePane.getId().charAt(0) == 'l' && pane.getId().charAt(0) == 'l') {
                        }
                        else {
                            sourcePane.getChildren().clear();
                        }
                    }

//                    if (pane.getChildren().isEmpty()){
//                        if (sourcePane.getId().charAt(0) == 'l' && pane.getId().charAt(0) == 'd') {
//                        }
//                        else {
//                        pane.getChildren().add(sourcePane.getChildren().getFirst());
//                        }
//                    }
//                    else if (sourcePane.getId().charAt(0) == 'd' && pane.getId().charAt(0) == 'd' && pane.getChildren().getFirst() != sourcePane.getChildren().getFirst()){
//                        System.out.println("Swapping");
//                        Pane temp = new Pane();
//                        temp.getChildren().add(sourcePane.getChildren().getFirst());
//                        sourcePane.getChildren().clear();
//                        sourcePane.getChildren().add(pane.getChildren().getFirst());
//                        pane.getChildren().clear();
//                        pane.getChildren().add(temp.getChildren().getFirst());
//                    }
//                    else if (pane.getChildren().getFirst() != sourcePane.getChildren().getFirst()){
//                        if (sourcePane.getId().charAt(0) == 'l' && pane.getId().charAt(0) == 'l') {
//                        }
//                        else {
//                            sourcePane.getChildren().clear();
//                        }
//                    }
                    System.out.println("Source: " + sourcePane.getId());
                    System.out.println("Target: " + pane.getId());
                    try{
                        GameManager.getInstance().getCurrentPlayer().moveKartu(sourcePane.getId(),pane.getId());
                        activeDeck.refreshCards();
                        ladang.refreshLadang();
                    }catch (Exception e){
                        System.out.println("Error! " + e.getMessage() );

                    }

                }
            }

            event.setDropCompleted(true);
            event.consume();

        });

        pane.setOnMouseClicked(event -> {
            if (!pane.getChildren().isEmpty()) {
                System.out.println("Clicked: " + pane.getId());
                ViewFactory.ShowCardDetail((Card) pane.getChildren().getFirst());
            }

        });
    }
}

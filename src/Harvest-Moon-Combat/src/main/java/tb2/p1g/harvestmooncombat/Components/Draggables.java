package tb2.p1g.harvestmooncombat.Components;

import javafx.application.Platform;
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
import tb2.p1g.harvestmooncombat.Models.Kartu;
import tb2.p1g.harvestmooncombat.Models.Player;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;
import  tb2.p1g.harvestmooncombat.Exceptions.InvalidMoveExceptions;

import javax.swing.text.View;
import java.util.Map;


public class Draggables implements  Runnable {

    private static ActiveDeck activeDeck;
    private static Ladang ladang;

    public Draggables(GridPane activeDeckGrid, GridPane ladangGrid){
        activeDeck = new ActiveDeck(activeDeckGrid);
        for (Pane pane : activeDeck.getCards()) {Draggables.setupDragAndDrop(pane);}

        ladang = new Ladang(ladangGrid);
        for (Pane pane : ladang.getCards()) {Draggables.setupDragAndDrop(pane);}
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle the interrupted exception
                System.out.println("Thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Restore the interrupt status
                break;
            }
            Platform.runLater(this::refresh);
        }
    }

    public void refresh(){
        activeDeck.refreshCards();
        ladang.refreshLadang();
    }

    public ActiveDeck getActiveDeck() {
        return activeDeck;
    }

    public Ladang getLadang() {
        return ladang;
    }

    private static void setupDragAndDrop(Pane pane) {
        pane.setOnDragDetected(event -> {
            if (pane.getChildren().isEmpty()) {
               return;
            }

            if (pane.getId().charAt(0) == 'd' && GameManager.getInstance().isBearAttackInProgress()){
                System.out.println("Serangan beruang sedang berlangsung, tidak bisa melakukan aksi bebas");
                return;
            }

            Dragboard db = pane.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();

            content.putString(pane.getId());
            db.setContent(content);

            SnapshotParameters snapshotParams = new SnapshotParameters();
            snapshotParams.setFill(Color.TRANSPARENT);
            WritableImage snapshot = pane.getChildren().getFirst().snapshot(snapshotParams, null);

            db.setDragView(snapshot, pane.getWidth() / 2, pane.getHeight() / 2);

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

                    System.out.println("Source: " + sourcePane.getId());
                    System.out.println("Target: " + pane.getId());
                    try{
                        GameManager.getInstance().moveKartuGM(sourcePane.getId(),pane.getId());

                    } catch (InvalidMoveExceptions e){
                        System.out.println("Error! " + e.getMessage() );
                        Kartu invalid_kartu =  e.getKartu();
                        if(invalid_kartu != null){
                            GameManager.getInstance().undoKartuGM(invalid_kartu,sourcePane.getId());
                        }

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            event.setDropCompleted(true);
            event.consume();

        });

        pane.setOnMouseClicked(event -> {
            if (!pane.getChildren().isEmpty()) {
                System.out.println("Clicked: " + pane.getId());
                ViewFactory.ShowCardDetail(pane.getId());
            }
        });
    }
}

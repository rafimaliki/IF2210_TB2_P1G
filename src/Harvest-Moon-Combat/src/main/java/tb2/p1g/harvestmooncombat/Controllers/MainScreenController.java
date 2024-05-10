package tb2.p1g.harvestmooncombat.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MainScreenController {
    @FXML
    private GridPane activeDeck;

    @FXML
    private GridPane ladang;

    private List<Pane> activeDeckPanes;
    private List<List<Pane>> ladangPanes;
    private ImageView dragImageView;

    private final int numRows = 4;
    private final int numCols = 5;

    @FXML
    public void initialize() {

        dragImageView = new ImageView();
        dragImageView.setOpacity(1);

        activeDeckPanes = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            Pane pane = (Pane) activeDeck.getChildren().get(i);
            pane.setId("d" + i);
            setupDragAndDrop(pane);
            activeDeckPanes.add(pane);
        }

        ladangPanes = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            List<Pane> rowPanes = new ArrayList<>();
            for (int col = 0; col < numCols; col++) {
                Node node = ladang.getChildren().get(row * numCols + col);
                if (node instanceof Pane pane) {
                    pane.setId("l" + (row * numCols + col));
                    setupDragAndDrop(pane);
                    rowPanes.add(pane);
                }
            }
            ladangPanes.add(rowPanes);
        }
    }


    @FXML
    protected void shuffleDeck() {
        Card card;
        activeDeckPanes.forEach(pane -> {
            // Clear the pane
            pane.getChildren().clear();
        });
        for (Pane node : activeDeckPanes) {
            if (node != null) {
                card = new Card();
                node.getChildren().clear();
                node.getChildren().add(card);
            }
        }
    }

    private void setupDragAndDrop(Pane pane) {
        pane.setOnDragDetected(event -> {
            System.out.println("Drag detected on " + pane.getId());
            Dragboard db = pane.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(pane.getId());
            db.setContent(content);

            Image snapshot = pane.getChildren().getFirst().snapshot(null, null);
            db.setDragView(snapshot, pane.getWidth()/2, pane.getHeight()/2);

            event.consume();
        });

        pane.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.ANY);
            event.consume();
        });

        pane.setOnDragDropped(event -> {
            System.out.println("Drag dropped on " + pane.getId());
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                String id = db.getString();
                System.out.println("Id: " + id);

                if (id.charAt(0) == 'd') {
                    // Dragged from active deck
                    Node node_temp = activeDeckPanes.get(Card.GetIdxFromId(id));
                    if (node_temp instanceof Pane sourcePane) {
                        pane.getChildren().add(sourcePane.getChildren().getFirst());
                        sourcePane.getChildren().clear();
                        System.out.println("Drag dropped on " + pane.getId() + " from " + id);
                    }
                } else {
                    // Dragged from ladang
                    Node node_temp = ladangPanes.get(Card.GetIdxFromId(id) / numCols).get(Card.GetIdxFromId(id) % numCols);
                    if (node_temp instanceof Pane sourcePane) {
                        pane.getChildren().add(sourcePane.getChildren().getFirst());
                        sourcePane.getChildren().clear();
                        System.out.println("Drag dropped on " + pane.getId() + " from " + id);
                    }
                }
            }
            event.setDropCompleted(true);
            event.consume();
        });

        pane.setOnDragDone(event -> System.out.println("Drag done on " + pane.getId()));
    }
}

class Card extends Pane {

    private final List<String> cardName = new ArrayList<>(){{
        add("bear");
        add("chicken");
        add("cow");
        add("hiu_darat");
        add("horse");
        add("sheep");
    }};

    public Card() {
        setPrefSize(65, 90);
        Random random = new Random();
        String randomCardName = cardName.get(random.nextInt(cardName.size())) + "-card";
        getStyleClass().addAll("card", randomCardName);
    }

    public static int GetIdxFromId(String id) {
        return Integer.parseInt(id.substring(1));
    }
}

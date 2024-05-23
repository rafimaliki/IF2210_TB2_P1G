package tb2.p1g.harvestmooncombat.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class Card extends Pane {

    private final List<String> cardNames = new ArrayList<>(){{
        add("BERUANG");
        add("AYAM");
        add("SAPI");
        add("HIU_DARAT");
        add("KUDA");
        add("DOMBA");
        add("ACCELERATE");
        add("TRAP");
        add("DELAY");
        add("DESTROY");
        add("INSTANT_HARVEST");
        add("PROTECT");
        add("JAGUNG");
        add("DAGING_BERUANG");
        add("DAGING_DOMBA");
        add("DAGING_KUDA");
        add("LABU");
        add("SIRIP_HIU");
        add("STROBERI");
        add("SUSU");
        add("TELUR");
        add("BIJI_JAGUNG");
        add("BIJI_LABU");
        add("BIJI_STROBERI");
    }};

    private String cardName;

    public Card() {

        setPrefSize(70, 90);
        Random random = new Random();
        String randomCardName = cardNames.get(random.nextInt(cardNames.size()));
        this.cardName = randomCardName;
        String randomCardStyle = randomCardName + "-card";
        String title = randomCardName.replace("_", " ");

        getStyleClass().addAll("card", randomCardStyle);

        if (title.length() < 12){
            Label cardLabel = new Label(title);
            cardLabel.getStyleClass().add("card-label");

            StackPane stackPane = new StackPane(cardLabel);

            stackPane.setAlignment(Pos.BOTTOM_CENTER);
            stackPane.setPrefSize(70, 90);
            stackPane.setPadding(new Insets(0, 0, 7, 0));

            getChildren().add(stackPane);
        } else {
            Label cardLabel1 = new Label(title.split(" ")[0]);
            cardLabel1.getStyleClass().add("card-label");
            Label cardLabel2 = new Label(title.split(" ")[1]);
            cardLabel2.getStyleClass().add("card-label");

            VBox vbox = new VBox(cardLabel1, cardLabel2);

            vbox.setAlignment(Pos.BOTTOM_CENTER);
            vbox.setPrefSize(70, 90);

            getChildren().add(vbox);
        }
    }

    public Card(String cardName) {

        setPrefSize(70, 90);
        this.cardName = cardName;
        String randomCardStyle = cardName + "-card";
        String title = cardName.replace("_", " ");

        getStyleClass().addAll("card", randomCardStyle);

        if (title.length() < 12){
            Label cardLabel = new Label(title);
            cardLabel.getStyleClass().add("card-label");

            StackPane stackPane = new StackPane(cardLabel);

            stackPane.setAlignment(Pos.BOTTOM_CENTER);
            stackPane.setPrefSize(70, 90);
            stackPane.setPadding(new Insets(0, 0, 7, 0));

            getChildren().add(stackPane);
        } else {
            Label cardLabel1 = new Label(title.split(" ")[0]);
            cardLabel1.getStyleClass().add("card-label");

            Label cardLabel2 = new Label(title.split(" ")[1]);
            cardLabel2.getStyleClass().add("card-label");

            VBox vbox = new VBox(cardLabel1, cardLabel2);

            vbox.setAlignment(Pos.BOTTOM_CENTER);
            vbox.setPrefSize(70, 90);

            getChildren().add(vbox);
        }
    }

    public static int GetIdxFromId(String id) {
        return Integer.parseInt(id.substring(1));
    }

    public int keyToIndex(String key){
        return Integer.parseInt(key.substring(1));
    }

    public String getCardName() {
        return cardName;
    }
}

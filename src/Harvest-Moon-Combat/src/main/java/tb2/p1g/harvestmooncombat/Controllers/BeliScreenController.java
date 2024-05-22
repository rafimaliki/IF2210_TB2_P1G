package tb2.p1g.harvestmooncombat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BeliScreenController {

    @FXML GridPane shoppingGrid;

    public void initialize(){
        for(Node node : shoppingGrid.getChildren()){
            if(node instanceof Pane){
                System.out.println("hihi");
                Pane pane = (Pane) node;
                pane.getStyleClass().add("card-slot");
            }
        }
    }

    @FXML
    public void closeButton(ActionEvent event){
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }
}

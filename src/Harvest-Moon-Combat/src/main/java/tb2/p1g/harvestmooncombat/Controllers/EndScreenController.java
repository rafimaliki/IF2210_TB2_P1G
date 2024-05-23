package tb2.p1g.harvestmooncombat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Player;

public class EndScreenController
{
    @FXML Label winnerLabel;

    @FXML
    public void initialize(){
        GameManager gameManager = GameManager.getInstance();
        Player winner = gameManager.getWinner();

        if (winner != null){
            winnerLabel.setText(winner.getNama() + " (" + winner.getGulden() + " gulden)");
        } else {
            winnerLabel.setText("Permainan Seri");
        }
    }
    @FXML
    public void closeButton(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }
}

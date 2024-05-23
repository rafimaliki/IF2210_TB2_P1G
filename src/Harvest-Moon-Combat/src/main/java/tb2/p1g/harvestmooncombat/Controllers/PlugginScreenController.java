package tb2.p1g.harvestmooncombat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PlugginScreenController {
    @FXML
    public void closeButton(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }
}

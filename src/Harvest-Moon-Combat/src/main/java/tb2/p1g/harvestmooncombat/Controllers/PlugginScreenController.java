package tb2.p1g.harvestmooncombat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PlugginScreenController {

    @FXML Label message, inputPath;
    File jarFile;

    @FXML
    public void closeButton(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void inputButtonAction() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select JAR File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JAR Files", "*.jar")
        );
        jarFile = fileChooser.showOpenDialog(null);

        if (jarFile != null) {
            System.out.println("Selected file: " + jarFile.getAbsolutePath());
            inputPath.setText(jarFile.getName());
        } else {
            System.out.println("No file selected");
            inputPath.setText("");
        }
    }

    @FXML
    public void uploadButtonAction() {
        if (jarFile != null) {
            System.out.println("Uploading file: " + jarFile.getAbsolutePath());
            message.setText("File uploaded successfully!");
            message.setStyle("-fx-text-fill: green;");
        } else {
            System.out.println("No file selected");
            message.setText("Error");
            message.setStyle("-fx-text-fill: red;");
        }
    }


}

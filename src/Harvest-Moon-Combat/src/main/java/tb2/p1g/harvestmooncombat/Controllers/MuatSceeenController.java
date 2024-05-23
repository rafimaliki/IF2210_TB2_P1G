package tb2.p1g.harvestmooncombat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import tb2.p1g.harvestmooncombat.App;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MuatSceeenController {

    @FXML ComboBox comboBox;

    List<String> fileNames = new ArrayList<>();
    String selectedFormat;

    @FXML
    public void initialize(){
        System.out.println("Muat Screen Controller Initialized");

        // set comboBox items
        comboBox.getItems().addAll(".txt", ".json", ".xml", ".yaml");
    }

    @FXML
    public void inputButtonAction(){
        System.out.println("Input Button Clicked");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder");

        File selectedDirectory = directoryChooser.showDialog(App.PrimaryStage);

        File[] files = selectedDirectory.listFiles();
        fileNames.clear();

        for (File file : files) {
            if (file.isFile()) {
                fileNames.add(file.getAbsolutePath());
            }
        }
    }

    @FXML
    public void muatButtonAction() {
        System.out.println("Muat Button Clicked");

        if (comboBox.getValue() == null) {
            System.out.println("Format belum dipilih");
            return;
        }

        selectedFormat = comboBox.getValue().toString();
        System.out.println("Selected Format: " + selectedFormat);

        if (fileNames.isEmpty()) {
            System.out.println("Tidak ada file yang dipilih");
            return;
        }
        
        for (String fileName : fileNames) {
            System.out.println("File Name: " + fileName);
            if (!fileName.endsWith(selectedFormat)) {
                System.out.println("Format tidak sesuai terdeteksi");
                return;
            }
        }

    }

    @FXML
    public void closeButton(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

}

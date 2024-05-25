package tb2.p1g.harvestmooncombat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import tb2.p1g.harvestmooncombat.App;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Simpan;
import tb2.p1g.harvestmooncombat.PluginLoader.PluginLoader;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimpanScreenController {

    @FXML ComboBox comboBox;
    @FXML Button inputButton;

    List<String> fileNames = new ArrayList<>();
    String selectedFormat;
    String selectedFolder;

    @FXML
    public void initialize(){
        System.out.println("Simpan Screen Controller Initialized");
        comboBox.getItems().clear();
        comboBox.getItems().add(".txt");
        List<String> availableExtensions = PluginLoader.getPluginNameList();

        if(!availableExtensions.isEmpty()){
            // set comboBox items

            comboBox.getItems().addAll(availableExtensions);
        }
    }

    @FXML
    public void inputButtonAction(){
        System.out.println("Input Button Clicked");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder");

        File selectedDirectory = directoryChooser.showDialog(ViewFactory.PrimaryStage);
        if (selectedDirectory == null) {
            return;
        }

        inputButton.setText(selectedDirectory.getName());

        File[] files = selectedDirectory.listFiles();
        fileNames.clear();

        for (File file : files) {
            if (file.isFile()) {
                fileNames.add(file.getAbsolutePath());
            }
        }

        selectedFolder = selectedDirectory.getAbsolutePath();



    }

    @FXML
    public void simpanButtonAction() {
        System.out.println("Simpan Button Clicked");

        if (comboBox.getValue() == null) {
            System.out.println("Format belum dipilih");
            return;
        }

        selectedFormat = comboBox.getValue().toString();
        int idFormat = comboBox.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Format: " + selectedFormat + " Dengan id box " + idFormat);
        if(idFormat == 0) { //default txt
            Simpan simpan = new Simpan(selectedFolder);
            simpan.saveEntryPoint();
        }else{
            try{
                PluginLoader.saveWithPlugins(idFormat-1,selectedFolder);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }


        }

//        if (fileNames.isEmpty()) {
//            System.out.println("Tidak ada file yang dipilih");
//            return;
//        }
//
//        for (String fileName : fileNames) {
//            System.out.println("File Name: " + fileName);
//            if (!fileName.endsWith(selectedFormat)) {
//                System.out.println("Format tidak sesuai terdeteksi");
//                return;
//            }
//        }

        //load class simpan




    }

    @FXML
    public void closeButton(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

}

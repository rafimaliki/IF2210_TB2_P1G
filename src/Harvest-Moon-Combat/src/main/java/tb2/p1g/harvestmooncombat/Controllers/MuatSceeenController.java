package tb2.p1g.harvestmooncombat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import tb2.p1g.harvestmooncombat.App;
import tb2.p1g.harvestmooncombat.Models.GameManager;
import tb2.p1g.harvestmooncombat.Models.Muat;
import tb2.p1g.harvestmooncombat.Models.Player;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;
import tb2.p1g.harvestmooncombat.Models.Simpan;
import tb2.p1g.harvestmooncombat.PluginLoader.PluginLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MuatSceeenController {

    @FXML ComboBox comboBox;
    @FXML Button inputButton;

    List<String> fileNames = new ArrayList<>();
    String selectedFormat;

    @FXML
    public void initialize(){
        System.out.println("Muat Screen Controller Initialized");
        comboBox.getItems().clear();
        comboBox.getItems().add(".txt");
        List<String> availExtensions = PluginLoader.getPluginNameList();
        // set comboBox items

        if(!availExtensions.isEmpty()){
            comboBox.getItems().addAll(availExtensions);

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
        int idFormat = comboBox.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Format: " + selectedFormat + " Dengan id box " + idFormat);
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
        if(idFormat == 0) { //default txt
            Muat muat = new Muat(fileNames);
            muat.loadGame();
        }else{
            try{
                for(String file :fileNames){
                    PluginLoader.loadObjectWithPlugins(idFormat-1,file);
                    //update ladang dan deck active

                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            PluginLoader.loadObjects();
            Player p1 = GameManager.getInstance().getCurrentPlayer();
            GameManager.getInstance().setDeckAktif(GameManager.getInstance().getCurrentPlayer().getDeckAktif());
            GameManager.getInstance().setLadang(GameManager.getInstance().getCurrentPlayer().getLadang());
        }


    }

    @FXML
    public void closeButton(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

}

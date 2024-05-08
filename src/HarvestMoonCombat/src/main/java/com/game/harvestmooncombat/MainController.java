package com.game.harvestmooncombat;

import com.game.harvestmooncombat.testclass.math;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField num1;

    @FXML
    private TextField num2;

    @FXML
    private Label answer;

    @FXML
    protected void calculate() {
        answer.setText(String.valueOf(math.add(Integer.parseInt(num1.getText()), Integer.parseInt(num2.getText()))));
    }
}
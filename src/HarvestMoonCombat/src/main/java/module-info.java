module com.game.harvestmooncombat {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.game.harvestmooncombat to javafx.fxml;
    exports com.game.harvestmooncombat;
}
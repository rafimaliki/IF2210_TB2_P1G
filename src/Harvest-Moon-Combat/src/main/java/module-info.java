module tb2.p1g.harvestmooncombat {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens tb2.p1g.harvestmooncombat to javafx.fxml;
    exports tb2.p1g.harvestmooncombat;
    exports tb2.p1g.harvestmooncombat.Controllers;
    opens tb2.p1g.harvestmooncombat.Controllers to javafx.fxml;
}
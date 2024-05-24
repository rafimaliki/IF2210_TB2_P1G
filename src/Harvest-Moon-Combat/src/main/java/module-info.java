module tb2.p1g.harvestmooncombat {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires java.smartcardio;

    opens tb2.p1g.harvestmooncombat to javafx.fxml;
    exports tb2.p1g.harvestmooncombat;
    exports tb2.p1g.harvestmooncombat.Controllers;
    opens tb2.p1g.harvestmooncombat.Controllers to javafx.fxml;
    exports tb2.p1g.harvestmooncombat.Views;
    opens tb2.p1g.harvestmooncombat.Views to javafx.fxml;
    exports tb2.p1g.harvestmooncombat.Components;
    exports tb2.p1g.harvestmooncombat.Models;
    opens tb2.p1g.harvestmooncombat.Components to javafx.fxml;
}

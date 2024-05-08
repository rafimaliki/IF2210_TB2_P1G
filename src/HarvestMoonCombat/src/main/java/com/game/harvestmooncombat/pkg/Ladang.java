package com.game.harvestmooncombat.pkg;

import java.util.List;
import java.util.ArrayList;

public class Ladang {
    private List<List<Kartu>> ladang;

    public Ladang() {
        ladang = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            ladang.add(new ArrayList<Kartu>(5));
        }
    }
}

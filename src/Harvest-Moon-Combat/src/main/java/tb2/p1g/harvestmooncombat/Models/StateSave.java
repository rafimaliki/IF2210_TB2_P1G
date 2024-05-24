package tb2.p1g.harvestmooncombat.Models;

import java.util.ArrayList;
import java.util.List;

public class StateSave {
    private int turn;
    private List<TokoEntry> produktoko;

    public StateSave(){
        this.turn = 0;
        this.produktoko = new ArrayList<TokoEntry>();
    }

    public StateSave(int turn, List<TokoEntry> tokoproduk) {
        this.turn = turn;
        this.produktoko = tokoproduk;
    }

    public int getTurn() {
        return turn;
    }

    public List<TokoEntry> getToko() {
        return produktoko;
    }
}

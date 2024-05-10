package tb2.p1g.harvestmooncombat.Models;

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

    public List<List<Kartu>> getLadang() {
        return ladang;
    }

    // Menambahkan kartu ke ladang
    public void addKartu (Kartu kartu, int row, int col){
        ladang.get(row).set(col, kartu);
    }

    // Menghapus kartu dari ladang
    public void removeKartu (int row, int col){
        ladang.get(row).set(col, null);
    }
}

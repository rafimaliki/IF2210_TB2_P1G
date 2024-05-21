package tb2.p1g.harvestmooncombat.Models;

import java.util.List;
import java.util.ArrayList;

public class Ladang {
    private List<List<Kartu>> ladang;

    public Ladang() {
        ladang = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            List<Kartu> content = new ArrayList<>(5);
            for (int j = 0; j < 5; j++){
                content.add(null);
            }
            ladang.add(content);
        }
    }

    public List<List<Kartu>> getLadang() {
        return ladang;
    }

    public Kartu getKartu(int row, int col){
        return (this.ladang.get(row).get(col));
    }

    // Menambahkan kartu ke ladang
    public void addKartu (Kartu kartu, int row, int col){
        ladang.get(row).set(col, kartu);
    }

    // Menghapus kartu dari ladang
    public void removeKartu (int row, int col){
        ladang.get(row).set(col, null);
    }

//    public void moveKartuDiLadang(int idxInit, int idxDest){
//        int rowInit = idxInit / this.ladang.size();
//        int colInit = idxInit % this.ladang.size();
//        int rowDest = idxInit / this.ladang.size();
//        int colDest = idxInit % this.ladang.size();
//
//        Kartu temp = ladang.get(rowInit).get(colInit);
//        ladang.get(rowDest).set(colDest, temp);
//        ladang.get(rowInit).set(colInit, null);
//    }
}

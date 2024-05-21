package tb2.p1g.harvestmooncombat.Models;


public class Utility {

    public static Kartu getKartuObject(String nama) {
        if (Config.listKartuHewan.contains(nama)) {
            return new KartuHewan(nama);
        } else if (Config.listKartuTanaman.contains(nama)) {
            return new KartuTanaman(nama);
        } else if (Config.listKartuProduk.contains(nama)) {
            return new KartuProduk(nama);
        } else if (Config.listKartuItem.contains(nama)) {
            return new KartuItem(nama);
        } else {
            throw new IllegalArgumentException("Nama kartu tidak dikenal: " + nama);
        }
    }
}
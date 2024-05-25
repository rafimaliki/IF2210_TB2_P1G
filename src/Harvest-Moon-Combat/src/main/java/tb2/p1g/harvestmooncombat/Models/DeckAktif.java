package tb2.p1g.harvestmooncombat.Models;

import java.util.List;

public class DeckAktif extends Deck {

    public DeckAktif() {
        super(6);
    }

    public DeckAktif(List<String> kartuNames) {
        super(6);
        this.kartuNames = kartuNames;
        this.lengthKartu = kartuNames.size();
    }

    public void setDeckAktif(List<String> kartuNames) {
        this.kartuNames = kartuNames;
        this.lengthKartu = kartuNames.size();

        for (String kartuName : kartuNames) {

            try {
                if (Config.listKartuHewan.contains(kartuName)) {
                    super.tambahKartu(new KartuHewan(kartuName));
                } else if (Config.listKartuTanaman.contains(kartuName)) {
                    super.tambahKartu(new KartuTanaman(kartuName));
                } else if (Config.listKartuProduk.contains(kartuName)) {
                    super.tambahKartu(new KartuProduk(kartuName));
                } else if (Config.listKartuItem.contains(kartuName)) {
                    super.tambahKartu(new KartuItem(kartuName));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public int getJumlahSlotKosong() {
        return 6 - super.getLengthKartu();
    }

    public boolean isFull() {
        return super.getLengthKartu() == 6;
    }

    public void tambahKartu(Kartu kartu) {
        try {
            super.tambahKartu(kartu);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getKartuNames() {
        return kartuNames;
    }
}
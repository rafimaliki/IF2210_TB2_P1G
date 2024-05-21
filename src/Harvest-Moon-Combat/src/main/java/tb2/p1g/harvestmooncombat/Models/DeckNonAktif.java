package tb2.p1g.harvestmooncombat.Models;

import java.util.Random;

public class DeckNonAktif extends Deck {
    private int kartuSisa;

    public DeckNonAktif(int kartuSisa){
        super(40);
        this.kartuSisa = kartuSisa;
    }

    public int getKartuSisa(){
        return this.kartuSisa;
    }

    public void setKartuSisa(int kartuSisa){
        this.kartuSisa = kartuSisa;
    }

    public void randomize(){
        for (int i = 0; i < kartuSisa; i++){
            Random random = new Random();
            int idx = random.nextInt(4);
            if (idx == 0){
                int idxKartu = random.nextInt(Config.listKartuHewan.size());
                Kartu kartu = new KartuHewan(Config.listKartuHewan.get(idxKartu));
                this.setKartu(i, kartu);
            } else if (idx == 1){
                int idxKartu = random.nextInt(Config.listKartuTanaman.size());
                Kartu kartu = new KartuTanaman(Config.listKartuTanaman.get(idxKartu));
                this.setKartu(i, kartu);
            } else if (idx == 2){
                int idxKartu = random.nextInt(Config.listKartuProduk.size());
                Kartu kartu = new KartuProduk(Config.listKartuProduk.get(idxKartu));
                this.setKartu(i, kartu);
            } else {
                int idxKartu = random.nextInt(Config.listKartuItem.size());
                Kartu kartu = new KartuItem(Config.listKartuItem.get(idxKartu));
                this.setKartu(i, kartu);
            }
        }
    }
}

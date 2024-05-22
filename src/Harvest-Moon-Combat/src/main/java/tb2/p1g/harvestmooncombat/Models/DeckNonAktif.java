package tb2.p1g.harvestmooncombat.Models;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class DeckNonAktif extends Deck {
    private int kartuSisa;

    public DeckNonAktif(int kartuSisa){
        super(40);
        this.kartuSisa = kartuSisa;
        this.randomize();
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

    public List<Kartu> ambil4Kartu(){
        List<Kartu> kartuRandom = new ArrayList<>();
        Random random = new Random();
        boolean valid = false;
        for (int i = 0; i < 4; i++){
            System.out.println(i);
            int idx = random.nextInt(this.kartuSisa);
            if (!valid){
                for (String nama : Config.listKartuHewan){
                    if (this.getKartu(idx).getNama().equals(nama)) {
                        kartuRandom.add(new KartuHewan(this.getKartu(idx).getNama()));
                        this.kartu.remove(idx);
                        valid = true;
                        break;
                    }
                }
            }
            if (!valid){
                for (String nama : Config.listKartuTanaman){
                    if (this.getKartu(idx).getNama().equals(nama)) {
                        kartuRandom.add(new KartuTanaman(this.getKartu(idx).getNama()));
                        this.kartu.remove(idx);
                        valid = true;
                        break;
                    }
                }
            }
            if (!valid){
                for (String nama : Config.listKartuProduk){
                    if (this.getKartu(idx).getNama().equals(nama)) {
                        kartuRandom.add(new KartuProduk(this.getKartu(idx).getNama()));
                        this.kartu.remove(idx);
                        valid = true;
                        break;
                    }
                }
            }
            if (!valid){
                for (String nama : Config.listKartuItem) {
                    if (this.getKartu(idx).getNama().equals(nama)) {
                        kartuRandom.add(new KartuItem(this.getKartu(idx).getNama()));
                        this.kartu.remove(idx);
                        valid = true;
                        break;
                    }
                }
            }
            valid = false;
            this.kartuSisa--;
        }

        for (int i = 0; i < 4; i++){
            this.kartu.add(null);
        }

        for (Kartu kartu : kartuRandom){
            System.out.println("Nama: " + kartu.getNama());
        }

        System.out.println(kartuRandom.size());

        return kartuRandom;
    }

    public void kembalikanKartu(List<Kartu> kartu, int banyak){
        for (int i = 0; i < banyak; i++){
            this.kartu.set(this.kartuSisa, kartu.get(i));
            this.kartuSisa++;
        }
    }
}

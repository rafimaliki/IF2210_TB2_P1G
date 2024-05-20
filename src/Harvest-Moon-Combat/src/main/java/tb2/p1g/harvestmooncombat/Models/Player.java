package tb2.p1g.harvestmooncombat.Models;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String nama;
    private int gulden;
    private DeckAktif deckAktif;
    private DeckNonAktif deckNonAktif;
    private Ladang ladang;

    public Player(String nama){
        this.nama = nama;
        this.gulden = 0;
        this.deckAktif = new DeckAktif();
        this.deckNonAktif = new DeckNonAktif();
        this.ladang = new Ladang();
    }

    public String getNama(){
        return this.nama;
    }

    public int getGulden(){
        return this.gulden;
    }

    public DeckAktif getDeckAktif(){
        return this.deckAktif;
    }

    public DeckNonAktif getDeckNonAktif(){
        return this.deckNonAktif;
    }

    public Ladang getLadang(){
        return this.ladang;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setGulden(int gulden){
        this.gulden = gulden;
    }

    public void setDeckAktif(DeckAktif deckAktif){
        this.deckAktif = deckAktif;
    }

    public void setDeckNonAktif(DeckNonAktif deckNonAktif){
        this.deckNonAktif = deckNonAktif;
    }

    public void setLadang(Ladang ladang){
        this.ladang = ladang;
    }

    public void addGulden(int gulden){
        this.gulden += gulden;
    }

    public void reduceGulden(int gulden){
        this.gulden -= gulden;
    }

    // TAHAPAN SHUFFLE DECK

    // Get 4 kartu random dari deck non aktif
    public List<Kartu> get4RandomCard(){
        List<Kartu> kartuRandom = new ArrayList<>();
        List<Integer> indexRandom = new ArrayList<>();
        Random random = new Random();

        //Ambil index kartu yang ada di list deck non aktif
        for (int i = 0; i < 40 ; i++ ){
            if (deckNonAktif.getKartu(i) != null){
                indexRandom.add(i);
            }
        }

        // Ambil 4 kartu acak dari deck non aktif
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(indexRandom.size());
            int indexKartu = indexRandom.get(index);
            kartuRandom.add(deckNonAktif.getKartu(indexKartu));
            indexRandom.remove(index);
            deckNonAktif.removeKartu(indexKartu);
        }
        return kartuRandom;
    }

    //add kartu ke deck non aktif
    public void addKartuToDeckNonAktif(Kartu kartu){
        deckNonAktif.setKartu(deckNonAktif.getLengthKartu(), kartu);
    }

    //add kartu ke deck aktif
    public void addKartuToDeckAktif(Kartu kartu){
        deckAktif.setKartu(deckAktif.getLengthKartu(), kartu);
    }

    //remove kartu dari deck aktif
    public void removeKartuFromDeckAktif(int index){
        deckAktif.removeKartu(index);
    }

    //TAHAPAN AKSI BEBAS

    //add kartu ke ladang


}

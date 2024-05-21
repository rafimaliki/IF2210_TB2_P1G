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

    // param idxInit = lXX or dXX, parse into row/column
    public void moveKartu(String idxInit, String idxDest) throws Exception {
        String initLocation = idxInit.substring(0, 1);
        int initIndex = Integer.parseInt(idxInit.substring(1));
        String destLocation = idxDest.substring(0, 1);
        int destIndex = Integer.parseInt(idxDest.substring(1));

        if ((initLocation.equals("l")) && (destLocation.equals("d"))){
            throw new Exception("Invalid move");
        }

        int rowInit = -1;
        int colInit = -1;
        int rowDest = -1;
        int colDest = -1;

        if (initLocation.equals("l")){
            rowInit = initIndex / 5;
            colInit = initIndex % 5;
        } else {
            colInit = initIndex;
        }

        if (destLocation.equals("l")){
            rowDest = destIndex / 5;
            colDest = destIndex % 5;
        } else {
            colDest = destIndex;
        }

        // swap kartu di deck aktif
        if ((initLocation.equals("d")) && (destLocation.equals("d"))){
            Kartu temp = deckAktif.getKartu(initIndex);
            deckAktif.setKartu(initIndex, deckAktif.getKartu(destIndex));
            deckAktif.setKartu(destIndex, temp);
        }

        // move kartu dari deck aktif ke ladang
        else if ((initLocation.equals("d")) && (destLocation.equals("l"))){
            Kartu kartu = deckAktif.getKartu(initIndex);
            deckAktif.removeKartu(initIndex);

            if (ladang.getKartu(rowDest, colDest) != null){
                throw new Exception("Invalid move");
            }

            ladang.addKartu(kartu, rowDest, colDest);
        }

        // move kartu di ladang ke null (tidak bisa swap)
        else {
            if (ladang.getKartu(rowInit, colInit) == null){
                throw new Exception("Invalid move");
            }

            Kartu temp = ladang.getKartu(rowInit, colInit);
            ladang.removeKartu(rowInit, colInit);

            if (ladang.getKartu(rowDest, colDest) != null){
                throw new Exception("Invalid move");
            }

            ladang.addKartu(temp, rowDest, colDest);
        }
    }

    public void addKartuToLadang(Kartu kartu, int row, int col){
        ladang.addKartu(kartu, row, col);
    }
}

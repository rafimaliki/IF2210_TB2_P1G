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
        this.deckNonAktif = new DeckNonAktif(40);
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

    //Masukan 2 kartu ke dalam deck aktif
    public void masukanKartuKeDeckAktif(Kartu kartu1, Kartu kartu2) throws Exception{
        // Cek apakah deck aktif bisa menyimpan 2 kartu?
        if (deckAktif.getJumlahSlotKosong() < 2){
            throw new Exception("Deck aktif tidak bisa menyimpan 2 kartu");
        }
        deckAktif.setKartu(deckAktif.getLengthKartu(), kartu1);
        deckAktif.setKartu(deckAktif.getLengthKartu(), kartu2);
    }

    //Masukan 2 kartu ke dalam deck non aktif
    public void masukanKartuKeDeckNonAktif(Kartu kartu1, Kartu kartu2){
        deckNonAktif.setKartu(deckNonAktif.getLengthKartu(), kartu1);
        deckNonAktif.setKartu(deckNonAktif.getLengthKartu(), kartu2);
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

            if ((ladang.getKartu(rowDest, colDest) != null) && (!Config.listKartuItem.contains(kartu.getNama()))){
                throw new Exception("Invalid move");
            }

            // Jika kartu item
            if (Config.listKartuItem.contains(kartu.getNama())){
                // Jika tujuan null atau kartu produk
                if ((ladang.getKartu(rowDest, colDest) == null) || (Config.listKartuProduk.contains(ladang.getKartu(rowDest, colDest).getNama()))){
                    throw new Exception("Invalid move");
                }

                // Jika tujuan kartu tanaman atau hewan
                else {
                    Kartu kartuTujuan = ladang.getKartu(rowDest, colDest);
                    String namaTujuan = kartuTujuan.getNama();

                    if (kartu.getNama().equals("INSTANT_HARVEST")){
                        KartuProduk produk = new KartuProduk(Config.mapHewanTanamanKeProduk.get(namaTujuan));
                        ladang.removeKartu(rowDest, colDest);
                        ladang.addKartu(produk, rowDest, colDest);
                    }

                    kartuTujuan.setEfekItem((KartuItem) kartu);
                }
            } else {
                ladang.addKartu(kartu, rowDest, colDest);
            }
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


    public void tumbuhkanTanaman(){
        List<List<Kartu>> ladangContent = ladang.getLadang();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                Kartu kartu = ladangContent.get(i).get(j);
                if (kartu != null){
                    if (Config.listKartuTanaman.contains(kartu.getNama())){
                        KartuTanaman tanaman = (KartuTanaman) kartu;
                        tanaman.grow();
                    }
                }
            }
        }
    }

    public void Panen(String idx) throws Exception{
        int index = Integer.parseInt(idx.substring(1));
        int row = index / 5;
        int col = index % 5;

        Kartu kartu = ladang.getKartu(row, col);
        if (kartu == null){
            throw new Exception("Invalid move");
        }
        if(deckAktif.isFull()){
            throw new Exception("Deck aktif penuh");
        }

        if(!kartu.isReadyToHarvest()){
            throw new Exception("Belum siap panen");
        }

        KartuProduk produk = new KartuProduk(Config.mapHewanTanamanKeProduk.get(kartu.getNama()));
        ladang.removeKartu(row, col);
        deckAktif.setKartu(deckAktif.getLengthKartu(), produk);
    }
}

package tb2.p1g.harvestmooncombat.Models;

import tb2.p1g.harvestmooncombat.Exceptions.InvalidMoveExceptions;

import javax.smartcardio.Card;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;




public class Player implements TokoActionInterface, CardActionsInterface {

    private String nama;
    private int gulden;
    private DeckAktif deckAktif;
    private DeckNonAktif deckNonAktif;
    private LadangLogic ladang;

    public  Player(){
        this.deckNonAktif = new DeckNonAktif(40);
    };

    public Player(String nama){
        this.nama = nama;
        this.gulden = 0;
        this.deckAktif = new DeckAktif();
        this.deckNonAktif = new DeckNonAktif(40);
        this.ladang = new LadangLogic();
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

    public LadangLogic getLadang(){
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

    public void setLadang(LadangLogic ladang){
        this.ladang = ladang;
    }

    public void addGulden(int gulden){
        this.gulden += gulden;
    }

    public void reduceGulden(int gulden){
        this.gulden -= gulden;
    }

    //TAHAPAN AKSI BEBAS

    public void undoKartu(Kartu k,String idxInit){
        System.out.println("Undo!");
        String initLocation = idxInit.substring(0,1);
        int initIndex = Integer.parseInt(idxInit.substring(1));
        if(initLocation.equals("l")){
            int rowInit;
            int colInit;
            rowInit = initIndex / 5;
            colInit = initIndex % 5;
            ladang.setLadang(k,rowInit,colInit);
        }else{
            deckAktif.setKartu(initIndex, k);
        }

    }
    // param idxInit = lXX or dXX, parse into row/column
    public void moveKartu(String idxInit, String idxDest) throws Exception {
        String initLocation = idxInit.substring(0, 1);
        int initIndex = Integer.parseInt(idxInit.substring(1));
        String destLocation = idxDest.substring(0, 1);
        int destIndex = Integer.parseInt(idxDest.substring(1));
        boolean beriMakan = false;
        LadangLogic prosesladang;
        if (GameManager.getInstance().getViewLawan()){
            System.out.println("Ladang lawan!");
            prosesladang = GameManager.getInstance().getCurrentLadang();
        } else {
            System.out.println("Ladang pribadi");
            prosesladang = ladang;
        }

        if ((initLocation.equals("l")) && (destLocation.equals("d"))){
            throw new InvalidMoveExceptions("Invalid Move");
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

            Kartu dest = ladang.getKartu(rowDest,colDest);
            //kasih makan dari deck ke ladang
            if ((Config.listKartuProduk.contains(kartu.getNama())) && (dest != null) && (Config.listKartuHewan.contains(dest.getNama())) && (!GameManager.getInstance().getViewLawan())){
                KartuHewan k_hewan = (KartuHewan) dest;
                KartuProduk k_produk = (KartuProduk) kartu;
                //Karnivore herbivore atau omnivore
                String tipe_dest = Config.mapTipeHewan.get(dest.getNama());
                if (tipe_dest.equals("CARNIVORE")){
                    if (Config.makananKarnivore.contains(k_produk.getNama())){
                        k_hewan.tambahBerat(k_produk);
                        beriMakan = true;
                    } else {
                        throw new InvalidMoveExceptions("Tipe makanan tidak sesuai!", kartu);
                    }
                } else if (tipe_dest.equals("HERBIVORE")){
                    if (Config.makananHerbivore.contains(k_produk.getNama())){
                        k_hewan.tambahBerat(k_produk);
                        beriMakan = true;
                    } else {
                        throw new InvalidMoveExceptions("Tipe makanan tidak sesuai!", kartu);
                    }
                } else {
                    k_hewan.tambahBerat(k_produk);
                    beriMakan = true;
                }
            }
            else if ((prosesladang.getKartu(rowDest, colDest) != null) && (!Config.listKartuItem.contains(kartu.getNama()))){
                throw new InvalidMoveExceptions("Invalid Move",kartu);
            }

            // Jika kartu item
            else if (Config.listKartuItem.contains(kartu.getNama())){
                // Jika tujuan null atau kartu produk
                if ((prosesladang.getKartu(rowDest, colDest) == null) || (Config.listKartuProduk.contains(prosesladang.getKartu(rowDest, colDest).getNama()))){
                    throw new InvalidMoveExceptions("Invalid move: " + kartu.getNama() + " cannot be placed on an empty or product slot.", kartu);
                }

                // Jika tujuan kartu tanaman atau hewan
                else {
                    if (GameManager.getInstance().getViewLawan()){ // Ladang lawan
                        Kartu kartuTujuan = prosesladang.getKartu(rowDest,colDest);
                        if ((kartu.getNama().equals("DELAY")) || (kartu.getNama().equals("DESTROY"))){
                            if (kartu.getNama().equals("DESTROY")){
                                prosesladang.removeKartu(rowDest,colDest);
                            } else {
                                if (Config.listKartuHewan.contains(kartuTujuan.getNama())){
                                    KartuHewan kh = (KartuHewan) kartuTujuan;
                                    kh.setEfekItem((KartuItem) kartu);
                                } else if (Config.listKartuTanaman.contains(kartuTujuan.getNama())){
                                    KartuTanaman kt = (KartuTanaman) kartuTujuan;
                                    kt.setEfekItem((KartuItem) kartu);
                                }

                            }

                        } else{
                            throw new InvalidMoveExceptions("Gk bisa! bukan delay atau destroy", kartu);
                        }
                    } else { // Ladang sendiri
                        if ((kartu.getNama().equals("DELAY")) || (kartu.getNama().equals("DESTROY"))){
                            throw new InvalidMoveExceptions("Tidak bisa menaruh delay atau destroy di ladang sendiri!", kartu);
                        }

                        Kartu kartuTujuan = ladang.getKartu(rowDest, colDest);
                        String namaTujuan = kartuTujuan.getNama();

                        if (kartu.getNama().equals("INSTANT_HARVEST")){
                            KartuProduk produk = new KartuProduk(Config.mapHewanTanamanKeProduk.get(namaTujuan));
                            prosesladang.removeKartu(rowDest, colDest);
                            prosesladang.addKartu(produk, rowDest, colDest);
                        }
                        if (Config.listKartuHewan.contains(kartuTujuan.getNama())){
                            KartuHewan kh = (KartuHewan) kartuTujuan;
                            kh.setEfekItem((KartuItem) kartu);
                        } else if (Config.listKartuTanaman.contains(kartuTujuan.getNama())){
                            KartuTanaman kt = (KartuTanaman) kartuTujuan;
                            kt.setEfekItem((KartuItem) kartu);
                        }

                    }
                }
            } else {
                if (GameManager.getInstance().getViewLawan()){
                    throw new InvalidMoveExceptions("Tidak bisa menaro kartu selain item di ladang lawan",kartu);
                }
                if (!beriMakan){
                    prosesladang.addKartu(kartu, rowDest, colDest);
                }
            }
        }

        // move kartu di ladang ke null (tidak bisa swap)
        else {
            if (ladang.getKartu(rowInit, colInit) == null){
                throw new InvalidMoveExceptions("Row ini col init null, invalid!");
            }

            Kartu temp = ladang.getKartu(rowInit, colInit);
            Kartu dest = ladang.getKartu(rowDest,colDest);
            ladang.removeKartu(rowInit, colInit);

            //Kartu valid ke kartu valid
            if (ladang.getKartu(rowDest, colDest) != null){
                // kartu produk cuma bisa ke hewan
                if (Config.listKartuProduk.contains(temp.getNama()) && Config.listKartuHewan.contains(dest.getNama())){
                    KartuHewan k_hewan = (KartuHewan) dest;
                    KartuProduk k_produk = (KartuProduk) temp;
                    //Karnivore herbivore atau omnivore
                    String tipe_dest = Config.mapTipeHewan.get(dest.getNama());
                    if (tipe_dest.equals("CARNIVORE")){
                        if (Config.makananKarnivore.contains(k_produk.getNama())){
                            k_hewan.tambahBerat(k_produk);
                        } else {
                            throw new InvalidMoveExceptions("Tipe makanan tidak sesuai!",temp);
                        }
                    } else if (tipe_dest.equals("HERBIVORE")) {
                        if (Config.makananHerbivore.contains(k_produk.getNama())){
                            k_hewan.tambahBerat(k_produk);
                        } else {
                            throw new InvalidMoveExceptions("Tipe makanan tidak sesuai!",temp);
                        }
                    } else {
                        k_hewan.tambahBerat(k_produk);
                    }
                } else {
                    throw new InvalidMoveExceptions("Tujuan bukan hewan atau inisial bukan produk! gagal",temp);
                }
            }else{
                ladang.addKartu(temp, rowDest, colDest);
            }

        }
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
        if(Config.listKartuHewan.contains(kartu.getNama())){
            KartuHewan kh = (KartuHewan) kartu;
            if(!kh.isReadyToHarvest()){
                throw new Exception("Belum siap panen");
            }
        }else if(Config.listKartuTanaman.contains(kartu.getNama())){
            KartuTanaman kt = (KartuTanaman) kartu;
            if(!kt.isReadyToHarvest()){
                throw new Exception("Belum siap panen");
            }
        }



        KartuProduk produk = new KartuProduk(Config.mapHewanTanamanKeProduk.get(kartu.getNama()));
        ladang.removeKartu(row, col);
        ladang.addKartu(produk,row,col);
    }

    public void displayLadangData(){
        getLadang().displayDataKartuLadang();
    }

    public void Ambil(String idx) throws Exception {
        int index = Integer.parseInt(idx.substring(1));
        int row = index / 5;
        int col = index % 5;

        Kartu kartu = ladang.getKartu(row,col);
        if(deckAktif.isFull()){
            throw new InvalidMoveExceptions("Deck penuh! invalid");
        }

        KartuProduk produk = new KartuProduk(ladang.getKartu(row,col).getNama());
        ladang.removeKartu(row, col);
        for (int i = 0; i <6 ; i++) {
            if(deckAktif.getKartu(i) == null){
                deckAktif.setKartu(i, produk);
                break;
            }

        }


    }

    public void jual(String deckIdx) throws Exception {
        int idx = Integer.parseInt(deckIdx.substring(1));

        Kartu kartu = this.deckAktif.kartu.get(idx);
        if (kartu == null){
            throw new Exception("Slot itu kosong banh");
        }

        String namaKartuProduk = kartu.getNama();

        if (!Config.listKartuProduk.contains(namaKartuProduk)){
            throw new Exception("Kartu ini bukan kartu produk!");
        }

        Toko.addProduk(new KartuProduk(namaKartuProduk));

        for (TokoEntry produkToko : Toko.produkToko){
            if (produkToko.getKartu().getNama().equals(namaKartuProduk)){
                this.gulden += produkToko.getHargaSatuan();
                this.deckAktif.removeKartu(idx);
            }
        }
    }

    public void beli(String namaKartuProduk) throws Exception {
        if (!Config.listKartuProduk.contains(namaKartuProduk)){
            throw new Exception("Lah ini ga ada di toko saya, kamu colong dari toko siapa?");
        }

        if (this.deckAktif.getJumlahSlotKosong() == 0){
            throw new Exception("Deck aktif kamu penuh, tidak bisa membeli!");
        }

        int hargaProduk = Toko.getHargaProduk(namaKartuProduk);
        if (this.gulden < hargaProduk){
            throw new Exception("Uang kamu tidak cukup!");
        }

        this.gulden -= hargaProduk;
        KartuProduk produkPembelian = Toko.subtractProduk(namaKartuProduk);
        this.deckAktif.tambahKartu(produkPembelian);
    }
}

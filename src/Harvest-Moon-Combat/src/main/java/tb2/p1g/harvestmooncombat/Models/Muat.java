package tb2.p1g.harvestmooncombat.Models;

import javafx.scene.control.Label;
import tb2.p1g.harvestmooncombat.Controllers.MainScreenController;
import tb2.p1g.harvestmooncombat.Views.ViewFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Muat implements MuatInterface {
    private int current_turn;
    private int jumlah_item_shop;
    Map<String, Integer> item_shop;
    private Player player1;
    private Player player2;

    private String player1path;
    private String player2path;
    private String gameStatePath;

    public Muat(List<String> paths) {
        // my file name is with absolute path, i want to check just the file name

        for (String path : paths){
            if (path.contains("gamestate")) {
                this.gameStatePath = path;
            }
            else if (path.contains("player1")) {
                this.player1path = path;
            }
            else if (path.contains("player2")) {
                this.player2path = path;
            }
        }
    }

    public void loadGame() {
        if (tryReadFile(this.gameStatePath)) {
            loadGameState(this.gameStatePath);
        }
        if (tryReadFile(this.player1path)) {
            loadPlayer(this.player1path, 1);
        }
        if (tryReadFile(this.player2path)) {
            loadPlayer(this.player2path, 2);
        }
    }

    public boolean tryReadFile(String path) {
        // Cek apakah file ada
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    public void loadGameState(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            // Baca file
            String line = reader.readLine();
            // Baca Current Turn
            this.current_turn = Integer.parseInt(line);

            // Baca jumlah item shop
            line = reader.readLine();
            this.jumlah_item_shop = Integer.parseInt(line);

            this.item_shop = new HashMap<>();

            // Baca Item Shop
            for (int i = 0; i < this.jumlah_item_shop; i++) {
                line = reader.readLine();
                String[] parts = line.split(" ");
                this.item_shop.put(parts[0], Integer.parseInt(parts[1]));
                System.out.println(parts[0] + " " + parts[1]);
            }

            // Masukan ke game stat
            GameManager.getInstance().setTurnNumber(current_turn);
            for (Map.Entry<String, Integer> entry : item_shop.entrySet()) {
                for(int i = 0; i < entry.getValue(); i++){
                    Toko.addProduk(new KartuProduk(entry.getKey()));
                }
            }

            reader.close();
            ((Label) ViewFactory.Root.lookup("#turnNumber")).setText(""+current_turn);
            System.out.println(current_turn);
            System.out.println("Game State Loaded");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPlayer(String path, int player) {
        try {
            // Baca file
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line = reader.readLine();
            int gulden = Integer.parseInt(line);

            line = reader.readLine();
            int kartuSisa = Integer.parseInt(line);

            // Inisialisasi deck Aktif
            line = reader.readLine();
            int jumlah_deck_aktif = Integer.parseInt(line);
            DeckAktif deckAktif = new DeckAktif();
            for (int i = 0; i < jumlah_deck_aktif; i++){
                line = reader.readLine();
                String[] parts = line.split(" ");
                int baris = Integer.parseInt(parts[0].substring(1)) - 1;
                int kolom = parts[0].charAt(0) - 'A';
                String nama = parts[1];
                Kartu kartu;
                if (Config.listKartuHewan.contains(nama)){
                    kartu = new KartuHewan(nama);
                } else if (Config.listKartuTanaman.contains(nama)){
                    kartu = new KartuTanaman(nama);
                } else if(Config.listKartuProduk.contains(nama)){
                    kartu = new KartuProduk(nama);
                } else{
                    kartu = new KartuItem(nama);
                }
                deckAktif.setKartu(baris * 5 + kolom, kartu);
            }

            // Inisialisasi ladang
            line = reader.readLine();
            int jumlah_kartu_ladang = Integer.parseInt(line);
            LadangLogic ladang = new LadangLogic();
            for (int i = 0; i < jumlah_kartu_ladang; i++){
                line = reader.readLine();
                String[] parts = line.split(" ");

                int baris = Integer.parseInt(parts[0].substring(1)) - 1;
                int kolom = parts[0].charAt(0) - 'A';

                String nama = parts[1];

                int umur_berat = 0;
                if(!Config.listKartuProduk.contains(nama)) {
                    umur_berat = Integer.parseInt(parts[2]);
                }

                Kartu kartu;
                if (Config.listKartuHewan.contains(nama)){
                    kartu = new KartuHewan(nama);
                    ((KartuHewan) kartu).setBerat(umur_berat);

                }else if (Config.listKartuTanaman.contains(nama)){
                    kartu = new KartuTanaman(nama);
                    ((KartuTanaman) kartu).setUmur(umur_berat);

                }else {
                    kartu = new KartuProduk(nama);
                }

                if(!Config.listKartuProduk.contains(nama)){
                    for (int j = 4 ; j < 4 + Integer.parseInt(parts[3]); j++){
                        if(Config.listKartuHewan.contains(kartu.getNama())){
                            KartuHewan kh = (KartuHewan) kartu;
                            kh.setEfekItem(new KartuItem(parts[j]));

                        }else if(Config.listKartuTanaman.contains(kartu.getNama())){
                            KartuTanaman kt = (KartuTanaman) kartu;
                            kt.setEfekItem(new KartuItem(parts[j]));

                        }
                    }
                }

                if (Config.listKartuHewan.contains(nama)){
                    ((KartuHewan) kartu).setBerat(umur_berat);

                }else if (Config.listKartuTanaman.contains(nama)){
                    ((KartuTanaman) kartu).setUmur(umur_berat);

                }


                ladang.setLadang(kartu, baris, kolom);
            }

            if(player == 1){
                this.player1 = new Player("Player 1");
                this.player1.setGulden(gulden);
                this.player1.setDeckAktif(deckAktif);
                this.player1.setLadang(ladang);
            }else{
                this.player2 = new Player("Player 2");
                this.player2.setGulden(gulden);
                this.player2.setDeckAktif(deckAktif);
                this.player2.setLadang(ladang);
            }
            reader.close();

            GameManager.getInstance().getPlayers().set(0, player1);
            GameManager.getInstance().getPlayers().set(1, player2);
            GameManager.getInstance().setDeckAktif(GameManager.getInstance().getCurrentPlayer().getDeckAktif());
            GameManager.getInstance().setLadang(GameManager.getInstance().getCurrentPlayer().getLadang());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
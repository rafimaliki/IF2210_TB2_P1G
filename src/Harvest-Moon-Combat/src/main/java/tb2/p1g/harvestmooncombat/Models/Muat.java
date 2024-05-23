package tb2.p1g.harvestmooncombat.Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class Muat implements MuatInterface {
    private int current_turn;
    private int jumlah_item_shop;
    Map<String, Integer> item_shop;
    private Player player1;
    private Player player2;

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
            // Baca file
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            // Baca Current Turn
            this.current_turn = Integer.parseInt(line);

            // Baca jumlah item shop
            line = reader.readLine();
            this.jumlah_item_shop = Integer.parseInt(line);

            // Baca Item Shop
            for (int i = 0; i < this.jumlah_item_shop; i++) {
                line = reader.readLine();
                String[] parts = line.split(" ");
                this.item_shop.put(parts[0], Integer.parseInt(parts[1]));
            }
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
                int baris = Integer.parseInt(parts[0].substring(1));
                int kolom = parts[0].charAt(0) - 'A';
                String nama = parts[1];
                Kartu kartu;
                if (Config.listKartuHewan.contains(nama)){
                    kartu = new KartuHewan(nama);
                } else if (Config.listKartuTanaman.contains(nama)){
                    kartu = new KartuTanaman(nama);
                } else if (Config.listKartuProduk.contains(nama)){
                    kartu = new KartuProduk(nama);
                } else {
                    kartu = new KartuItem(nama);
                }
                deckAktif.setKartu(baris * 5 + kolom, kartu);
            }

            // Inisialisasi ladang
            line = reader.readLine();
            int jumlah_kartu_ladang = Integer.parseInt(line);
            Ladang_Logic ladang = new Ladang_Logic();
            for (int i = 0; i < jumlah_kartu_ladang; i++){
                line = reader.readLine();
                String[] parts = line.split(" ");

                int baris = Integer.parseInt(parts[0].substring(1));
                int kolom = parts[0].charAt(0) - 'A';

                String nama = parts[1];

                int umur_berat = Integer.parseInt(parts[2]);

                Kartu kartu;
                if (Config.listKartuHewan.contains(nama)){
                    kartu = new KartuHewan(nama);
                    ((KartuHewan) kartu).setBerat(umur_berat);
                } else {
                    kartu = new KartuTanaman(nama);
                    ((KartuTanaman) kartu).setUmur(umur_berat);
                }

                for (int j = 4 ; j < 4 + Integer.parseInt(parts[3]); j++){
                    kartu.setEfekItem(new KartuItem(parts[j]));
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
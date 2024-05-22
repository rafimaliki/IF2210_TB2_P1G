package tb2.p1g.harvestmooncombat.Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class Muat {
    private Player player1;
    private int current_turn;
    private int jumlah_item_shop;
    Map<String, Integer> item_shop;
    private int gulden_player1;
    private int gulden_player2;
    private int jumlah_deck_nonaktif_player1;
    private int jumlah_deck_nonaktif_player2;
    private int jumlah_deck_aktif_player1;
    private int jumlah_deck_aktif_player2;
    private DeckAktif deck_aktif_player1;
    private DeckAktif deck_aktif_player2;
    private int jumlah_kartu_ladang_player1;
    private int jumlah_kartu_ladang_player2;
    private Ladang_Logic ladang_player1;
    private Ladang_Logic ladang_player2;

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

}
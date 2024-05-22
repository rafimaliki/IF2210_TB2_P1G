package tb2.p1g.harvestmooncombat.Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class Muat {
    private int current_turn;
    private int jumlah_item_shop;
    Map<String, Integer> item_shop;
    private Player player1;
    private Player player2;
    private DeckNonAktif deckNonAktif_Player1;
    private DeckNonAktif deckNonAktif_Player2;
    private DeckAktif deckAktif_Player1;
    private DeckAktif getDeckAktif_Player2;

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
            line = reader.readLine();
            int jumlah_deck_aktif = Integer.parseInt(line);
            for (int i = 0; i < jumlah_deck_aktif; i++){
                line = reader.readLine();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
package tb2.p1g.harvestmooncombat.Models;

import java.io.FileWriter;
import java.util.List;

public class Simpan implements SimpanInterface {

    private String path;

    public Simpan(String absolutePath) {
        this.path = absolutePath;
    }


    private String convert_gameStatustoString() {
        String result = "";
        // cari jumlah turn saat ini
        int sumTurn = GameManager.getInstance().getTurnNumber();
        result += sumTurn + "\n";

        // cari jumlah product
        int countProduct = 0;
        for (TokoEntry tk : Toko.produkToko) {
            if (tk != null) {
                countProduct++;
            }
        }
        result += countProduct + "\n";

        // cari product
        for (TokoEntry tk : Toko.produkToko) {
            if (tk != null) {
                result += tk.getKartu().getNama() + " " + tk.getKuantitas() + "\n";
            }
        }

        return result;
    }

    private String convert_playertoString(Player player) {
        // Inisialisasi result
        String result = "";

        // Ubah gulden ke string
        int gulden = player.getGulden();
        result += gulden + "\n";

        // Ubah deck non aktif ke string
        int lenDeckNonAktif = player.getDeckNonAktif().getKartuSisa();
        result += lenDeckNonAktif + "\n";

        // Masukan panjang deckAktif ke string
        int lenDeckAktif = player.getDeckAktif().getLengthKartu();
        result += lenDeckAktif + "\n";

        // Masukan deckAktif ke string
        for (int i = 0; i < 6; i++) {
            Kartu kartu = player.getDeckAktif().getKartu(i);
            if (kartu != null) {
                char kolom = (char) ('A' + i);
                String kolomStr = kolom + "01";
                String namaKartu = kartu.getNama();
                result += kolomStr + " " + namaKartu + "\n";
            }
        }

        //Inisalisasi ladang
        LadangLogic ladang = player.getLadang();
        result += ladang.getJumlahKartu() + "\n";

        // Ubah ladang ke string
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Kartu kartu = ladang.getKartu(i, j);
                if (kartu != null) {
                    // Cari string index
                    char kolom = (char) ('A' + j);
                    String baris = Integer.toString(i + 1);
                    baris = "0" + baris;
                    String indexStr = kolom + baris;

                    // Cari nama kartu
                    String namaKartu = kartu.getNama();

                    int umur_berat = 0;
                    if (Config.listKartuTanaman.contains(namaKartu)) {
                        KartuTanaman tanaman = (KartuTanaman) kartu;
                        umur_berat = tanaman.getUmur();
                    } else if (Config.listKartuHewan.contains(namaKartu)) {
                        KartuHewan hewan = (KartuHewan) kartu;
                        umur_berat = hewan.getBerat();
                    } else {
                        KartuProduk produk = (KartuProduk) kartu;
                    }

                    String efekItemStr = "";
                    int banyak_efek_item = 0;
                    if (!Config.listKartuProduk.contains(namaKartu)) {
                        List<String> efekItem = kartu.getEfekItem();
                        banyak_efek_item = efekItem.size();
                        for (String efek : efekItem) {
                            efekItemStr += " " + efek;
                        }
                    }

                    result += indexStr + " " + namaKartu;

                    if (Config.listKartuProduk.contains(namaKartu)) {
                        result += "\n";
                    } else {
                        result += " " + umur_berat + " " + banyak_efek_item + efekItemStr + "\n";
                    }
                }
            }
        }
        return result;
    }


    public void writeSavePlayer(Player player) {
        // Inisialisasi result
        String result = "";


        // Ubah player ke string
        result += convert_playertoString(player);

        //get id player
        String nama = player.getNama();
        //hilangkan spasi dari nama dan buat semuanya huruf kecil
        nama = nama.replaceAll("\\s", "").toLowerCase();



        // Tulis ke file
        try {
            nama = '/' + nama;
            FileWriter myWriter = new FileWriter(this.path + nama + ".txt");
            myWriter.write(result);
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeGameState() {
        // Inisialisasi result
        String result = "";

        // Ubah game state ke string
        result += convert_gameStatustoString();

        // Tulis ke file
        try {
            //simpan file txt ke path

            FileWriter myWriter = new FileWriter(this.path + "/gamestate.txt");
            myWriter.write(result);
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveEntryPoint(){
        writeGameState();
        writeSavePlayer(GameManager.getInstance().getPlayerOne());
        writeSavePlayer(GameManager.getInstance().getPlayerTwo());
    }

    public void writeGameState(StateSave stateSave) {}
}



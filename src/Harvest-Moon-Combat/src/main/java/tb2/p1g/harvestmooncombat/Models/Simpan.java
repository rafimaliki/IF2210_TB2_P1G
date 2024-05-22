package tb2.p1g.harvestmooncombat.Models;

public class Simpan {
    private String gameStatus
    private String convert_gameStatustoString(){
        String result = "";
        // cari jumlah turn saat ini
        int sumTurn = GameManager.getInstance().getSumTurn();
        result += sumTurn + "\n";

        int countProduct = 0;
        for(TokoEntry tk : Toko.produkToko){
            if(tk != null){
                countProduct++;
            }
        }
        result += countProduct + "\n";
        for(TokoEntry tk : Toko.produkToko){
            if(tk != null){
                result += tk.getKartu().getNama() + " " + tk.getKuantitas() + "\n";
            }
        }

        return result;
    }

    private String convert_playertoString(Player player){
        String result = "";
        int gulden = player.getGulden();
        result += gulden + "\n";

        int lenDeckNonAktif = player.getDeckNonAktif().getKartuSisa();
        result += lenDeckNonAktif + "\n";
        int lenDeckAktif = player.getDeckAktif().getLengthKartu();
        result += lenDeckAktif + "\n";
        for (int i = 0; i < 6; i++){
            Kartu kartu = player.getDeckAktif().getKartu(i);
            if (kartu != null){
                char kolom = (char)('A' + i);
                String kolomStr = kolom + "01 ";
                String namaKartu = kartu.getNama();
                result += kolomStr + namaKartu + "\n";
            }
        }

        Ladang_Logic ladang = player.getLadang();

        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                Kartu kartu = ladang.getKartu(i,j);
                if(kartu != null){
                    char kolom = (char)('A' + j);
                    String baris = Integer.toString(i+1);
                    if (baris.length() == 1){
                        baris = "0" + baris;
                    }
                    String indexStr = kolom + baris + " ";
                    String namaKartu = kartu.getNama();

                    int umur_berat = 0;
                }
            }
        }


    }
}

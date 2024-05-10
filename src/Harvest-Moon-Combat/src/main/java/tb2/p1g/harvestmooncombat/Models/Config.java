package tb2.p1g.harvestmooncombat.Models;

import java.util.*;

public class Config {
    final static int UMUR_PANEN_JAGUNG = 3;
    final static int UMUR_PANEN_LABU = 5;
    final static int UMUR_PANEN_STROBERI = 4;

    final static int BERAT_PANEN_HIU_DARAT = 20;
    final static int BERAT_PANEN_SAPI = 10;
    final static int BERAT_PANEN_DOMBA = 12;
    final static int BERAT_PANEN_KUDA = 14;
    final static int BERAT_PANEN_AYAM = 5;
    final static int BERAT_PANEN_BERUANG = 25;

    final static int HARGA_SIRIP_HIU = 500;
    final static int HARGA_SUSU = 100;
    final static int HARGA_DAGING_DOMBA = 120;
    final static int HARGA_DAGING_KUDA = 150;
    final static int HARGA_TELUR = 50;
    final static int HARGA_DAGING_BERUANG = 500;
    final static int HARGA_JAGUNG = 150;
    final static int HARGA_LABU = 500;
    final static int HARGA_STROBERI = 350;

    final static int BERAT_TAMBAH_SIRIP_HIU = 12;
    final static int BERAT_TAMBAH_SUSU = 4;
    final static int BERAT_TAMBAH_DAGING_DOMBA = 6;
    final static int BERAT_TAMBAH_DAGING_KUDA = 8;
    final static int BERAT_TAMBAH_TELUR = 2;
    final static int BERAT_TAMBAH_DAGING_BERUANG = 12;
    final static int BERAT_TAMBAH_JAGUNG = 3;
    final static int BERAT_TAMBAH_LABU = 10;
    final static int BERAT_TAMBAH_STROBERI = 5;

    // map berat panen hewan String (nama) -> Integer (berat)
    final static Map<String, Integer> mapBeratPanen = new HashMap<>(){{
        put("HIU_DARAT", BERAT_PANEN_HIU_DARAT);
        put("SAPI", BERAT_PANEN_SAPI);
        put("DOMBA", BERAT_PANEN_DOMBA);
        put("KUDA", BERAT_PANEN_KUDA);
        put("AYAM", BERAT_PANEN_AYAM);
        put("BERUANG", BERAT_PANEN_BERUANG);
    }};

    // map umur panen tanaman String (nama) -> Integer (umur)
    final static Map<String, Integer> mapUmurPanen = new HashMap<>(){{
        put("JAGUNG", UMUR_PANEN_JAGUNG);
        put("LABU", UMUR_PANEN_LABU);
        put("STROBERI", UMUR_PANEN_STROBERI);
    }};

    // map produk String (nama) -> List<Integer (harga), Integer (berat_tambah)>
    final static Map<String, List<Integer>> mapProduk = new HashMap<>(){{
        put("SIRIP_HIU", Arrays.asList(HARGA_SIRIP_HIU, BERAT_TAMBAH_SIRIP_HIU));
        put("SUSU", Arrays.asList(HARGA_SUSU, BERAT_TAMBAH_SUSU));
        put("DAGING_DOMBA", Arrays.asList(HARGA_DAGING_DOMBA, BERAT_TAMBAH_DAGING_DOMBA));
        put("DAGING_KUDA", Arrays.asList(HARGA_SIRIP_HIU, BERAT_TAMBAH_SIRIP_HIU));
        put("TELUR", Arrays.asList(HARGA_TELUR, BERAT_TAMBAH_TELUR));
        put("DAGING_BERUANG", Arrays.asList(HARGA_DAGING_BERUANG, BERAT_TAMBAH_DAGING_BERUANG));
        put("JAGUNG", Arrays.asList(HARGA_JAGUNG, BERAT_TAMBAH_JAGUNG));
        put("LABU", Arrays.asList(HARGA_LABU, BERAT_TAMBAH_LABU));
        put("STROBERI", Arrays.asList(HARGA_STROBERI, BERAT_TAMBAH_STROBERI));
    }};
}

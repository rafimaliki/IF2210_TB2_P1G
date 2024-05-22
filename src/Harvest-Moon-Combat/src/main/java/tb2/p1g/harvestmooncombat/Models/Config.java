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

    // list kartu hewan yang ada pada permainan
    final static List<String> listKartuHewan = new ArrayList<>(){{
        add("HIU_DARAT");
        add("SAPI");
        add("DOMBA");
        add("KUDA");
        add("AYAM");
        add("BERUANG");
    }};

    // list kartu tanaman yang ada pada permainan
    final static List<String> listKartuTanaman = new ArrayList<>(){{
        add("BIJI_JAGUNG");
        add("BIJI_LABU");
        add("BIJI_STROBERI");
    }};

    // list kartu produk yang ada pada permainan
    final static List<String> listKartuProduk = new ArrayList<>(){{
        add("LABU");
        add("JAGUNG");
        add("STROBERI");
        add("SUSU");
        add("TELUR");
        add("SIRIP_HIU");
        add("DAGING_KUDA");
        add("DAGING_DOMBA");
        add("DAGING_BERUANG");
    }};

    // list kartu item yang ada pada permainan
    final static List<String> listKartuItem = new ArrayList<>(){{
        add("ACCELERATE");
        add("DELAY");
        add("INSTANT_HARVEST");
        add("DESTROY");
        add("PROTECT");
        add("TRAP");
    }};

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
        put("BIJI_JAGUNG", UMUR_PANEN_JAGUNG);
        put("BIJI_LABU", UMUR_PANEN_LABU);
        put("BIJI_STROBERI", UMUR_PANEN_STROBERI);
    }};

    final static Map<String, String> mapHewanTanamanKeProduk = new HashMap<>(){{
        put("HIU_DARAT", "SIRIP_HIU");
        put("SAPI", "SUSU");
        put("DOMBA", "DAGING_DOMBA");
        put("KUDA", "DAGING_KUDA");
        put("AYAM", "TELUR");
        put("BERUANG", "DAGING_BERUANG");
        put("BIJI_JAGUNG", "JAGUNG");
        put("BIJI_LABU", "LABU");
        put("BIJI_STROBERI", "STROBERI");
    }};

    // map produk String (nama) -> List<Integer (harga), Integer (berat_tambah)>
    public final static Map<String, List<Integer>> mapProduk = new HashMap<>(){{
        put("SIRIP_HIU", Arrays.asList(HARGA_SIRIP_HIU, BERAT_TAMBAH_SIRIP_HIU));
        put("SUSU", Arrays.asList(HARGA_SUSU, BERAT_TAMBAH_SUSU));
        put("DAGING_DOMBA", Arrays.asList(HARGA_DAGING_DOMBA, BERAT_TAMBAH_DAGING_DOMBA));
        put("DAGING_KUDA", Arrays.asList(HARGA_DAGING_KUDA, BERAT_TAMBAH_DAGING_KUDA));
        put("TELUR", Arrays.asList(HARGA_TELUR, BERAT_TAMBAH_TELUR));
        put("DAGING_BERUANG", Arrays.asList(HARGA_DAGING_BERUANG, BERAT_TAMBAH_DAGING_BERUANG));
        put("JAGUNG", Arrays.asList(HARGA_JAGUNG, BERAT_TAMBAH_JAGUNG));
        put("LABU", Arrays.asList(HARGA_LABU, BERAT_TAMBAH_LABU));
        put("STROBERI", Arrays.asList(HARGA_STROBERI, BERAT_TAMBAH_STROBERI));
    }};

    // map tipe hewan String (nama) -> String (tipe)
    final static Map<String, String> mapTipeHewan = new HashMap<>(){{
        put("HIU_DARAT", "CARNIVORE");
        put("SAPI", "HERBIVORE");
        put("DOMBA", "HERBIVORE");
        put("KUDA", "HERBIVORE");
        put("AYAM", "HERBIVORE");
        put("BERUANG", "HERBIVORE");
    }};
    final static Map<String, String> mapTipeKartu = new HashMap<>(){{
        put("ACCELERATE", "ACCELERATE");
        put("DELAY", "DELAY");
        put("INSTANT_HARVEST", "INSTANT_HARVEST");
        put("DESTROY", "DESTROY");
        put("PROTECT", "PROTECT");
        put("TRAP", "TRAP");
    }};
}

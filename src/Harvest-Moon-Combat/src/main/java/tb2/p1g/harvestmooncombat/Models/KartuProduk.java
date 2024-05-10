package tb2.p1g.harvestmooncombat.Models;

public class KartuProduk implements Kartu {
    private String nama;
    private int hargaJual;
    private int beratTambah;

    public KartuProduk(String nama){
        this.nama = nama;
        this.hargaJual = Config.mapProduk.get(this.nama).getFirst();
        this.beratTambah = Config.mapProduk.get(this.nama).getLast();
    }

    public String getNama(){
        return this.nama;
    }

    public int getHargaJual(){
        return this.hargaJual;
    }

    public int getBeratTambah(){
        return this.beratTambah;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setHargaJual(int hargaJual){
        this.hargaJual = hargaJual;
    }

    public void setBeratTambah(int beratTambah){
        this.beratTambah = beratTambah;
    }
}

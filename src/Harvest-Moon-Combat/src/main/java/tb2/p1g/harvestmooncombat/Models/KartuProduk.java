package tb2.p1g.harvestmooncombat.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KartuProduk extends Kartu {
    private String nama;
    private int hargaJual;
    private int beratTambah;

    public KartuProduk(String nama){
        this.nama = nama;
        this.hargaJual = Config.mapProduk.get(this.nama).getFirst();
        this.beratTambah = Config.mapProduk.get(this.nama).getLast();
    }

    public KartuProduk(KartuProduk other){
        this.nama = other.getNama();
        this.hargaJual = other.getHargaJual();
        this.beratTambah = other.getBeratTambah();
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

    public List<String> getInformasi(){
        List<String> informasi = new ArrayList<>();
        informasi.add(this.nama);
        informasi.add("Harga Jual: " + this.hargaJual);
        informasi.add("Berat Tambah: " + this.beratTambah);

        return informasi;
    }
}

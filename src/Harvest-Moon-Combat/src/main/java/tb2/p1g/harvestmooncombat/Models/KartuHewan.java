package tb2.p1g.harvestmooncombat.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class KartuHewan extends Kartu {

    private String nama;
    private int berat;
    private String type;
    private Map<String, Integer> itemAktif;
    public KartuHewan(){};

    public KartuHewan(String namaHewan){
        this.nama = namaHewan;
        this.berat = 0;
        this.type = Config.mapTipeHewan.get(namaHewan);
        this.itemAktif = new HashMap<>(){{
            put("ACCELERATE", 0);
            put("DELAY", 0);
            put("PROTECT", 0);
            put("TRAP", 0);
        }};
    }

    public String getNama(){
        return  this.nama;
    }

    public int getBerat(){
        return  this.berat;
    }

    public String getType(){
        return this.type;
    }

    public Map<String, Integer> getItemAktif(){
        return this.itemAktif;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setBerat(int berat){
        this.berat = berat;
    }

    public void setEfekItem(KartuItem kartuItem) throws Exception {
        String namaItem = kartuItem.getNama();

        if ((namaItem.equals("ACCELERATE")) || (namaItem.equals("DELAY")) || (namaItem.equals("PROTECT")) || (namaItem.equals("TRAP"))){
            this.itemAktif.put(kartuItem.getNama(), this.itemAktif.get(kartuItem.getNama()) + 1);
        }

        if (namaItem.equals("ACCELERATE")){
            this.berat += 8;
        } else if (namaItem.equals("DELAY")){
            this.berat -= 5 * this.itemAktif.get("DELAY");
            if (this.berat < 0){
                this.berat = 0;
            }
        }
    }

    public void tambahBerat(KartuProduk produk){
        this.berat = this.berat + produk.getBeratTambah();
    }

    public boolean isReadyToHarvest(){
        return this.berat >= Config.mapBeratPanen.get(this.nama);
    }

    public List<String> getInformasi(){
        List<String> informasi = new ArrayList<>();
        informasi.add(this.nama);
        int berat_harvest = Config.mapBeratPanen.get(this.nama);
        informasi.add("Berat: " + this.berat + " (" + berat_harvest + ")");
        //print map item aktif dengan format nama item (banyak item) dari item aktif
        String itemAktif = "Item aktif: ";
        for (Map.Entry<String, Integer> entry : this.itemAktif.entrySet()){
            if (entry.getValue() > 0){
                itemAktif += entry.getKey() + " (" + entry.getValue() + ") ";
            }
        }
        informasi.add(itemAktif);
        return informasi;
    }

    public List<String> getEfekItem(){
        List<String> efekItem = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : this.itemAktif.entrySet()){
            if (entry.getValue() > 0){
                for (int i = 0; i <entry.getValue() ; i++) {
                    efekItem.add(entry.getKey());
                }
            }
        }
        return efekItem;
    }

}
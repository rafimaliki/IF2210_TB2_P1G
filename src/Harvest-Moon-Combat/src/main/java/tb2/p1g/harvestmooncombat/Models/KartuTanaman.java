package tb2.p1g.harvestmooncombat.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KartuTanaman extends Kartu {

    private String nama;
    private int umur;
    private Map<String, Integer> itemAktif;

    public KartuTanaman(String namaTanaman){
        this.nama = namaTanaman;
        this.umur = 0;
        this.itemAktif = new HashMap<>(){{
            put("ACCELERATE", 0);
            put("DELAY", 0);
            put("PROTECT", 0);
            put("TRAP", 0);
        }};
    }

    public String getNama(){
        return this.nama;
    }

    public int getUmur(){
        return this.umur;
    }

    public Map<String, Integer> getItemAktif(){
        return this.itemAktif;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setUmur(int umur){
        this.umur = umur;
    }

    public boolean isReadyToHarvest(){
        return this.umur >= Config.mapUmurPanen.get(this.nama);
    }

    public void setEfekItem(KartuItem kartuItem) throws Exception {
        String namaItem = kartuItem.getNama();

        if ((namaItem.equals("ACCELERATE")) || (namaItem.equals("DELAY")) || (namaItem.equals("PROTECT")) || (namaItem.equals("TRAP"))){
            this.itemAktif.put(kartuItem.getNama(), this.itemAktif.get(kartuItem.getNama()) + 1);
        }

        if (namaItem.equals("ACCELERATE")){
            this.umur += 2;
        } else if (namaItem.equals("DELAY")){
            this.umur -= 2;
            if (this.umur < 0){
                this.umur = 0;
            }
        }
    }

    //buat next turn?
    public void grow(){
        this.umur = this.umur +1 + (itemAktif.get("ACCELERATE") * 2) - (itemAktif.get("DELAY") * 2);
        if (this.umur < 0) {
            this.umur = 0;
        }
    }

    public List<String> getInformasi(){
        List<String> informasi = new ArrayList<>();
        informasi.add(this.nama);
        int umurPanen = Config.mapUmurPanen.get(this.nama);
        informasi.add("Umur: " + this.umur + " (" + umurPanen + ")");

        String itemAktif = "Item aktif: ";
        for (Map.Entry<String, Integer> entry : this.itemAktif.entrySet()){
            if (entry.getValue() > 0){
                itemAktif += entry.getKey() + " (" + entry.getValue() + ") ";
            }
        }
        informasi.add(itemAktif);

        return informasi;
    }


    public List<String> getEfekItem() {
        List<String> efekItem = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : this.itemAktif.entrySet()){
            if (entry.getValue() > 0){
                efekItem.add(entry.getKey());
            }
        }
        return efekItem;
    }
}




package tb2.p1g.harvestmooncombat.Models;

import java.util.HashMap;
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

    public void setItemAktif(KartuItem kartuItem) throws Exception {
        String namaItem = kartuItem.getNama();

        if ((!namaItem.equals("ACCELERATE")) && (!namaItem.equals("DELAY")) && (!namaItem.equals("PROTECT"))){
            throw new Exception("Item tidak valid");
        }

        this.itemAktif.put(kartuItem.getNama(), this.itemAktif.get(kartuItem.getNama()) + 1);
    }

    public void grow(){
        this.umur++;
    }
}

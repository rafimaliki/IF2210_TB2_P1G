package tb2.p1g.harvestmooncombat.Models;

import java.util.Map;
import java.util.HashMap;

public class KartuHewan extends Kartu {

    private String nama;
    private int berat;
    private String type;
    private Map<String, Integer> itemAktif;

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
            this.berat -= 5;
            if (this.berat < 0){
                this.berat = 0;
            }
        }
    }
    //waktu next turn )?(
    public void tambahBerat(){
        this.berat = this.berat + (itemAktif.get("ACCELERATE") * 8) - (itemAktif.get("DELAY")*  5);
    }

    public boolean isReadyToHarvest(){
        return this.berat >= Config.mapBeratPanen.get(this.nama);
    }
}
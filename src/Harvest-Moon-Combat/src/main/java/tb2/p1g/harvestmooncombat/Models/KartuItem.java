package tb2.p1g.harvestmooncombat.Models;

import java.util.ArrayList;
import java.util.List;

public class KartuItem extends Kartu {
    private String nama;
    private String tipe;


    public  KartuItem(String nama ){
        this.nama = nama;
        this.tipe = Config.mapTipeKartu.get(this.nama);

    }

    public String getNama(){
        return this.nama;
    }
    public String getTipe(){return this.tipe;}

    public List<String> getInformasi(){
        List<String> informasi = new ArrayList<>();
        informasi.add(this.nama);
        informasi.add("Tipe: " + this.tipe);
        informasi.add("");

        return informasi;
    }
}

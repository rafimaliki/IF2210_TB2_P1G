package tb2.p1g.harvestmooncombat.Models;

import java.util.Map;
import java.util.HashMap;

public class KartuHewan implements Kartu {

    private String nama;
    private int berat;

    public KartuHewan(String namaHewan){
        this.nama = namaHewan;
        this.berat = 0;
    }

    public String getNama(){
        return  this.nama;
    }

    public int getBerat(){
        return  this.berat;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setBerat(int berat){
        this.berat = berat;
    }
}
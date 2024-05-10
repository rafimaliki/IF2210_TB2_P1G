package tb2.p1g.harvestmooncombat.Models;

public class KartuTanaman implements Kartu {

    private String nama;
    private int umur;

    public KartuTanaman(String namaTanaman){
        this.nama = namaTanaman;
        this.umur = 0;
    }

    public String getNama(){
        return this.nama;
    }

    public int getUmur(){
        return this.umur;
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

    public void grow(){
        this.umur++;
    }
}

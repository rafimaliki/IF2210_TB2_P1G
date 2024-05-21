package tb2.p1g.harvestmooncombat.Models;

public class KartuItem extends Kartu {
    private String nama;
    private  String tipe;


    public  KartuItem(String nama ){
        this.nama = nama;
        this.tipe = Config.mapTipeKartu.get(this.nama);

    }

    public String getNama(){
        return this.nama;
    }
    public String getTipe(){return this.tipe;}
    
}

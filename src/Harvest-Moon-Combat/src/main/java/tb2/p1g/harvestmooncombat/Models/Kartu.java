package tb2.p1g.harvestmooncombat.Models;

public abstract class Kartu {
    String nama;

    public String getNama(){
        return this.nama;
    }


    public void setEfekItem(KartuItem item) throws Exception {}
}
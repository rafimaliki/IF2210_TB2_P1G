package tb2.p1g.harvestmooncombat.Models;

public class TokoEntry {
    private KartuProduk kartu;
    private int kuantitas;

    public TokoEntry(KartuProduk kartu){
        this.kartu = kartu;
        this.kuantitas = 1;
    }

    public KartuProduk getKartu(){
        return kartu;
    }

    public int getKuantitas(){
        return kuantitas;
    }

    public void setKartu(KartuProduk kartu){
        this.kartu = kartu;
    }

    public void setKuantitas(int kuantitas){
        this.kuantitas = kuantitas;
    }
}

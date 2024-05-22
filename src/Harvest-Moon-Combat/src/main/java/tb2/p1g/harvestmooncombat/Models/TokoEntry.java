package tb2.p1g.harvestmooncombat.Models;

public class TokoEntry {
    private KartuProduk kartu;
    private int kuantitas;
    private int hargaSatuan;

    public TokoEntry(KartuProduk kartu){
        this.kartu = kartu;
        this.kuantitas = 1;
        this.hargaSatuan = Config.mapProduk.get(kartu.getNama()).getFirst();
    }

    public KartuProduk getKartu(){
        return this.kartu;
    }

    public int getKuantitas(){
        return this.kuantitas;
    }

    public int getHargaSatuan(){
        return this.hargaSatuan;
    }

    public void setKartu(KartuProduk kartu){
        this.kartu = kartu;
    }

    public void setKuantitas(int kuantitas){
        this.kuantitas = kuantitas;
    }

    public void setHargaSatuan(int hargaSatuan){
        this.hargaSatuan = hargaSatuan;
    }
}

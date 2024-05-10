package tb2.p1g.harvestmooncombat.Models;

public class Player {
    private String nama;
    private int gulden;
    private DeckAktif deckAktif;
    private DeckNonAktif deckNonAktif;
    private Ladang ladang;

    public Player(String nama){
        this.nama = nama;
        this.gulden = 0;
        this.deckAktif = new DeckAktif();
        this.deckNonAktif = new DeckNonAktif();
        this.ladang = new Ladang();
    }

    public String getNama(){
        return this.nama;
    }

    public int getGulden(){
        return this.gulden;
    }

    public DeckAktif getDeckAktif(){
        return this.deckAktif;
    }

    public DeckNonAktif getDeckNonAktif(){
        return this.deckNonAktif;
    }

    public Ladang getLadang(){
        return this.ladang;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setGulden(int gulden){
        this.gulden = gulden;
    }

    public void setDeckAktif(DeckAktif deckAktif){
        this.deckAktif = deckAktif;
    }

    public void setDeckNonAktif(DeckNonAktif deckNonAktif){
        this.deckNonAktif = deckNonAktif;
    }

    public void setLadang(Ladang ladang){
        this.ladang = ladang;
    }

    public void addGulden(int gulden){
        this.gulden += gulden;
    }

    public void reduceGulden(int gulden){
        this.gulden -= gulden;
    }
}

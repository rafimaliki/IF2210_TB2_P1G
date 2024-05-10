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
}

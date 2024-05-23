package tb2.p1g.harvestmooncombat.Models;

public class StateSave {
    private int turn;
    private Toko toko;

    public StateSave(int turn, Toko toko) {
        this.turn = turn;
        this.toko = toko;
    }

    public int getTurn() {
        return turn;
    }

    public Toko getToko() {
        return toko;
    }
}

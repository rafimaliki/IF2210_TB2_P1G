package tb2.p1g.harvestmooncombat.Models;

import java.util.List;
import java.util.ArrayList;

public class Deck {
    private List<Kartu> kartu;

    public Deck(int n){
        this.kartu = new ArrayList<>(n);
    }

    public void setKartu(Kartu kartu, int i){
        this.kartu.set(i, kartu);
    }

    public Kartu getKartu(int i){
        return this.kartu.get(i);
    }

    public void removeKartu(int i){
        this.kartu.remove(i);
    }

    public int getLengthKartu(){
        return this.kartu.size();
    }
}
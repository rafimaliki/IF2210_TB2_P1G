package tb2.p1g.harvestmooncombat.Models;

import java.util.List;
import java.util.ArrayList;

public class Deck {
    private List<Kartu> kartu;

    public Deck(){
        this.kartu = new ArrayList<>();
    }

    public Deck(int n){
        this.kartu = new ArrayList<>(n);
    }
}
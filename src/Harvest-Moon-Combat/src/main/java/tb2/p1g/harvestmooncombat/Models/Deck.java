package tb2.p1g.harvestmooncombat.Models;

import java.util.List;
import java.util.ArrayList;

public class Deck {
    private List<Kartu> kartu;

    public Deck(int n){
        this.kartu = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            this.kartu.add(null);
        }
    }
    public  void displayInfoDeck(){
        int i = 0;
        for (Kartu k : kartu){
            i++;
            if(k != null){
                System.out.println("Deck Aktif id ke - " + i + " berisi " + k.getNama());
            }
        }
    }

    public void setKartu(Kartu kartu, int i){
        this.kartu.set(i, kartu);
    }

    public Kartu getKartu(int i){
        return this.kartu.get(i);
    }

    public void removeKartu(int i){
        this.kartu.set(i, null);
    }

    public int getLengthKartu(){
        int count = 0;
        for(Kartu kartu : this.kartu){
            if(kartu != null){
                count++;
            }
        }
        return count;
    }

    public void setKartu(int index, Kartu kartu){
        this.kartu.set(index, kartu);
    }
}
package tb2.p1g.harvestmooncombat.Models;

import java.util.List;
import java.util.ArrayList;

public class Deck {
    protected List<Kartu> kartu;

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

    public void setKartu(List<Kartu> kartu){
        this.kartu = kartu;
    }

    public List<Kartu> getKartu(){
        return this.kartu;
    }

    public void tambahKartu(Kartu card) throws Exception{
        if (this.getLengthKartu() == 6){
            throw new Exception("Deck kamu full!");
        }

        for (int i = 0; i < this.kartu.size(); i++){
            if (this.kartu.get(i) == null){
                setKartu(card, i);
                break;
            }
        }
    }

    public void printDeck(){
        for(Kartu kartu : this.kartu){
            if(kartu != null){
                System.out.println(kartu.getNama());
            }
        }
    }

    public List<String> getKartuNames(){
        List<String> namaKartu = new ArrayList<>();

        for(Kartu kartu : this.kartu){
            if(kartu != null){
                namaKartu.add(kartu.getNama());
            } else {
                namaKartu.add("EMPTY");
            }
//            System.out.println("print");
        }

        return namaKartu;
    }

}
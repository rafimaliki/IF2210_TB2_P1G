package tb2.p1g.harvestmooncombat.Models;

public class DeckAktif extends Deck {
    public DeckAktif() {
        super(6);
    }

    public int getJumlahSlotKosong(){
        return 6 - super.getLengthKartu();
    }

    public boolean isFull(){
        return super.getLengthKartu() == 6;
    }

}

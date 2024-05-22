package tb2.p1g.harvestmooncombat.Exceptions;

import tb2.p1g.harvestmooncombat.Models.Kartu;

public class InvalidMoveExceptions extends Exception {
    private Kartu kartu;

    public InvalidMoveExceptions(String message, Kartu kartu) {
        super(message);
        this.kartu = kartu;
    }
    public InvalidMoveExceptions(String message){
        super(message);
        this.kartu = null;
    }

    public Kartu getKartu() {
        return kartu;
    }

}

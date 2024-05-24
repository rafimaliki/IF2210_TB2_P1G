package tb2.p1g.harvestmooncombat.Models;

public interface CardActionsInterface {
    void moveKartu(String idxInit, String idxDest) throws Exception;
    void undoKartu(Kartu k, String idxInit);
    void Panen(String idx) throws Exception;
    void Ambil(String idx) throws Exception;
}

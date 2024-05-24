package tb2.p1g.harvestmooncombat.Models;

public interface LadangManagementInterface {
    Ladang_Logic getLadang();
    void setLadang(Ladang_Logic ladang);
    void addKartuToLadang(Kartu kartu, int row, int col);
    void tumbuhkanTanaman();
    void displayLadangData();
}

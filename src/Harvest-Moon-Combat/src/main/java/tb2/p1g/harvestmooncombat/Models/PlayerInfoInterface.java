package tb2.p1g.harvestmooncombat.Models;

public interface PlayerInfoInterface {
    String getNama();
    void setNama(String nama);
    int getGulden();
    void setGulden(int gulden);
    void addGulden(int gulden);
    void reduceGulden(int gulden);
}

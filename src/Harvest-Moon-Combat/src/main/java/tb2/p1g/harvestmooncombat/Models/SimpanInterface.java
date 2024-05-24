package tb2.p1g.harvestmooncombat.Models;

public interface SimpanInterface {

    /**
     * Prosedur menulis state game ke file
     * @param stateSave state game yang ingin disimpan
     */
    public void writeGameState(StateSave stateSave);

    /**
     * Prosedur menulis state player ke file
     * @param player player yang ingin disimpan
     */
    public void writeSavePlayer(Player player);

}

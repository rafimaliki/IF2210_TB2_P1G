package tb2.p1g.harvestmooncombat.Plugins;

public interface MuatInterface {

    /**
     * @param path path (string) ke file yang ingin di-load
     * @return true jika file ada, false jika tidak
     */
    public boolean tryReadFile(String path);

    /**
     * prosedur memuat state game, yaitu current turn dan item di shop
     *
     * @param path path (string) ke file yang ingin di-load
     */
    public void loadGameState(String path);

    /**
     * prosedur memuat informasi player
     *
     * @param path path (string) ke file yang ingin di-load
     * @param player nomor player (int) yang ingin di-load
     */
    public void loadPlayer(String path, int player);

}

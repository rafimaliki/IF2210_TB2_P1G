package tb2.p1g.harvestmooncombat.Models;

public interface LadangManagementInterface {

    /**
     * Prosedur menambahkan kartu ke ladang
     * @param kartu kartu yang ingin ditambahkan
     * @param row indeks baris ladang
     * @param col indeks kolom ladang
     */
    void addKartuToLadang(Kartu kartu, int row, int col);

    /**
     * Prosedur menumbuhkan tanaman pada ladang
     */
    void tumbuhkanTanaman();

    /**
     * Prosedur menampilkan data ladang
     */
    void displayLadangData();

}

package tb2.p1g.harvestmooncombat.Models;

public interface CardActionsInterface {

    /**
     * Prosedur untuk memindahkan kartu dari satu tempat ke tempat lain
     * @param idxInit indeks kartu yang ingin dipindahkan
     * @param idxDest indeks tempat tujuan kartu
     * @throws Exception jika pemindahan tidak valid
     */
    void moveKartu(String idxInit, String idxDest) throws Exception;

    /**
     * Prosedur untuk membatalkan pemindahan kartu
     * @param k kartu yang ingin dibatalkan pemindahannya
     * @param idxInit indeks kartu yang ingin dibatalkan pemindahannya
     */
    void undoKartu(Kartu k, String idxInit);

    /**
     * Prosedur untuk memanen KartuTanaman pada ladang
     * @param idx indeks KartuTanaman yang ingin dipanen
     * @throws Exception jika kartu bukan KartuTanaman atau belum waktunya untuk panen
     */
    void Panen(String idx) throws Exception;

    /**
     * Prosedut untuk mengambil KartuProduk dari ladang
     * @param idx indeks KartuProduk yang ingin diambil
     * @throws Exception jika tidak valid untuk diambil
     */
    void Ambil(String idx) throws Exception;

}

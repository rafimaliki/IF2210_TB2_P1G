package tb2.p1g.harvestmooncombat.Models;

public interface TokoActionInterface {

    /**
     * Prosedur menjual kartu pada deck aktif
     * @param deckIdx index kartu pada deck aktif yang ingin dijual
     * @throws Exception jika kartu tidak ada atau bukan kartu produk
     */
    void jual(String deckIdx) throws Exception;

    /**
     * Prosedur membeli kartu produk
     * @param namaKartuProduk nama kartu produk yang ingin dibeli
     * @throws Exception jika kartu tidak ada atau uang tidak cukup
     */
    void beli(String namaKartuProduk) throws Exception;

}

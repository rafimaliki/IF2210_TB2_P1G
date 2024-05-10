package tb2.p1g.harvestmooncombat.Models;

import java.util.List;
import java.util.ArrayList;

public class Toko {
    public static List<TokoEntry> produkToko;

    public Toko() {}

    public TokoEntry getProdukToko(int idx) throws Exception {
        if ((idx >= produkToko.size()) || (idx < 0)){
            throw (new Exception("Indeks tidak valid!"));
        }

        return produkToko.get(idx);
    }

    public static void addProduk(KartuProduk produk){
        boolean succeed = false;

        for (TokoEntry currentProduk : produkToko){
            if (currentProduk.getKartu().getNama().equals(produk.getNama())) {
                int newKuantitas = currentProduk.getKuantitas();
                newKuantitas++;
                currentProduk.setKuantitas(newKuantitas);
                succeed = true;
                break;
            }
        }

        if (!succeed){
            TokoEntry newProduk = new TokoEntry(produk);
            produkToko.add(newProduk);
        }
    }

    public static KartuProduk subtractProduk(KartuProduk produk) throws Exception {
        boolean found = false;
        int removeIdx = -1, i = 0;
        KartuProduk kartu;

        for (TokoEntry currentProduk : produkToko){
            if (currentProduk.getKartu().getNama().equals(produk.getNama())){
                int newKuantitas = currentProduk.getKuantitas();
                newKuantitas--;
                if (newKuantitas == 0){
                    removeIdx = i;
                }
                currentProduk.setKuantitas(newKuantitas);
                found = true;
                break;
            }
            i++;
        }


        if (!found){
            throw (new Exception("Produk tidak ditemukan!"));
        } else {
            kartu = new KartuProduk(produkToko.get(i).getKartu());

            if (removeIdx == -1){
                produkToko.remove(removeIdx);
            }

            return kartu;
        }
    }
}

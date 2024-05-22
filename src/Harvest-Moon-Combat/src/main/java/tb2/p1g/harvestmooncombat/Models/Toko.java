package tb2.p1g.harvestmooncombat.Models;

import java.util.List;
import java.util.ArrayList;

public class Toko {
    public static List<TokoEntry> produkToko = new ArrayList<>();

    public Toko() {}

    public static void displayToko(){
        for(TokoEntry currentProduk : produkToko){
            if(currentProduk != null){
                System.out.println(currentProduk.getKartu().getNama());
                System.out.println(currentProduk.getKuantitas());
                System.out.println(currentProduk.getHargaSatuan());

            }

        }
    }

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

    public static KartuProduk subtractProduk(String namaProduk) throws Exception {
        boolean found = false;
        int removeIdx = -1, i = 0;

        for (TokoEntry currentProduk : produkToko){
            if (currentProduk.getKartu().getNama().equals(namaProduk)){
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
            KartuProduk kartu = new KartuProduk(namaProduk);

            if (removeIdx != -1){
                produkToko.remove(removeIdx);
            }

            return kartu;
        }
    }

    public static KartuProduk subtractByIndex(int idx) throws Exception {
        if ((idx < 0) || (idx >= produkToko.size())){
            throw (new Exception("Indeks tidak valid"));
        }

        TokoEntry currentProduk = produkToko.get(idx);
        int newKuantitas = currentProduk.getKuantitas();
        newKuantitas--;
        KartuProduk kartu = new KartuProduk(currentProduk.getKartu());

        if (newKuantitas == 0){
            produkToko.remove(idx);
        }

        return kartu;
    }

    public static int getHargaProduk(String namaProduk) throws Exception {
        for (TokoEntry currentProduk : produkToko){
            if (currentProduk.getKartu().getNama().equals(namaProduk)){
                return currentProduk.getHargaSatuan();
            }
        }

        throw new Exception("Produk tidak tersedia di toko ini");
    }
}

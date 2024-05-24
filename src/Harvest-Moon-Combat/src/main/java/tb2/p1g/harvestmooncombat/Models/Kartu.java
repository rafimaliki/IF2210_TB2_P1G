package tb2.p1g.harvestmooncombat.Models;

import java.util.ArrayList;
import java.util.List;

public abstract class Kartu {
    String nama;

    public String getNama(){
        return this.nama;
    }
    public void setNama(String nama){this.nama = nama;}

    public List<String> getInformasi() {
        return new ArrayList<>();
    }
}

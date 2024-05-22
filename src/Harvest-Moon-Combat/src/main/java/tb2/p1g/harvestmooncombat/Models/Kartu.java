package tb2.p1g.harvestmooncombat.Models;

import java.util.ArrayList;
import java.util.List;

public abstract class Kartu {
    String nama;

    public String getNama(){
        return this.nama;
    }


    public void setEfekItem(KartuItem item) throws Exception {}

    public boolean isReadyToHarvest(){
        return false;
    }

    public List<String> getInformasi(){
        return new ArrayList<>();
    }

    public List<String> getEfekItem(){
        return new ArrayList<>();
    }

 }
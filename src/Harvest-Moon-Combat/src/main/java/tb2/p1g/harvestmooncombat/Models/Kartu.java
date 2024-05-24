package tb2.p1g.harvestmooncombat.Models;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.List;

public abstract class Kartu {
    String nama;

    public String getNama(){
        return this.nama;
    }
    public void setNama(String nama){this.nama = nama;}

    public abstract List<String> getInformasi();
}

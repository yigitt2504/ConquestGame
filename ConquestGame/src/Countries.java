import java.util.ArrayList;
import java.util.List;

public class Countries {
    String country_name;
    List<Provinces> provincesOwned;

    public Countries(String country_name){
        this.country_name = country_name;
        this.provincesOwned = new ArrayList<>();
    }

    public void addProvince(Provinces provinces){
        this.provincesOwned.add(provinces);
    }

    public void removeProvince(Provinces provinces){
        this.provincesOwned.remove(provinces);
    }

    public String getName() {
        return this.country_name;
    }

    public List<Provinces> getProvincesOwned() {
        return provincesOwned;
    }

}

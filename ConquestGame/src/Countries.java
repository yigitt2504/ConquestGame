import java.util.ArrayList;
import java.util.List;

public class Countries {
    String country_name;
    List<Provinces> provincesOwned;
    int gold;
    int income;

    public Countries(String country_name){
        this.country_name = country_name;
        this.provincesOwned = new ArrayList<>();
        this.gold = 1000;
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

    public int getGold(){
        return gold;
    }

    public void addGold(int amount){
        this.gold += amount;
    }

    public boolean spendGold(int amount){
        if(gold >= amount){
            gold -= amount;
            return true;
        }
        else{
            System.out.println("=====================================================\n");
            System.out.println("You don't have enough gold! ");
            System.out.println("=====================================================\n");
            return false;
        }
    }

    public void generateIncome(){
        System.out.println("=====================================================\n");
        
        if (provincesOwned == null || provincesOwned.isEmpty()) {
            System.out.println(this.country_name + " no longer exists.");
            return;
        }

        this.income = provincesOwned.size() * 100;
        this.gold += income;
        System.out.println(this.country_name + " earned " + income + " gold. Current balance is: " + this.gold);
    }

    public int getIncome(){
        return income;
    }

}

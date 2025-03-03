import java.util.*;

public class Provinces {
    String province_name;
    int troops;
    Countries owner;
    List<Provinces> neighbors;

    public Provinces(String province_name, int troops, Countries owner){
        this.province_name = province_name;
        this.troops = troops;
        this.owner = owner;
        this.neighbors = new ArrayList<>();
    }

    public String getProvinceName(){
        return province_name;
    }

    public List<Provinces> getNeighbors(){
        return neighbors;
    }

    public void addNeighbor(Provinces neighbor){
        if(!neighbors.contains(neighbor)){
            neighbors.add(neighbor);
            neighbor.getNeighbors().add(this);
        }
    }

    public void trainingTroops(int troops){
        int cost = troops*100;
        if(owner.spendGold(cost)){
            troops += troops;
            System.out.println("=====================================================\n");
            System.out.println("Troops trained in " + this.province_name + ", " + owner.getName() + " has " + owner.getGold() + " gold left. ");
        }
    }

    public String attacking(Provinces attacked_province, int attackingTroops){
        if (attackingTroops >attacked_province.troops) {
            this.troops -= attackingTroops;
            attacked_province.troops = 0;
    
            Iterator<Provinces> iterator = attacked_province.owner.getProvincesOwned().iterator();
            while(iterator.hasNext()){
                if(iterator.next().equals(attacked_province)){
                    iterator.remove();
                    break;
                }
            }

            attacked_province.owner.getProvincesOwned().remove(attacked_province);
            
            attacked_province.owner = this.owner;
            this.owner.addProvince(attacked_province);
    
            return "The province of " + attacked_province.getProvinceName() + " is now conquered!";
        }

        else{
            attacked_province.troops -= attackingTroops;
            this.troops -= attackingTroops;
            return "The defenders have held their ground and won the battle!";
        }

    }
}

import java.util.ArrayList;
import java.util.List;

public class Provinces {
    String province_name;
    int troops;
    int gold;
    Countries owner;

    public Provinces(String province_name, int troops, Countries owner){
        this.province_name = province_name;
        this.troops = troops;
        this.owner = owner;
    }

    public String getProvinceName(){
        return province_name;
    }

    public void trainingTroops(int troops){
        this.troops += troops;
        this.gold -= troops * 100;
    }

    public String attacking(Provinces attacked_province, int troops){
        if(troops>attacked_province.troops){
            this.owner.removeProvince(attacked_province);
            attacked_province.owner = this.owner;
            this.owner.addProvince(attacked_province);
            return("The province is conquered!");
        }
        else{
            return("The defenders have succeded!");
        }
    }

}





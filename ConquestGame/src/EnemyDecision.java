import java.util.*;

public class EnemyDecision {
    
    public static void displaySituation(List<Countries> countriesList) {
        System.out.println("\n===== Displaying the Situation Around the World =====");
        for(Countries country : countriesList){
            System.out.print(country.getName() + " Gold: " + country.getGold());
            for(Provinces provinces : country.getProvincesOwned()){
                System.out.print("\n- " + provinces.getProvinceName() + " Troops: " + provinces.troops);
            }
            System.out.println();
        }
    System.out.println("=====================================================\n");
    }

    public static void displayNeighbors(List<Countries> countriesList){
        System.out.println("=====================================================\n");
        for(Countries country : countriesList){
            for(Provinces provinces: country.getProvincesOwned()){
                System.out.print("\n- " + provinces.getProvinceName() + " | Neighbors: ");
                for(Provinces neighbor : provinces.getNeighbors()){
                    System.out.print(neighbor.getProvinceName() + " ");
                }
                System.out.println();
            }
        }
    }

    public static void enemyMovement(List<Countries> countriesList, Countries country, String playerCountryName){
        if(country == null || country.getName().equalsIgnoreCase(playerCountryName)) return;

        for(Provinces provinces : country.getProvincesOwned()){
            Random random = new Random();
            int random_number = random.nextInt(2);

            if(random_number == 0){
                int troopsToTrain = country.getGold() / 100;
                if(troopsToTrain > 0){
                    provinces.trainingTroops(troopsToTrain);
                    System.out.println(country.getName() + " reinforced " + provinces.getProvinceName() + " with " + troopsToTrain + " troops. ");
                }
            }
            else{
                for(Provinces neighbor : provinces.getNeighbors()){
                    if(!neighbor.owner.getName().equalsIgnoreCase(country.getName()) && provinces.troops > neighbor.troops){
                        int attackingTroops = provinces.troops / 2;
                        String result = provinces.attacking(neighbor, attackingTroops);
                        System.out.println(country.getName() + " attacked " + neighbor.getProvinceName() + " from " + provinces.getProvinceName() + "! ");
                        System.out.println(result);
                        break;
                    }
                }
            }
        }
    }
}

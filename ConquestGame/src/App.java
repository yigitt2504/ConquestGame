import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Countries england = new Countries("England");
        Countries france = new Countries("France");

        Provinces london = new Provinces("London", 1000, england);
        Provinces paris = new Provinces("Paris", 1000, france);
        Provinces york = new Provinces("York", 1000, england);


        england.addProvince(york);
        england.addProvince(london);
        france.addProvince(paris);

        List<Countries> countriesList = new ArrayList<>();
        countriesList.add(england);
        countriesList.add(france);

        Scanner selecting_country = new Scanner(System.in);
        System.out.println("Which country do you want to play as?");
        String selected_country = selecting_country.nextLine();
        
        boolean country_found = false;
        for(Countries country : countriesList){
            if(selected_country.equalsIgnoreCase(country.getName())){
                System.out.println("You are now in charge of: " + country.getName());

                System.out.println(country.getName() + " is in control of these provinces: ");
                for(Provinces p : country.getProvincesOwned()){
                    System.out.println(p.getProvinceName());
                }
                country_found = true;
                break;
            }
        }
        if(!country_found){
            System.out.println("This country doesn't exist, please try again!");
        }

        selecting_country.close();
        



    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Countries england = new Countries("England");
        Countries france = new Countries("France");


        Provinces london = new Provinces("London", 1000, england);
        Provinces paris = new Provinces("Paris", 1000, france);
        Provinces york = new Provinces("York", 1000, england);


        england.addProvince(york);
        england.addProvince(london);
        france.addProvince(paris);


        london.addNeighbor(york);
        paris.addNeighbor(london);

        //Add a mechanic to see which province neighbors which.

        List<Countries> countriesList = new ArrayList<>();
        countriesList.add(england);
        countriesList.add(france);

        Scanner selecting_country = new Scanner(System.in);
        System.out.println("Which country do you want to play as?");
        String selected_country = selecting_country.nextLine();
        
        boolean country_found = false;
        for(Countries country : countriesList){
            if(selected_country.equalsIgnoreCase(country.getName())){
                System.out.println("You are now in charge of " + country.getName());

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
            selecting_country.close();
        }
        
        boolean gameContinues = true;
        int turn = 0;
        while(gameContinues){
            System.out.println("Turn " + turn);
            System.out.println("1 to view your provinces. ");
            System.out.println("2 to view the situation in all nations. ");
            System.out.println("3 to train more troops. ");
            System.out.println("4 to attack. ");
            System.out.println("5 to end turn. ");
            System.out.println("'End' to end the game. ");
            System.out.println("Choose your action. ");
            
            String players_input = scanner.nextLine();

            if(players_input.equalsIgnoreCase("End")){
                gameContinues = false;
                System.out.println("Player ended the game! ");
                break;
            }

            try{
                int players_choice = Integer.parseInt(players_input);
                switch(players_choice){
                    case 1:
                        System.out.println(selected_country + " now owns these provinces: "  );
                        for(Countries country : countriesList){
                            if(selected_country.equalsIgnoreCase(country.getName())){
                                for(Provinces province : country.getProvincesOwned()){
                                    System.out.println("- " + province.getProvinceName());
                                }
                                break;
                            }
                        }
                        break;

                    case 2:
                        System.out.println("Situation in all nations: ");
                        for(Countries country : countriesList){
                            System.out.println(country.getName() + " now controls: ");
                            for(Provinces province : country.getProvincesOwned()){
                                System.out.println("- " + province.getProvinceName());
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Choose which province do you want to train your troops in? ");
                        String trainingProvince = scanner.nextLine();
                        System.out.println("How many troops do you want to train? ");
                        int troopsToTrain = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();

                        Provinces trainingTarget = null;
                        Countries playerCountry = null;

                        for(Countries country : countriesList){
                            for(Provinces province : country.getProvincesOwned()){
                                if(province.getProvinceName().equalsIgnoreCase(trainingProvince)){
                                    trainingTarget = province;
                                    playerCountry = country;
                                    break;
                                }
                            }
                        }

                        if(trainingTarget != null && playerCountry != null){
                            trainingTarget.trainingTroops(troopsToTrain);
                        }
                        else{
                            System.out.println("Invalid province! ");
                        }
                        break;

                    case 4:
                        System.out.println("");

                        //The player needs to choose a friendly province and number of troops and then an enemy province to attack.
                        break;

                    case 5:
                        System.out.println("You ended turn " + turn + " now AI will make it's move. ");
                        for(Countries country : countriesList){
                            country.generateIncome();
                            System.out.println(country.country_name + " earned " + country.income + " gold. Your new balance is: " + country.gold);
                        }

                        //Implement enemies' moves here.

                        turn ++;
                        System.out.println("Now it's turn " + turn + "!");

                        System.out.println();
                        break;

                    default:
                        System.out.println("Invalid option, try again! ");
                        break;
                    }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'End' to quit.");
            }
    }
    scanner.close();
}
}

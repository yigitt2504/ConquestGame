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

        //Add a mechanic to see the provinces' neighbors at will.

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
        int turn = 1;
        while(gameContinues){
            System.out.println("Turn " + turn);
            System.out.println("Choose your action. ");
            System.out.println("1 to view your provinces. ");
            System.out.println("2 to view the situation in all nations. ");
            System.out.println("3 to train more troops. ");
            System.out.println("4 to attack. ");
            System.out.println("5 to move your troops across your provinces. ");
            System.out.println("6 to end turn. ");
            System.out.println("'End' to end the game. ");
            for(Countries country : countriesList){
                if(selected_country.equalsIgnoreCase(country.getName())){
                    System.out.println("Your gold: " + country.getGold());
                    if(turn>1){
                        System.out.println("Your income: " + country.getIncome());
                    }
                }
            }
            
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
                        System.out.println("Choose a friendly province to attack from. ");
                        String attackingProvinceName = scanner.nextLine();

                        Provinces attackingProvince = null;

                        for(Countries country: countriesList){
                            for(Provinces provinces : country.getProvincesOwned()){
                                if(provinces.getProvinceName().equalsIgnoreCase(attackingProvinceName) && country.getName().equalsIgnoreCase(selected_country)){
                                    attackingProvince = provinces;
                                    break;
                                }
                            }
                        }

                        if(attackingProvince == null){
                            System.out.println("The province you're trying to attack is invalid! ");
                            break;
                        }

                        System.out.println("How many troops do you want to send? ");
                        int attackingTroops = scanner.nextInt();
                        scanner.nextLine();

                        if(attackingTroops <= 0 || attackingTroops > attackingProvince.troops){
                            System.out.println("Invalid troop number! ");
                        }

                        System.out.println("Choose an enemy province to attack: ");
                        String attackedProvinceName = scanner.nextLine();

                        Provinces attackedProvince = null;
                        for(Provinces neighbor : attackingProvince.getNeighbors()){
                            if(neighbor.getProvinceName().equalsIgnoreCase(attackedProvinceName) && !neighbor.owner.getName().equalsIgnoreCase(selected_country)){
                                attackedProvince = neighbor;
                                break;
                            }
                        }

                        if(attackedProvince == null){
                            System.out.println("Invalid enemy province! ");
                        }
                        
                        String battleResult = attackingProvince.attacking(attackedProvince, attackingTroops);
                        System.out.println(battleResult);
                        break;

                    case 5: 
                        System.out.println("Which province do you want to march your troops from? ");
                        String movingFromProvinceName = scanner.nextLine();

                        Provinces movingFromProvince = null;
                        for(Countries country: countriesList){
                            if(country.getName().equalsIgnoreCase(selected_country)){
                                for(Provinces provinces : country.getProvincesOwned()){
                                    if(provinces.getProvinceName().equalsIgnoreCase(movingFromProvinceName)){
                                        movingFromProvince = provinces;
                                        break;
                                    }
                                }
                            }
                        }

                        if(movingFromProvince == null){
                            System.out.println("Invalid province! ");
                            break;
                        }

                        System.out.println("How many troops are you ordering to march from " + movingFromProvinceName + "? ");
                        int troopsToMarch = scanner.nextInt();
                        scanner.nextLine();

                        if(troopsToMarch <= 0 && troopsToMarch < movingFromProvince.troops){
                            System.out.println("Invalid amount of troops! ");
                            break;
                        }

                        System.out.println("Where do you want to send the soldiers to? ");
                        String movingToProvinceName = scanner.nextLine();

                        Provinces movingToProvince = null;
                        for(Provinces neighbor : movingFromProvince.getNeighbors()){
                            if(neighbor.getProvinceName().equalsIgnoreCase(movingToProvinceName) && neighbor.owner.getName().equalsIgnoreCase(selected_country)){
                                movingToProvince = neighbor;
                                break;
                            }
                        }

                        if(movingToProvince == null){
                            System.out.println("Invalid province! You can only march your troops to a neighboring province that you own. ");
                            System.out.println("You are either risking a clash with the enemy or you are trying to move your troops to a far away land. ");
                            break;
                        }

                        movingFromProvince.troops -= troopsToMarch;
                        movingToProvince.troops += troopsToMarch;

                        System.out.println("Your troops have marched " + movingFromProvince.getProvinceName() + " to " + movingToProvince.getProvinceName());

                        break;

                    case 6:
                    //_____________________________________________________________________________________________
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
//________________________________________________________________________
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

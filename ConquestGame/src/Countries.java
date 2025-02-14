import java.util.ArrayList;
import java.util.List;

public class Countries {
    List<String> Europe = new ArrayList<>();
    List<String> Asia = new ArrayList<>();
    List<String> NorthAmerica = new ArrayList<>();
    List<String> SouthAmerica = new ArrayList<>();

    public static void main(String[] args) {
        Countries europe = new Countries();
        Countries asia = new Countries();
        Countries northAmerica = new Countries();
        Countries southAmerica = new Countries();

        europe.Europe.add("Portugal");
        europe.Europe.add("Spain");
        europe.Europe.add("Italy");
        europe.Europe.add("France");
        europe.Europe.add("United Kingdom");
        europe.Europe.add("Ireland");

        asia.Asia.add("Japan");
        asia.Asia.add("South Korea");
        asia.Asia.add("North Korea");
        asia.Asia.add("China");
        asia.Asia.add("Taiwan");
        asia.Asia.add("Vietnam");

        northAmerica.NorthAmerica.add("USA");
        northAmerica.NorthAmerica.add("Canada");
        northAmerica.NorthAmerica.add("Mexico");
        northAmerica.NorthAmerica.add("Guatemala");
        northAmerica.NorthAmerica.add("Cuba");
        northAmerica.NorthAmerica.add("El Salvador");

        southAmerica.SouthAmerica.add("Brazil");
        southAmerica.SouthAmerica.add("Argentina");
        southAmerica.SouthAmerica.add("Uruguay");
        southAmerica.SouthAmerica.add("Colombia");
        southAmerica.SouthAmerica.add("Peru");
        southAmerica.SouthAmerica.add("Chile");
        
    }
}

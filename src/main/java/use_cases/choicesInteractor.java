package use_cases;

import au.com.bytecode.opencsv;
import entities.Restaurant;

public class choicesInteractor {
    private ArrayList<Restaurant> restaurant_list;

    // Access csv database using Gateway and retrieve filtered list
    String restaurants_file = "whereveritis.csv"; // restaurant csv location

    // Options:
    // 1. BufferedReader try(BufferedReader reader = new BufferedReader(new FileReader(restaurants_file)))
    // 2. OpenCSV? Need to be able to add dependency to GitHub
}







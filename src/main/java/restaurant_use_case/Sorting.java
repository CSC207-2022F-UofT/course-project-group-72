package restaurant_use_case;

import entities.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

abstract class Sorting {
    // ArrayList<Restaurant> sortedRestaurants = new ArrayList<>();
    public abstract void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection);

}

class sortName extends Sorting {
    public void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection) {
        Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getName));
    }
}

class sortPrice extends Sorting {
    public void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection) {
        Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getPriceBucket,
                Comparator.reverseOrder()));
    }
}

class sortAvgStars extends Sorting {
    public void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection) {
        Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getAvgStars));
    }
}

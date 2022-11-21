package restaurant_use_case;

import entities.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

abstract class Sorting {
    // ArrayList<Restaurant> sortedRestaurants = new ArrayList<>();
    public abstract void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection);

}

class sortName extends Sorting {
    public void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection) {
        if (Objects.equals(sortDirection, "Ascending")){
            Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getName));
        }

        if (Objects.equals(sortDirection, "Descending")){
            Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getName, Comparator.reverseOrder()));
    }
}

class sortPrice extends Sorting {
    public void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection) {
        if (Objects.equals(sortDirection, "Ascending")){
            Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getPriceBucket));
        }

        if (Objects.equals(sortDirection, "Descending")){
            Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getPriceBucket,
                    Comparator.reverseOrder()));
        }
    }

    }
}

class sortAvgStars extends Sorting {
    public void sortList(ArrayList<Restaurant> sortedRestaurants, String sortDirection) {
        if (Objects.equals(sortDirection, "Ascending")) {
            Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getAvgStars));
        }

        if (Objects.equals(sortDirection, "Descending")) {
            Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getAvgStars,
                    Comparator.reverseOrder()));
        }
    }
}

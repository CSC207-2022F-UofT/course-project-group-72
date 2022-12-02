package filtering_use_case.interactors;

import entities.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

class sortPrice extends Sorting {
    @Override
    public void sortList(ArrayList<Restaurant> sortedRestaurants, String sortMethod, String sortDirection) {
        if (Objects.equals(sortDirection, "Ascending")) {
            Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getPriceBucket));
        }

        if (Objects.equals(sortDirection, "Descending")) {
            Collections.sort(sortedRestaurants, Comparator.comparing(Restaurant::getPriceBucket,
                    Comparator.reverseOrder()));
        }
    }
}
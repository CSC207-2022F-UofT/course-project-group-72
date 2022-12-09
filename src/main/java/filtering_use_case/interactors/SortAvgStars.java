package filtering_use_case.interactors;

import entities.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * The Sorting Class for sorting by AvgStars (Rating)
 */
public class SortAvgStars extends Sorting {
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
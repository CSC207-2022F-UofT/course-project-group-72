package filtering_use_case.interactors;

import entities.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

abstract class Sorting {

    public abstract void sortList(ArrayList<Restaurant> sortedRestaurants, String sortMethod, String sortDirection);

}


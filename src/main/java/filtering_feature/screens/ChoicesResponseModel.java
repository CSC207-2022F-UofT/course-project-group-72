package filtering_feature.screens;

import entities.Restaurant;

import java.util.ArrayList;

/**
 * The screen data that is required to update the screen
 */
public class ChoicesResponseModel {

    ArrayList<Restaurant> restaurantList;

    public ChoicesResponseModel(ArrayList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    // getter method for the restaurants list
    public ArrayList<Restaurant> getRestaurants() {
        return restaurantList;
    }


}
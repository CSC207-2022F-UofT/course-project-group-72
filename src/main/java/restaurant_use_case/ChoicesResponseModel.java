package restaurant_use_case;

import entities.Restaurant;

import java.util.ArrayList;

public class ChoicesResponseModel {

    ArrayList<Restaurant> restaurantList;

    public ChoicesResponseModel(ArrayList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurantList;
    }

}

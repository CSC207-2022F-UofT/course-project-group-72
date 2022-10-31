package entities;

import java.util.ArrayList;

public class OwnerUser extends User{
    private ArrayList<Restaurant> ownedRestaurants = new ArrayList<>();

    public void addRestaurant(Restaurant restaurant) {
        this.ownedRestaurants.add(restaurant);
    }
}

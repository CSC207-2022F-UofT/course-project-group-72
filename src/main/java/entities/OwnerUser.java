package entities;

import java.util.ArrayList;

public class OwnerUser extends User{
    private ArrayList<Restaurant> ownedRestaurants = new ArrayList<>();

    public OwnerUser(String username, String password) {
        super(username, password);
    }

    public void addRestaurant(Restaurant restaurant) {
        this.ownedRestaurants.add(restaurant);
    }

    public ArrayList<Restaurant> getOwnedRestaurants() {
        return this.ownedRestaurants;
    }

}

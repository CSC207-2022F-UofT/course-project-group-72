package restaurant_use_case;

import entities.OwnerUser;
import entities.Restaurant;

public class RestaurantDeleteRequestModel {
    private final OwnerUser owner;
    private final Restaurant restaurant;

    public RestaurantDeleteRequestModel( OwnerUser owner, Restaurant restaurant) {
        this.owner = owner;
        this.restaurant = restaurant;
    }

    public OwnerUser getOwner() {
        return owner;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}

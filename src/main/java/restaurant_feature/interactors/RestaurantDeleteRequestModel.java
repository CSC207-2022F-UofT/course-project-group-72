package restaurant_feature.interactors;

import entities.OwnerUser;
import entities.Restaurant;

/**
 * Object containing the owner and the Restaurant impacted by the change
 */
public class RestaurantDeleteRequestModel {
    /**
     * the current User which must be an OwnerUser
     */
    private final OwnerUser owner;
    /**
     * the requested to be deleted Restaurant
     */
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

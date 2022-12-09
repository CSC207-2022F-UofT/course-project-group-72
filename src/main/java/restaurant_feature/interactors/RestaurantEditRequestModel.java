package restaurant_feature.interactors;

import entities.OwnerUser;
import entities.Restaurant;

/**
 * The EditRequestModel contains the additional information of the current unchanged Restaurant
 */
public class RestaurantEditRequestModel extends RestaurantRequestModel{
    /**
     * The unchanged to be changed Restaurant
     */
    private final Restaurant restaurant;

    public RestaurantEditRequestModel(OwnerUser owner, String name, String location, String cuisineType, int priceBucket,
                                      Restaurant changedRestaurant) {
        super(owner, name, location, cuisineType, priceBucket);
        this.restaurant = changedRestaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}

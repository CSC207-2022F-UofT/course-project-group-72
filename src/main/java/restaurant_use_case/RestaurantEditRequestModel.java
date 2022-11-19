package restaurant_use_case;

import entities.OwnerUser;
import entities.Restaurant;

public class RestaurantEditRequestModel extends RestaurantRequestModel{
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

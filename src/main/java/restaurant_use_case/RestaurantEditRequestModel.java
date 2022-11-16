package restaurant_use_case;

import entities.Restaurant;

public class RestaurantEditRequestModel extends RestaurantRequestModel{
    private final Restaurant restaurant;

    public RestaurantEditRequestModel(String ownerID, String name, String location, String cuisineType, int priceBucket,
                                      Restaurant changedRestaurant) {
        super(ownerID, name, location, cuisineType, priceBucket);
        this.restaurant = changedRestaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}

package entities;

import java.util.ArrayList;

public class RestaurantFactory {

    public RestaurantFactory() {}
    public Restaurant create(String ownerID, String name, String location, String cuisineType, int priceBucket) {
        return new Restaurant(ownerID, name, location, cuisineType, priceBucket);
    }
    // NOTE Type of ArrayList<T> may be revered to Review and ownerID to OwnerUser
    public Restaurant reinitialize(String ownerID, String name, String location, String cuisineType, int priceBucket,
                                   double avgStars, ArrayList<String> reviews) {
        return new Restaurant(ownerID, name, location, cuisineType, priceBucket, avgStars, reviews);
    }
}

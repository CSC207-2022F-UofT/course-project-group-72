package entities;

import java.util.ArrayList;

public class RestaurantFactory {

    public RestaurantFactory() {}
    public Restaurant create(OwnerUser owner, String name, String location, String cuisineType, int priceBucket) {
        return new Restaurant(owner, name, location, cuisineType, priceBucket);
    }

    public Restaurant reintialize(OwnerUser owner, String name, String location, String cuisineType, int priceBucket,
                                  double avgStars, ArrayList<Review> reviews) {
        return new Restaurant(owner, name, location, cuisineType, priceBucket);
    }
}

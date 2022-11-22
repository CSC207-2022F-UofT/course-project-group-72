package restaurant_use_case.interactors;

import entities.OwnerUser;
import entities.Review;

import java.util.ArrayList;

/**
 * The Request model for modifying Restaurants, containing all the possible changed or new information
 */
public class RestaurantRequestModel {
    //TODO Possibly use RestaurantAttributes to contain the data we need to pass
    private final String name;
    private final String location;
    private final String cuisineType;
    private final int priceBucket;
    private final OwnerUser owner;

    public RestaurantRequestModel(OwnerUser owner, String name, String location,
                                  String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.owner = owner;
        this.cuisineType = cuisineType;
        this.priceBucket = priceBucket;
    }

    // Retrieve Information
    String getName() {return this.name;}
    String getLocation() {return this.location;}
    String getCuisineType() {return this.cuisineType;}
    int getPriceBucket() {return this.priceBucket;}
    OwnerUser getOwner() {return this.owner;}
}

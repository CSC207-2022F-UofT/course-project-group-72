package restaurant_use_case;

import entities.OwnerUser;
import entities.Review;

import java.util.ArrayList;

public class RestaurantRequestModel {
    //TODO Possibly use RestaurantAttributes to contain the data we need to pass
    private String name;
    private String location;
    private String cuisineType;
    private int priceBucket;
    private String ownerID;

    public RestaurantRequestModel(String ownerID, String name, String location,
                                  String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.ownerID = ownerID;
        this.cuisineType = cuisineType;
        this.priceBucket = priceBucket;
    }

    // Retrieve Information
    String getName() {return this.name;}
    String getLocation() {return this.location;}
    String getCuisineType() {return this.cuisineType;}
    int getPriceBucket() {return this.priceBucket;}
    String getOwnerID() {return this.ownerID;}
}

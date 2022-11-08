package restaurant_use_case;

import entities.OwnerUser;

public class RestaurantDSRequestModel {
    private String name;
    private String location;
    private String cuisineType;
    private int priceBucket;
    private String owner;

    public RestaurantDSRequestModel(String owner, String name, String location,
                                  String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.owner = owner;
        this.cuisineType = cuisineType;
        this.priceBucket= priceBucket;
    }

    // Retrieve Information
    String getName() {return this.name;}
    String getLocation() {return this.location;}
    String getCuisineType() {return this.cuisineType;}
    int getPriceBucket() {return this.priceBucket;}
    String getOwner() {return this.owner;}
}

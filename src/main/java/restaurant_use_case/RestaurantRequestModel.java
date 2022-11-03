package restaurant_use_case;

import entities.OwnerUser;

public class RestaurantRequestModel {
    //TODO Possibly use RestaurantAttributes to contain the data we need to pass
    private String name;
    private String location;
    private String cuisineType;
    private int priceBucket;
    private OwnerUser owner;

    public RestaurantRequestModel(OwnerUser owner, String name, String location,
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
    OwnerUser getOwner() {return this.owner;}
}

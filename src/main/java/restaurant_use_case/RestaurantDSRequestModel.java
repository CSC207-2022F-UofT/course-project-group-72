package restaurant_use_case;

import entities.OwnerUser;

import java.util.ArrayList;

public class RestaurantDSRequestModel {
    private String name;
    private String location;
    private String cuisineType;
    private int priceBucket;
    private String owner;
    private int avgStars;
    private ArrayList<String> reviews;

    public RestaurantDSRequestModel(String owner, String name, String location,
                                  String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.owner = owner;
        this.cuisineType = cuisineType;
        this.priceBucket= priceBucket;
        this.avgStars = 0;
        this.reviews = new ArrayList<>();
    }

    public RestaurantDSRequestModel(String owner, String name, String location,
                                    String cuisineType, int priceBucket, int avgStars,
                                    ArrayList<String> reviews) {
        this.name = name;
        this.location = location;
        this.owner = owner;
        this.cuisineType = cuisineType;
        this.priceBucket= priceBucket;
        this.avgStars = avgStars;
        this.reviews = reviews;
    }

    // Retrieve Information
    String getName() {return this.name;}
    String getLocation() {return this.location;}
    String getCuisineType() {return this.cuisineType;}
    int getPriceBucket() {return this.priceBucket;}
    String getOwner() {return this.owner;}
    int getAvgStars() {return this.avgStars;}
    ArrayList<String> getReviews() {return this.reviews;}
}

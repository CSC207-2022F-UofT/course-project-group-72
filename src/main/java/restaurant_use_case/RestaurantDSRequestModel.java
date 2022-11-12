package restaurant_use_case;

import java.util.ArrayList;

public class RestaurantDSRequestModel {
    private String name;
    private String location;
    private String cuisineType;
    private int priceBucket;
    private String ownerID;
    private double avgStars;
    private ArrayList<String> reviews;

    public RestaurantDSRequestModel(String ownerID, String name, String location,
                                    String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.ownerID = ownerID;
        this.cuisineType = cuisineType;
        this.priceBucket= priceBucket;
        this.avgStars = 0;
        this.reviews = new ArrayList<>();
    }

    public RestaurantDSRequestModel(String ownerID, String name, String location,
                                    String cuisineType, int priceBucket, double avgStars,
                                    ArrayList<String> reviews) {
        this.name = name;
        this.location = location;
        this.ownerID = ownerID;
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
    String getOwnerID() {return this.ownerID;}
    double getAvgStars() {return this.avgStars;}
    ArrayList<String> getReviews() {
        if (this.reviews.size() == 0) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("empty");
            return temp;
        } else {
            return this.reviews;
        }
        //TODO decide on how to handle csv empty reviews
    }
}

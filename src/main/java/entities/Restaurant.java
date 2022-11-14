package entities;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private final String location;
    private ReviewList reviews = new ReviewList(new ArrayList<>());
    private String ownerID;
    private RestaurantAttributes attributes = new RestaurantAttributes();

    //Constructors
    Restaurant(String ownerID, String name, String location, String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.ownerID = ownerID;
        this.attributes.setPriceBucket(priceBucket);
        this.attributes.setCuisineType(cuisineType);
    }
    //Retrieval Constructor
    Restaurant(String ownerID, String name, String location, String cuisineType, int priceBucket,
               double avgStars, ArrayList<String> reviews) {
        this.name = name;
        this.location = location;
        this.ownerID = ownerID;
        this.attributes.setPriceBucket(priceBucket);
        this.attributes.setCuisineType(cuisineType);
        this.attributes.setAvgStars(avgStars);
        this.reviews = new ReviewList(reviews);
    }

    //Setters
    public void setName(String name) {this.name = name;}

    public void setOwnerID(String ownerID) {this.ownerID = ownerID;}

    public void setCuisineType(String cuisineType) {this.attributes.setCuisineType(cuisineType);}

    public void setPriceBucket(int priceBucket) {this.attributes.setPriceBucket(priceBucket);}

    public void addReview(Review review) {
        // Can take in a review object instead of ReviewID since the review object will have been newly created
        this.reviews.addNewReview(review.getID());
        this.attributes.addReview(review.getStars());
    }

    //Getters
    public String getName() {return this.name;}

    public String getLocation() {return this.location;}

//    public OwnerUser getOwner() {
//        OwnerUser owner = //TODOx retrieve owner object from database
//        return owner;
//    }

    public String getOwnerID() {return this.ownerID;}

    public ArrayList<String> getReviewIDs() {
        return this.reviews.getReviewIDs();
    }

    public ArrayList<Review>  getReview() {
        return this.reviews.getReviews();
    }

    public String getCuisineType() {return this.attributes.getCuisineType();}

    public double getAvgStars() {return this.attributes.getAvgStars();}

    public int getPriceBucket() {return this.attributes.getPriceBucket();}

    public RestaurantAttributes getAttributes() {return this.attributes;}
}

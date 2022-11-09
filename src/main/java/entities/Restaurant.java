package entities;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String location;
    private ArrayList<String> reviews = new ArrayList<String>();
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
        this.reviews = reviews;
    }

    //Setters
    public void setName(String name) {this.name = name;}

    public void setOwnerID(String ownerID) {this.ownerID = ownerID;}

    public void setCuisineType(String cuisineType) {this.attributes.setCuisineType(cuisineType);}

    public void setPriceBucket(int priceBucket) {this.attributes.setPriceBucket(priceBucket);}

    public void addReview(String reviewID) {
        //NOTE the type of reviewID may be reverted to Review
        this.reviews.add(reviewID);
        Review review = //TODO retrieve review object from database
        this.attributes.addReview(review.getStars());
    }

    //Getters
    public String getName() {return this.name;}

    public String getLocation() {return this.location;}

    public OwnerUser getOwner() {
        OwnerUser owner = //TODO retrieve owner object from database
        return owner;
    }

    public ArrayList<Review> getReviews() {
        ArrayList<Review> reviewList = new ArrayList<>();
        for (String id : this.reviews) {
            reviewList.add() //TODO retrieve review object from database
        }
        return reviewList;
    }

    public String getCuisineType() {return this.attributes.getCuisineType();}

    public double getAvgStars() {return this.attributes.getAvgStars();}

    public int getPriceBucket() {return this.attributes.getPriceBucket();}

    public RestaurantAttributes getAttributes() {return this.attributes;}
}

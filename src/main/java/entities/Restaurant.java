package entities;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String location;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private OwnerUser owner;
    private RestaurantAttributes attributes = new RestaurantAttributes();

    //Constructors
    Restaurant(OwnerUser owner, String name, String location, String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.owner = owner;
        this.attributes.setPriceBucket(priceBucket);
        this.attributes.setCuisineType(cuisineType);
    }

    //Setters
    public void setName(String name) {this.name = name;}

    public void setCuisineType(String cuisineType) {this.attributes.setCuisineType(cuisineType);}

    public void setPriceBucket(int priceBucket) {this.attributes.setPriceBucket(priceBucket);}

    public void addReview(Review review) {
        this.reviews.add(review);
        this.attributes.addReview(review.getStars());
    }

    //Getters
    public String getName() {return this.name;}

    public String getLocation() {return this.location;}

    public ArrayList<Review> getReviews() {return this.reviews;}

    public String getCuisineType() {return this.attributes.getCuisineType();}

    public double getAvgStars() {return this.attributes.getAvgStars();}

    public int getPriceBucket() {return this.attributes.getPriceBucket();}

    public RestaurantAttributes getAttributes() {return this.attributes;}
}

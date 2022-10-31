package entities;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String location;
    private String cuisineType;
    private int priceBucket;
    private double AvgStars = 0;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private OwnerUser owner;


    //Constructors
    public Restaurant(OwnerUser owner, String name, String location, String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
        this.priceBucket = priceBucket;
        this.owner = owner;
    }

    //Setters
    public void setName(String name) {this.name = name;}

    public void setCuisineType(String cuisineType) {this.cuisineType = cuisineType;}

    public void setPriceBucket(int priceBucket) {this.priceBucket = priceBucket;}

    public void setLocation(String location) {this.location = location;}

    public void addReview(Review review) {
        this.reviews.add(review);
        this.AvgStars = ((double)review.getStars() + this.AvgStars) / (this.reviews.size() + 1);
    }

    //Getters
    public String getName() {return this.name;}

    public String getLocation() {return this.location;}

    public int getPriceBucket() {return this.priceBucket;}

    public String getCuisineType() {return this.cuisineType;}

    public double getAvgStars() {return this.AvgStars;}

    public ArrayList<Review> getReviews() {return this.reviews;}
}

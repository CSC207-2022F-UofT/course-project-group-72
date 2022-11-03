package entities;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String location;
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private OwnerUser owner;
    private RestaurantAttributes attributes = new RestaurantAttributes();


    //Constructors
    public Restaurant(OwnerUser owner, String name, String location, String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.owner = owner;
        this.attributes.setPriceBucket(priceBucket);
        this.attributes.setCuisineType(cuisineType);
    }

    //Setters
    public void setName(String name) {this.name = name;}

    public void addReview(Review review) {
        this.reviews.add(review);
        this.attributes.addReview(review.getStars());
    }

    //Getters
    public String getName() {return this.name;}

    public String getLocation() {return this.location;}

    public ArrayList<Review> getReviews() {return this.reviews;}

    public RestaurantAttributes getAttributes() {return this.attributes;}
}

package entities;

import java.util.ArrayList;

public class Restaurant {
    public String name;
    public String location;
    public String cuisineType;
    public double AvgStars = 0;
    public ArrayList<Review> reviews = new ArrayList<Review>();

    //TODO Connection with User class
    // TODO2 Tags/attributes for filter @Janssen

    //Constructors
    public void Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
        this.cuisineType = "Unset";
    }

    public void Restaurant(String name, String location, String cuisineType) {
        this.name = name;
        this.location = location;
        this.cuisineType = cuisineType;
    }

    //Setters
    public void setName(String name) {this.name = name;}

    public void setCuisineType(String cuisineType) {this.cuisineType = cuisineType;}

    public void setLocation(String location) {this.location = location;}

    public void addReview(Review review) {
        this.reviews.add(review);
        this.AvgStars = ((double)review.getStars() + this.AvgStars) / (this.reviews.size() + 1);
    }

    //Getters
    public String getName() {return this.name;}

    public String getLocation() {return this.location;}

    public String getCuisineType() {return this.cuisineType;}

    public double getAvgStars() {return this.AvgStars;}

    public ArrayList<Review> getReviews() {return this.reviews;}
}

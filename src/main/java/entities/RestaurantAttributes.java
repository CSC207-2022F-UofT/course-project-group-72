package entities;

//TODO Decide which attributes will stay in Restaurant and which will stay in here

public class RestaurantAttributes {
    private String cuisineType;
    private int priceBucket;
    private double avgStars = 0;

    //Constructors
    public RestaurantAttributes() {}

    // Setters
    public void setCuisineType(String cuisineType) {this.cuisineType = cuisineType;}
    public void addReview(int stars) {
        this.avgStars = (stars + this.avgStars) / (stars + 1);
    }
    public void setPriceBucket(int priceBucket) {this.priceBucket = priceBucket;}

    // Getters
    public int getPriceBucket() {return this.priceBucket;}

    public String getCuisineType() {return this.cuisineType;}

    public double getAvgStars() {return this.avgStars;}
}

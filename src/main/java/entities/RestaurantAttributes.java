package entities;

/**
 * The object containing the Restaurant's attributes
 */
public class RestaurantAttributes {
    /**
     * The cuisine served at the Restaurant
     */
    private String cuisineType;
    /**
     * The price range of the Restaurant
     */
    private int priceBucket;
    /**
     * The average stars based on the reviews left at the restaurant
     */
    private double avgStars = 0;

    //Constructors
    public RestaurantAttributes() {}

    // Setters

    /**
     *
     * @param cuisineType change or reinitialize the cuisine served
     */
    public void setCuisineType(String cuisineType) {this.cuisineType = cuisineType;}
    /**
     *
     * @param stars the stars given in the added review
     */
    public void addReview(int stars) {
        this.avgStars = (stars + this.avgStars) / (stars + 1);
    }
    /**
     *
     * @param priceBucket the changed or reinitialized price range of the restaurant
     */
    public void setPriceBucket(int priceBucket) {this.priceBucket = priceBucket;}
    /**
     *
     * @param avgStars the reinitialized stars of the restaurant
     */
    void setAvgStars(double avgStars) {this.avgStars = avgStars;}

    // Getters
    /**
     *
     * @return the current price range of the Restaurant
     */
    public int getPriceBucket() {return this.priceBucket;}
    /**
     *
     * @return the current cuisine served at the Restaurant
     */
    public String getCuisineType() {return this.cuisineType;}
    /**
     *
     * @return the current star rating of the Restaurant
     */
    public double getAvgStars() {return this.avgStars;}
}

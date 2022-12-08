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
    RestaurantAttributes() {}

    // Setters

    /**
     *
     * @param cuisineType change or reinitialize the cuisine served
     */
    void setCuisineType(String cuisineType) {this.cuisineType = cuisineType;}
    /**
     * Makes modification to the Average Stars in response to
     * changes in ReviewList
     *
     * @param stars the stars given in the added review
     * @param reviewCount the updated amount of reviews in Restaurant
     */
    void addReview(int stars, int reviewCount) {
        // if there were no reviews other than this one then the new avg stars will just
        // be this review's stars
        if (reviewCount - 1 > 0) {
            this.avgStars = (stars + this.avgStars * (reviewCount - 1)) / (reviewCount);
        } else {
            this.avgStars = stars;
        }
    }
    /**
     * Makes modification to the Average Stars in response to
     * changes in ReviewList
     *
     * @param stars the stars given in the removed review
     * @param reviewCount the updated amount of reviews in Restaurant
     */
    void removeReview(int stars, int reviewCount) {
        // If the new review count is greater than 0 then get new average
        // else set to 0
        if (reviewCount > 0) {
            this.avgStars = (this.avgStars * (reviewCount + 1) - stars) / (reviewCount);
        } else {
            this.avgStars = 0;
        }
    }
    /**
     *
     * @param priceBucket the changed or reinitialized price range of the restaurant
     */
    void setPriceBucket(int priceBucket) {this.priceBucket = priceBucket;}
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
    int getPriceBucket() {return this.priceBucket;}
    /**
     *
     * @return the current cuisine served at the Restaurant
     */
    String getCuisineType() {return this.cuisineType;}
    /**
     *
     * @return the current star rating of the Restaurant
     */
    double getAvgStars() {return this.avgStars;}
}

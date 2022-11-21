package entities;

import java.util.ArrayList;

/**
 * The restaurant entity
 */
public class Restaurant {
    /**
     * The Restaurant's name
     */
    private String name;
    /**
     * The Restaurant's unique location, used as the identifier for a Restaurant
     */
    private final String location;
    /**
     * The Restaurant's reviews
     */
    private ReviewList reviews = new ReviewList(new ArrayList<>());
    /**
     * The Restaurant's owner, stored as their unique username
     */
    private String ownerID;
    /**
     * The Restaurant's attributes, which are used in sorting
     *  includes:
     *  - Average Stars (calculated based on the added reviews)
     *  - Cuisine Type (the type of cuisine that they serve)
     *  - Price Bucket (how expensive the restaurant is on a scale of 0 to 5)
     */
    private RestaurantAttributes attributes = new RestaurantAttributes();

    /**
     * The creation constructor of the restaurant
     *
     * @param ownerID the id of the active owner
     * @param name the name of the new restaurant
     * @param location the unique location of the new restaurant
     * @param cuisineType the desired cuisine served
     * @param priceBucket the self-described price rating
     */
    Restaurant(String ownerID, String name, String location, String cuisineType, int priceBucket) {
        this.name = name;
        this.location = location;
        this.ownerID = ownerID;
        this.attributes.setPriceBucket(priceBucket);
        this.attributes.setCuisineType(cuisineType);
    }

    /**
     * The reinitialization constructor of an existing restaurant
     *
     * @param ownerID the id of the owner
     * @param name the name of the retrieved restaurant
     * @param location the unique location of the retrieved restaurant
     * @param cuisineType the cuisine served
     * @param priceBucket the price rating
     * @param avgStars the avgStars of the retrieved restaurant
     * @param reviews the reviews of the retrieved restaurant
     */
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
    /**
     *  Change the name of the restaurant
     */
    public void setName(String name) {this.name = name;}
    /**
     *  Change the ownerID of the restaurant,
     *  effectively changing the owner of the restaurant
     */
    public void setOwnerID(String ownerID) {this.ownerID = ownerID;}
    /**
     *  Change the cuisine type of the restaurant
     */
    public void setCuisineType(String cuisineType) {this.attributes.setCuisineType(cuisineType);}
    /**
     * Change the price bucket of the restaurant
     */
    public void setPriceBucket(int priceBucket) {this.attributes.setPriceBucket(priceBucket);}
    /**
     * Add a review to both the ReviewList and
     * calculate a new average stars in attributes
     *
     * @param review the newly created review object
     */
    public void addReview(Review review) {
        // Can take in a review object instead of ReviewID since the review object will have been newly created
        this.reviews.addNewReview(review.getID());
        this.attributes.addReview(review.getStars());
    }

    //Getters
    /**
     * @return the restaurant's name
     */
    public String getName() {return this.name;}
    /**
     * @return the restaurant's unique location
     */
    public String getLocation() {return this.location;}

//    public OwnerUser getOwner() {
//        OwnerUser owner = //TODOx retrieve owner object from database
//        return owner;
//    }
    /**
     * @return the restaurant's owner in the form of the ownerID
     */
    public String getOwnerID() {return this.ownerID;}
    /**
     * @return the restaurant's reviews in the form of reviewIDs
     */
    public ArrayList<String> getReviewIDs() { return this.reviews.getReviewIDs();}
    /**
     * @return the restaurant's reviews
     */
    public ArrayList<Review> getReviews() {return this.reviews.getReviews();}
    /**
     * @return the restaurant's cuisine type
     */
    public String getCuisineType() {return this.attributes.getCuisineType();}
    /**
     * @return the restaurant's average star rating
     */
    public double getAvgStars() {return this.attributes.getAvgStars();}
    /**
     * @return the restaurant's price bucket
     */
    public int getPriceBucket() {return this.attributes.getPriceBucket();}
    /**
     * @return the restaurant's sortable attributes
     */
    public RestaurantAttributes getAttributes() {return this.attributes;}
}

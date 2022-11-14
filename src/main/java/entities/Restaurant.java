package entities;

import java.util.ArrayList;

public class Restaurant {
    // Used to deal with the empty review case when saving to database
    public static final String EMPTY_FILLER = "empty";
    /**
     * The Restaurant's name
     */
    private String name;
    /**
     * The Restaurant's unique location, used as a unique identifier
     */
    private final String location;
    /**
     * The Restaurant's reviews, stored as the review's unique ID
     */
    private ArrayList<String> reviews = new ArrayList<String>();
    /**
     * The Restaurant's owner, stored as the User's unique username
     */
    private String ownerID;
    /**
     * The Restaurant's attributes, includes its price bucket, average stars and cuisine type
     */
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

    public void addReview(Review review) {
        // Can take in a review object instead of ReviewID since the review object will have been newly created
        this.reviews.add(review.getID());
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

//    public ArrayList<Review> getReviews() {
//        ArrayList<Review> reviewList = new ArrayList<>();
//        for (String id : this.reviews) {
//            reviewList.add() //TODOx retrieve review object from database
//        }
//        return reviewList;
//    }

    public ArrayList<String> getReviewIDs() {
        try {
            this.reviews.remove(EMPTY_FILLER);
        } catch (Exception ignored) {
        }
        return this.reviews;
    }

    public String getCuisineType() {return this.attributes.getCuisineType();}

    public double getAvgStars() {return this.attributes.getAvgStars();}

    public int getPriceBucket() {return this.attributes.getPriceBucket();}

    public RestaurantAttributes getAttributes() {return this.attributes;}
}

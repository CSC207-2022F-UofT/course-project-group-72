package entities;

import Gateways.ReviewGateway;

import java.util.ArrayList;

/**
 * An object containing the list of reviewIDs of the restaurant,
 * can return the Review Object, or ID
 */
public class ReviewList {
    /**
     * The filler string to store in the csv file to prevent errors
     */
    public final static String EMPTY_FILLER = "empty";
    /**
     * the ReviewGateway which deals with the Review database
     */
    private final ReviewGateway gateway = new ReviewGateway();
    /**
     * the ArrayList of reviewIDs
     */
    private ArrayList<String> reviews;

    /**
     *
     * @param reviewIDList the reinitialized reviewIDlist
     */
    ReviewList(ArrayList<String> reviewIDList) {
        this.reviews = reviewIDList;
    }
    // Setters

    /**
     *
     * @param review the ID of the new Review to be added to the restaurant
     */
    void addNewReview(String review) {
        reviews.add(review);
    }

    // Getters

    /**
     *
     * @return the list of Review objects
     */
    ArrayList<Review> getReviews() {
        ArrayList<Review> formattedReviews = new ArrayList<>();
        try {
            this.reviews.remove(EMPTY_FILLER);
        } catch (Exception ignored) {
        }
        for (String reviewID : reviews) {
            Review review = gateway.getReview(reviewID);
            formattedReviews.add(review);
        }
        return formattedReviews;
    }
    /**
     *
     * @return the list of Review IDs
     */
    ArrayList<String> getReviewIDs() {
        try {
            this.reviews.remove(EMPTY_FILLER);
        } catch (Exception ignored) {
        }
        return this.reviews;
    }


}

package entities;

import review_feature.gateways.ReviewGateway;
import review_feature.gateways.ReviewNotFoundException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * An object containing the list of reviewIDs of the restaurant,
 * can return the Review Object, or ID
 */
public class ReviewList {
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

    /**
     *
     */
    void removeReview(String review) {reviews.remove(review); }

    // Getters

    /**
     *
     * @return the list of Review objects
     */
    ArrayList<Review> getReviews() throws ReviewNotFoundException, FileNotFoundException {
        ArrayList<Review> formattedReviews = new ArrayList<>();
        for (String reviewID : reviews) {
            Review review = gateway.getReview(reviewID);
            formattedReviews.add(review);
        }
        return formattedReviews;
    }
    /**
     *
     * @return the list of Review ID stings
     */
    ArrayList<String> getReviewIDs() {
        return this.reviews;
    }


}

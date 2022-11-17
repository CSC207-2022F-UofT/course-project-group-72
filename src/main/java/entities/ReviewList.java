package entities;

import Gateways.ReviewGateway;

import java.util.ArrayList;

public class ReviewList {
    public final static String EMPTY_FILLER = "empty";
    private final ReviewGateway gateway = new ReviewGateway();
    private ArrayList<String> reviews;

    ReviewList(ArrayList<String> reviewIDList) {
        this.reviews = reviewIDList;
    }
    // Setters
    void addNewReview(String review) {
        reviews.add(review);
    }

    // Getters
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

    ArrayList<String> getReviewIDs() {
        return this.reviews;
    }


}

//Class responsible for incrementing or decrementing the review's likes and updating that change in the database

package review_use_case.interactors;

import review_use_case.interfaces.LikeReviewInputBoundary;
import review_use_case.screens.ReviewResponseModel;
import entities.Review;

import java.io.IOException;

public class LikeReviewInteractor implements LikeReviewInputBoundary {

    /*
    Method to change the likes and reflect that change in the database
     */
    public ReviewResponseModel interact(LikeReviewRequestModel requestModel){
        try {
            Review review = requestModel.getReview();
            String reviewID = review.getID();

            //If the user has already liked the review, unlike it and update database
            if (requestModel.getUser().getLikedReviews().contains(reviewID)) {
                review.decrementLikes();
                requestModel.getReviewGateway().updateReview(review);
            }
            //Otherwise, like the review and update the database
            else {
                review.incrementLikes();
                requestModel.getReviewGateway().updateReview(review);
            }
            //Return success
            return new ReviewResponseModel(true);
        }catch(IOException e){
            //If an error occurs, return a response model containing false
            System.out.println("An error has occurred");
            e.printStackTrace();
            return new ReviewResponseModel(false);
        }
    }
}

//Class responsible for incrementing or decrementing the review's likes and updating that change in the database

package review_feature.interactors;

import entities.User;
import review_feature.interfaces.LikeReviewInputBoundary;
import review_feature.screens.ReviewResponseModel;
import entities.Review;

import java.io.IOException;

public class LikeReviewInteractor implements LikeReviewInputBoundary {

    /*
    Method to change the likes and reflect that change in the database
     */
    public ReviewResponseModel interact(LikeReviewRequestModel requestModel){
        try {
            Review review = requestModel.getReview();
            User user = requestModel.getUser();
            String reviewID = review.getID();

            //If the user has already liked the review, unlike it and update database
            if (requestModel.getUser().getLikedReviews().contains(reviewID)) {
                requestModel.getUser().removeLikedReview(reviewID);
                requestModel.getUserGateway().updateUser(user);
                review.decrementLikes();
                requestModel.getReviewGateway().updateReview(review);
            }
            //Otherwise, like the review and update the database
            else {
                requestModel.getUser().addLikedReview(reviewID);
                requestModel.getUserGateway().updateUser(user);
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

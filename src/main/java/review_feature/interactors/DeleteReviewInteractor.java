//Class responsible for deleting reviews from the user's and restaurant's list of reviews + databases

package review_feature.interactors;

import review_feature.interfaces.DeleteReviewInputBoundary;
import review_feature.screens.ReviewResponseModel;
import entities.Restaurant;
import entities.Review;
import entities.User;

import java.io.IOException;

public class DeleteReviewInteractor implements DeleteReviewInputBoundary {

    /*
    Method to delete review from the necessary databases
     */
    public ReviewResponseModel interact(DeleteReviewRequestModel requestModel){
        try {
            //Get objects and store for ease of reading
            Review review = requestModel.getReview();
            String reviewID = review.getID();
            User user = requestModel.getUser();
            Restaurant restaurant = requestModel.getRestaurant();

            //Set the Review's visible to false and delete it from the database
            //NOTE: I'm pretty sure that this method removes all references to the review object, but I set its
            //visibility to false just to make sure it doesn't show up after being deleted
            review.setVisible(false);
            requestModel.getReviewGateway().deleteReview(reviewID);

            //Remove the old version of the restaurant from the database, remove the deleted review from the restaurant
            //object's reviews, then add the old restaurant back in with this change reflected
            requestModel.getRestaurantGateway().deleteRestaurant(restaurant.getLocation());
            restaurant.removeReview(review);
            requestModel.getRestaurantGateway().save(restaurant);

            //Remove the review from the user's list of reviews + databases
            user.getPast_reviews().remove(reviewID);
            requestModel.getUserGateway().updateUser(user);

            //Return success
            return new ReviewResponseModel(true);
        }catch(IOException e){
            //If an error occurs, return a response model containing false
            System.out.println("An error has occurred.");
            e.printStackTrace();
            return new ReviewResponseModel(false);
        }
    }
}

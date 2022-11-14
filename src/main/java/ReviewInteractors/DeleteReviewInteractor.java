//Class responsible for deleting reviews from the user's and restaurant's list of reviews + databases

package ReviewInteractors;

import ReviewInterfaces.DeleteReviewInputBoundary;
import ReviewScreens.ReviewResponseModel;
import entities.Restaurant;
import entities.Review;
import entities.User;
import restaurant_use_case.RestaurantDSRequestModel;

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

            //Set the Review's visible to false and reflect that change in the database
            review.setVisible(false);
            requestModel.getReviewGateway().deleteReview(reviewID);

            //Remove the old version of the restaurant from the database, remove the deleted review from the restaurant
            //object's reviews, then add the old restaurant back in with this change reflected
            requestModel.getRestaurantGateway().delete(restaurant.getLocation());
            restaurant.getReviewIDs().remove(reviewID);
            RestaurantDSRequestModel restaurantDSRequestModel = new RestaurantDSRequestModel(restaurant.getOwnerID(),
                    restaurant.getName(), restaurant.getLocation(), restaurant.getCuisineType(),
                    restaurant.getPriceBucket(), restaurant.getAvgStars(), restaurant.getReviewIDs());
            requestModel.getRestaurantGateway().save(restaurantDSRequestModel);

            //Remove the review from the user's list of reviews + databases
            //CHANGE THIS LATER WHEN YOU HAVE THE ACTUAL GATEWAY
            user.getPast_reviews().remove(reviewID);
            requestModel.getUserGateway().deleteReview(reviewID);

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

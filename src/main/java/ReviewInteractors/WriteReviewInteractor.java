//Class responsible for adding a new review to the database and attaching the ID to a user and restaurant

package ReviewInteractors;

import ReviewInterfaces.WriteReviewInputBoundary;
import ReviewScreens.ReviewResponseModel;
import entities.Review;
import entities.User;
import entities.Restaurant;

import java.io.IOException;

public class WriteReviewInteractor implements WriteReviewInputBoundary{

    /*
    Method to add the review to the necessary objects and databases
     */
    public ReviewResponseModel interact(WriteReviewRequestModel requestModel){
        try {
            //Get attributes for ease of reading
            String id = requestModel.getReviewGateway().loadReviewID();
            User user = requestModel.getUser();
            Restaurant restaurant = requestModel.getRestaurant();

            //Add the review to the database, increment ReviewIDCounter and increment Review.currentID
            Review review = new Review(id, requestModel.getStars(), requestModel.getText(),
                    user.getUsername(), restaurant.getLocation());
            requestModel.getReviewGateway().addReview(review);
            requestModel.getReviewGateway().incrementReviewID();

            //Remove the old version of the restaurant from the database, add the new review to the restaurant
            //object's reviews, then add the old restaurant back in with this change reflected
            requestModel.getRestaurantGateway().deleteRestaurant(restaurant.getLocation());
            restaurant.getReviewIDs().add(id);
            requestModel.getRestaurantGateway().save(restaurant);

            //Add the review's id to the user database and object
            requestModel.getUserGateway().addReview(id, user.getUsername());
            user.add_review(id);

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

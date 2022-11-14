//Class responsible for adding a new review to the database and attaching the ID to a user and restaurant

package ReviewInteractors;

import ReviewInterfaces.WriteReviewInputBoundary;
import ReviewScreens.ReviewResponseModel;
import entities.Review;
import entities.User;
import entities.Restaurant;
import restaurant_use_case.RestaurantDSRequestModel;

import java.io.IOException;

public class WriteReviewInteractor implements WriteReviewInputBoundary{
    String DEFAULT_LIKES = "0";
    String DEFAULT_RESPONSE = "";
    String DEFAULT_REPORTS = "0";
    String DEFAULT_VISIBLE = "true";

    /*
    Method to add the review to the necessary objects and databases
     */
    public ReviewResponseModel interact(WriteReviewRequestModel requestModel){
        try {
            //Get attributes for ease of reading
            String id = Review.getCurrentID();
            String stars = Integer.toString(requestModel.getStars());
            String text = requestModel.getText();
            User user = requestModel.getUser();
            Restaurant restaurant = requestModel.getRestaurant();

            //Add the review to the database, increment ReviewIDCounter and increment Review.currentID
            requestModel.getReviewGateway().addReview(id, stars, text, user.getUsername(), restaurant.getLocation(),
                    DEFAULT_LIKES, DEFAULT_RESPONSE, DEFAULT_REPORTS, DEFAULT_VISIBLE);
            requestModel.getReviewGateway().incrementReviewID();
            Review.incrementCurrentID();

            //Remove the old version of the restaurant from the database, add the new review to the restaurant
            //object's reviews, then add the old restaurant back in with this change reflected
            requestModel.getRestaurantGateway().delete(restaurant.getLocation());
            restaurant.getReviewIDs().add(id);
            RestaurantDSRequestModel restaurantDSRequestModel = new RestaurantDSRequestModel(restaurant.getOwnerID(),
                    restaurant.getName(), restaurant.getLocation(), restaurant.getCuisineType(),
                    restaurant.getPriceBucket(), restaurant.getAvgStars(), restaurant.getReviewIDs());
            requestModel.getRestaurantGateway().save(restaurantDSRequestModel);

            //Add the review's id to the user database and object
            //CHANGE THIS LATER WHEN YOU HAVE THE ACTUAL GATEWAY
            requestModel.getUserGateway().addReview(id);
            requestModel.getUser().add_review(id);

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

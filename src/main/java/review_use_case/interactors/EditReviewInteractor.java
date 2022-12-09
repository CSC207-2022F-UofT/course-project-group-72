//Class responsible for changing attributes of the review and reflecting those changes in the database

package review_use_case.interactors;

import entities.Restaurant;
import review_use_case.interfaces.EditReviewInputBoundary;
import review_use_case.screens.ReviewResponseModel;
import entities.Review;

import java.io.IOException;

public class EditReviewInteractor implements EditReviewInputBoundary {

    /*
    Method to change the review's attributes and reflect those in the database
     */
    public ReviewResponseModel interact(EditReviewRequestModel editReviewRequestModel){
        try{
            Review review = editReviewRequestModel.getReview();
            Restaurant restaurant = editReviewRequestModel.getRestaurant();

            //Remove old version of review from restaurant
            restaurant.removeReview(review);

            //Change review attributes
            review.setStars(editReviewRequestModel.getStars());
            review.setText(editReviewRequestModel.getText());

            //Reflect change in database
            editReviewRequestModel.getReviewGateway().updateReview(review);

            //Add review back to restaurant
            restaurant.addReview(review);

            //Reflect change in database
            editReviewRequestModel.getRestaurantGateway().deleteRestaurant(restaurant.getLocation());
            editReviewRequestModel.getRestaurantGateway().save(restaurant);

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

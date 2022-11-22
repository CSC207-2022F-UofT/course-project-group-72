//Class responsible for changing attributes of the review and reflecting those changes in the database

package review_use_case.interactors;

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
            //Change attributes
            Review review = editReviewRequestModel.getReview();
            review.setStars(editReviewRequestModel.getStars());
            review.setText(editReviewRequestModel.getText());

            //Reflect change in database
            editReviewRequestModel.getReviewGateway().updateReview(review);

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

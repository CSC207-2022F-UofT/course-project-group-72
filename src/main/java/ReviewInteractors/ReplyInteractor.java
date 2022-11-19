//Class responsible for attaching a reply to a review and reflecting that change in the database

package ReviewInteractors;

import ReviewInterfaces.ReplyInputBoundary;
import ReviewScreens.ReviewResponseModel;
import entities.Review;

import java.io.IOException;

public class ReplyInteractor implements ReplyInputBoundary {

    /*
    Method to add the reply to the review and database
     */
    @Override
    public ReviewResponseModel interact(ReplyRequestModel requestModel) {
        try {
            //Set the review's reply
            Review review = requestModel.getReview();
            review.setResponse(requestModel.getText());

            //Update the review in the database
            requestModel.getReviewGateway().updateReview(review);

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

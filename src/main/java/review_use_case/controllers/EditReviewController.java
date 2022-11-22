//Class that packages input data and sends it to a DeleteReviewInputBoundary

package review_use_case.controllers;

import review_use_case.interfaces.EditReviewInputBoundary;
import review_use_case.interfaces.ReviewGatewayInterface;
import review_use_case.interactors.EditReviewRequestModel;
import review_use_case.screens.ReviewResponseModel;
import entities.Review;

public class EditReviewController {
    //EditReviewInputBoundary that interact will use
    private final EditReviewInputBoundary reviewInputBoundary;

    /*
    Constructor
     */
    public EditReviewController(EditReviewInputBoundary reviewInputBoundary){
        this.reviewInputBoundary = reviewInputBoundary;
    }

    /*
    Method to package the input data, send it to the reviewInputBoundary and return a response model containing a
    boolean representing if the method call was successful
     */
    public ReviewResponseModel interact(ReviewGatewayInterface reviewGateway, Review review, int stars, String text){
        EditReviewRequestModel requestModel = new EditReviewRequestModel(reviewGateway, review, stars, text);
        return this.reviewInputBoundary.interact(requestModel);
    }
}

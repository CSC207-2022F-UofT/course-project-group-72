//Class that packages input data and sends it to a DeleteReviewInputBoundary

package review_feature.controllers;

import entities.Restaurant;
import restaurant_feature.interfaces.RestaurantDSGateway;
import review_feature.interfaces.EditReviewInputBoundary;
import review_feature.interfaces.ReviewGatewayInterface;
import review_feature.interactors.EditReviewRequestModel;
import review_feature.screens.ReviewResponseModel;
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
    public ReviewResponseModel interact(ReviewGatewayInterface reviewGateway, RestaurantDSGateway restaurantGateway,
                                        Review review, Restaurant restaurant,
                                        int stars, String text){
        EditReviewRequestModel requestModel = new EditReviewRequestModel(reviewGateway, restaurantGateway, review,
                restaurant, stars, text);
        return this.reviewInputBoundary.interact(requestModel);
    }
}

//Class that packages input data and sends it to a DeleteReviewInputBoundary

package review_use_case.controllers;

import review_use_case.interfaces.DeleteReviewInputBoundary;
import review_use_case.interfaces.ReviewGatewayInterface;
import user_use_case.UserGatewayInterface;
import restaurant_use_case.RestaurantDSGateway;
import review_use_case.interactors.DeleteReviewRequestModel;
import review_use_case.screens.ReviewResponseModel;
import entities.Restaurant;
import entities.Review;
import entities.User;

public class DeleteReviewController {
    //DeleteReviewInputBoundary that this controller will use
    private final DeleteReviewInputBoundary reviewInputBoundary;

    /*
    Constructor
     */
    public DeleteReviewController(DeleteReviewInputBoundary reviewInputBoundary){
        this.reviewInputBoundary = reviewInputBoundary;
    }

    /*
    Method to package the input data, send it to the reviewInputBoundary and return a response model containing a
    boolean representing if the method call was successful
     */
    public ReviewResponseModel interact(ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                        RestaurantDSGateway restaurantGateway, Review review,
                                        User user, Restaurant restaurant){
        DeleteReviewRequestModel requestModel = new DeleteReviewRequestModel(reviewGateway, userGateway,
                restaurantGateway, review, user, restaurant);
        return this.reviewInputBoundary.interact(requestModel);
    }
}

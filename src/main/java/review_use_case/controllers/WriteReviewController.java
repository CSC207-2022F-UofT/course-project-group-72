//Class that packages input data and sends it to a WriteReviewInputBoundary

package review_use_case.controllers;

import review_use_case.interfaces.ReviewGatewayInterface;
import user_use_case.interfaces.UserGatewayInterface;
import restaurant_use_case.gateways.RestaurantDSGateway;
import review_use_case.interfaces.WriteReviewInputBoundary;
import review_use_case.interactors.WriteReviewRequestModel;
import review_use_case.screens.ReviewResponseModel;
import entities.Restaurant;
import entities.User;

public class WriteReviewController {
    //WriteReviewInputBoundary that interact will use
    private final WriteReviewInputBoundary reviewInputBoundary;

    /*
    Constructor
     */
    public WriteReviewController(WriteReviewInputBoundary reviewInputBoundary){
        this.reviewInputBoundary = reviewInputBoundary;
    }

    /*
    Method to package the input data, send it to the reviewInputBoundary and return a response model containing a
    boolean representing if the method call was successful
     */
    public ReviewResponseModel interact(ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                        RestaurantDSGateway restaurantGateway, User user, Restaurant restaurant,
                                        int stars, String text){
        WriteReviewRequestModel requestModel = new WriteReviewRequestModel(reviewGateway, userGateway,
                restaurantGateway, user, restaurant, stars, text);
        return this.reviewInputBoundary.interact(requestModel);
    }
}

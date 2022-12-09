//Class that packages input data and sends it to a WriteReviewInputBoundary

package review_feature.controllers;

import review_feature.interfaces.ReviewGatewayInterface;
import user_feature.interfaces.UserGatewayInterface;
import restaurant_feature.interfaces.RestaurantDSGateway;
import review_feature.interfaces.WriteReviewInputBoundary;
import review_feature.interactors.WriteReviewRequestModel;
import review_feature.screens.ReviewResponseModel;
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

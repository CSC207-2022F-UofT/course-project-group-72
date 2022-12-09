//Class that packages input data and sends it to a LikeReviewInputBoundary

package review_feature.controllers;

import review_feature.interfaces.LikeReviewInputBoundary;
import review_feature.interfaces.ReviewGatewayInterface;
import review_feature.interactors.LikeReviewRequestModel;
import review_feature.screens.ReviewResponseModel;
import entities.Review;
import entities.User;
import user_feature.interfaces.UserGatewayInterface;

public class LikeReviewController {
    //LikeReviewInputBoundary that interact will use
    private final LikeReviewInputBoundary reviewInputBoundary;

    /*
    Constructor
     */
    public LikeReviewController(LikeReviewInputBoundary reviewInputBoundary){
        this.reviewInputBoundary = reviewInputBoundary;
    }

    /*
    Method to package the input data, send it to the reviewInputBoundary and return a response model containing a
    boolean representing if the method call was successful
     */
    public ReviewResponseModel interact(ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                        Review review, User user){
        LikeReviewRequestModel requestModel = new LikeReviewRequestModel(reviewGateway, userGateway, review, user);
        return this.reviewInputBoundary.interact(requestModel);
    }
}

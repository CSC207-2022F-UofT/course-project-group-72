//Class that packages input data and sends it to a LikeReviewInputBoundary

package review_use_case.controllers;

import review_use_case.interfaces.LikeReviewInputBoundary;
import review_use_case.interfaces.ReviewGatewayInterface;
import review_use_case.interactors.LikeReviewRequestModel;
import review_use_case.screens.ReviewResponseModel;
import entities.Review;
import entities.User;
import user_use_case.interfaces.UserGatewayInterface;

import java.rmi.server.UID;

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

//Class that packages input data and sends it to a LikeReviewInputBoundary

package ReviewControllers;

import ReviewInterfaces.LikeReviewInputBoundary;
import ReviewInterfaces.ReviewGatewayInterface;
import ReviewInteractors.LikeReviewRequestModel;
import ReviewScreens.ReviewResponseModel;
import entities.Review;
import entities.User;

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
    public ReviewResponseModel interact(ReviewGatewayInterface reviewGateway, Review review, User user){
        LikeReviewRequestModel requestModel = new LikeReviewRequestModel(reviewGateway, review, user);
        return this.reviewInputBoundary.interact(requestModel);
    }
}

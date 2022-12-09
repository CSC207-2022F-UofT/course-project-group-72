//Class that packages input data and sends it to a ReplyInputBoundary

package review_feature.controllers;

import review_feature.interactors.ReplyRequestModel;
import review_feature.interfaces.ReplyInputBoundary;
import review_feature.interfaces.ReviewGatewayInterface;
import review_feature.screens.ReviewResponseModel;
import entities.Review;

public class ReplyController {
    //ReplyInputBoundary that the controller will use
    private final ReplyInputBoundary replyInputBoundary;

    /*
    Constructor
     */
    public ReplyController(ReplyInputBoundary replyInputBoundary){
        this.replyInputBoundary = replyInputBoundary;
    }

    /*
    Method to package the input data, send it to the reviewInputBoundary and return a response model containing a
    boolean representing if the method call was successful
     */
    public ReviewResponseModel interact(ReviewGatewayInterface reviewGateway, Review review, String text){
        ReplyRequestModel requestModel = new ReplyRequestModel(reviewGateway, review, text);
        return this.replyInputBoundary.interact(requestModel);
    }
}

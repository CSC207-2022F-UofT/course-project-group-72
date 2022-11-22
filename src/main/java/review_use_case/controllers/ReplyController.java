//Class that packages input data and sends it to a ReplyInputBoundary

package review_use_case.controllers;

import review_use_case.interactors.ReplyRequestModel;
import review_use_case.interfaces.ReplyInputBoundary;
import review_use_case.interfaces.ReviewGatewayInterface;
import review_use_case.screens.ReviewResponseModel;
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

//Class responsible for holding input data for reply use case

package review_use_case.interactors;

import review_use_case.interfaces.ReviewGatewayInterface;
import entities.Review;

public class ReplyRequestModel {
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;
    private final String text;

    /*
    Constructor
     */
    public ReplyRequestModel(ReviewGatewayInterface reviewGateway, Review review, String text){
        this.reviewGateway = reviewGateway;
        this.review = review;
        this.text = text;
    }

    //Methods to return attributes
    public ReviewGatewayInterface getReviewGateway() {return reviewGateway;}

    public Review getReview() {return review;}

    public String getText() {return text;}
}

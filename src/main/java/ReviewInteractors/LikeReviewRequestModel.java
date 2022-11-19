//Class responsible for bundling the input data required for the like use case

package ReviewInteractors;

import ReviewInterfaces.ReviewGatewayInterface;
import entities.Review;
import entities.User;

public class LikeReviewRequestModel {
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;
    private final User user;

    /*
    Constructor
     */
    public LikeReviewRequestModel(ReviewGatewayInterface reviewGateway, Review review, User user){
        this.reviewGateway = reviewGateway;
        this.review = review;
        this.user = user;
    }

    //Methods for retrieving attributes
    public ReviewGatewayInterface getReviewGateway() {return reviewGateway;}

    public Review getReview() {return review;}

    public User getUser() {return user;}
}

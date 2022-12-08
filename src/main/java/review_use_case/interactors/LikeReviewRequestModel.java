//Class responsible for bundling the input data required for the like use case

package review_use_case.interactors;

import review_use_case.interfaces.ReviewGatewayInterface;
import entities.Review;
import entities.User;
import user_use_case.interfaces.UserGatewayInterface;

public class LikeReviewRequestModel {
    private final ReviewGatewayInterface reviewGateway;
    private final UserGatewayInterface userGateway;
    private final Review review;
    private final User user;

    /*
    Constructor
     */
    public LikeReviewRequestModel(ReviewGatewayInterface reviewGateway, UserGatewayInterface userGateway,
                                  Review review, User user){
        this.reviewGateway = reviewGateway;
        this.userGateway = userGateway;
        this.review = review;
        this.user = user;
    }

    //Methods for retrieving attributes
    public ReviewGatewayInterface getReviewGateway() {return reviewGateway;}
    public UserGatewayInterface getUserGateway() {return this.userGateway;}

    public Review getReview() {return review;}

    public User getUser() {return user;}
}

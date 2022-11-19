//Class responsible for bundling the data required for the edit use case

package ReviewInteractors;

import ReviewInterfaces.ReviewGatewayInterface;
import entities.Review;

public class EditReviewRequestModel {
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;
    private final int stars;
    private final String text;

    /*
    Constructor
     */
    public EditReviewRequestModel(ReviewGatewayInterface reviewGateway, Review review, int stars, String text){
        this.reviewGateway = reviewGateway;
        this.review = review;
        this.stars = stars;
        this.text = text;
    }

    //Methods to retrieve attributes
    public ReviewGatewayInterface getReviewGateway() {return reviewGateway;}

    public Review getReview() {return review;}

    public int getStars() {return stars;}

    public String getText() {return text;}
}

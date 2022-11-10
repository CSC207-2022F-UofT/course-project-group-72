package Interactors;

import Interfaces.WriteReviewInputBoundary;
import Models.WriteReviewRequestModel;
import Models.ReviewResponseModel;
import entities.Review;

public class WriteReviewInteractor implements WriteReviewInputBoundary{
    String DEFAULT_LIKES = "0";
    String DEFAULT_RESPONSE = "";
    String DEFAULT_REPORTS = "0";
    String DEFAULT_VISIBLE = "true";

    public ReviewResponseModel interact(WriteReviewRequestModel requestModel){
        String id = entities.Review.getCurrentID();
        String stars = Integer.toString(requestModel.getStars());
        String text = requestModel.getText();
        String username = requestModel.getUser().getUsername();
        String restaurantAddress = requestModel.getRestaurant().getLocation();

        requestModel.getReviewGateway().addReview(id, stars, text, username, restaurantAddress,
                DEFAULT_LIKES, DEFAULT_RESPONSE, DEFAULT_REPORTS, DEFAULT_VISIBLE);
        requestModel.getReviewGateway().incrementReviewID();
        Review.incrementCurrentID();

        requestModel.getUserGateway().addReview(id);
        requestModel.getRestaurantGateway().addReview(id);

        return new ReviewResponseModel(true);
    }
}

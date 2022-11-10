package Controllers;

import Interfaces.WriteReviewInputBoundary;
import Models.WriteReviewRequestModel;
import Models.ReviewResponseModel;

public class WriteReviewController {
    private final WriteReviewInputBoundary reviewInputBoundary;

    public WriteReviewController(WriteReviewInputBoundary reviewInputBoundary){
        this.reviewInputBoundary = reviewInputBoundary;
    }

    public ReviewResponseModel interact(WriteReviewRequestModel requestModel){
        return this.reviewInputBoundary.interact(requestModel);
    }
}

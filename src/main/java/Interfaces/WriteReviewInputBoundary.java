package Interfaces;

import Models.ReviewResponseModel;
import Models.WriteReviewRequestModel;

public interface WriteReviewInputBoundary {
    public ReviewResponseModel interact(WriteReviewRequestModel requestModel);

}

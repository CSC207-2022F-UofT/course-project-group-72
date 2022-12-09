//Interface that all interactors who want to write reviews must implement

package review_feature.interfaces;

import review_feature.screens.ReviewResponseModel;
import review_feature.interactors.WriteReviewRequestModel;

public interface WriteReviewInputBoundary {

    /*
    Method to write a review that all interactors must implement
     */
    public ReviewResponseModel interact(WriteReviewRequestModel requestModel);

}

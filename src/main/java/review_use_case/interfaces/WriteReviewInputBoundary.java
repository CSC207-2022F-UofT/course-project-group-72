//Interface that all interactors who want to write reviews must implement

package review_use_case.interfaces;

import review_use_case.screens.ReviewResponseModel;
import review_use_case.interactors.WriteReviewRequestModel;

public interface WriteReviewInputBoundary {

    /*
    Method to write a review that all interactors must implement
     */
    public ReviewResponseModel interact(WriteReviewRequestModel requestModel);

}

//Interface that all interactors who want to edit reviews must implement

package review_use_case.interfaces;

import review_use_case.interactors.EditReviewRequestModel;
import review_use_case.screens.ReviewResponseModel;

public interface EditReviewInputBoundary {

    /*
    Method for editing reviews that all interactors must implement
     */
    public ReviewResponseModel interact(EditReviewRequestModel editReviewRequestModel);
}

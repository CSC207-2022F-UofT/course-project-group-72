//Interface that all interactors who want to edit reviews must implement

package review_feature.interfaces;

import review_feature.interactors.EditReviewRequestModel;
import review_feature.screens.ReviewResponseModel;

public interface EditReviewInputBoundary {

    /*
    Method for editing reviews that all interactors must implement
     */
    public ReviewResponseModel interact(EditReviewRequestModel editReviewRequestModel);
}

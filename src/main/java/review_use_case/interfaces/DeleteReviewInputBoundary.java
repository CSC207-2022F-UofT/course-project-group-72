//Interface that all interactors who want to delete reviews must implement

package review_use_case.interfaces;

import review_use_case.interactors.DeleteReviewRequestModel;
import review_use_case.screens.ReviewResponseModel;

public interface DeleteReviewInputBoundary {

    /*
    Method required for all interactors that want to delete reviews
     */
    public ReviewResponseModel interact(DeleteReviewRequestModel requestModel);
}

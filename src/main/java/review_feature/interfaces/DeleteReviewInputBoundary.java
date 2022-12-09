//Interface that all interactors who want to delete reviews must implement

package review_feature.interfaces;

import review_feature.interactors.DeleteReviewRequestModel;
import review_feature.screens.ReviewResponseModel;

public interface DeleteReviewInputBoundary {

    /*
    Method required for all interactors that want to delete reviews
     */
    public ReviewResponseModel interact(DeleteReviewRequestModel requestModel);
}

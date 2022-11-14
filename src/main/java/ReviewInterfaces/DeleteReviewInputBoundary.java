//Interface that all interactors who want to delete reviews must implement

package ReviewInterfaces;

import ReviewInteractors.DeleteReviewRequestModel;
import ReviewScreens.ReviewResponseModel;

public interface DeleteReviewInputBoundary {

    /*
    Method required for all interactors that want to delete reviews
     */
    public ReviewResponseModel interact(DeleteReviewRequestModel requestModel);
}

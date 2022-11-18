//Interface that all interactors who want to like reviews must implement

package ReviewInterfaces;

import ReviewScreens.ReviewResponseModel;
import ReviewInteractors.LikeReviewRequestModel;

public interface LikeReviewInputBoundary {

    /*
    Method for liking reviews that all interactors must implement
     */
    public ReviewResponseModel interact(LikeReviewRequestModel requestModel);
}

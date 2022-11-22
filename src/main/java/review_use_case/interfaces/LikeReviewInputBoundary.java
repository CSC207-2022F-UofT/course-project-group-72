//Interface that all interactors who want to like reviews must implement

package review_use_case.interfaces;

import review_use_case.screens.ReviewResponseModel;
import review_use_case.interactors.LikeReviewRequestModel;

public interface LikeReviewInputBoundary {

    /*
    Method for liking reviews that all interactors must implement
     */
    public ReviewResponseModel interact(LikeReviewRequestModel requestModel);
}

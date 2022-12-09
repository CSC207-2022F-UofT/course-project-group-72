//Interface that all interactors who want to like reviews must implement

package review_feature.interfaces;

import review_feature.screens.ReviewResponseModel;
import review_feature.interactors.LikeReviewRequestModel;

public interface LikeReviewInputBoundary {

    /*
    Method for liking reviews that all interactors must implement
     */
    public ReviewResponseModel interact(LikeReviewRequestModel requestModel);
}

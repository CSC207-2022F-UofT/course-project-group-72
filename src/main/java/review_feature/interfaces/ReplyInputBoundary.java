//Interface that all interactors who want to reply to reviews must implement

package review_feature.interfaces;

import review_feature.screens.ReviewResponseModel;
import review_feature.interactors.ReplyRequestModel;

public interface ReplyInputBoundary {

    /*
    method for replying to a review that all interactors must implement
     */
    public ReviewResponseModel interact(ReplyRequestModel requestModel);
}

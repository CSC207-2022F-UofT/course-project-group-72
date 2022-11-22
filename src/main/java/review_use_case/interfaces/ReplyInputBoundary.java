//Interface that all interactors who want to reply to reviews must implement

package review_use_case.interfaces;

import review_use_case.screens.ReviewResponseModel;
import review_use_case.interactors.ReplyRequestModel;

public interface ReplyInputBoundary {

    /*
    method for replying to a review that all interactors must implement
     */
    public ReviewResponseModel interact(ReplyRequestModel requestModel);
}

//Interface that all interactors who want to reply to reviews must implement

package ReviewInterfaces;

import ReviewScreens.ReviewResponseModel;
import ReviewInteractors.ReplyRequestModel;

public interface ReplyInputBoundary {

    /*
    method for replying to a review that all interactors must implement
     */
    public ReviewResponseModel interact(ReplyRequestModel requestModel);
}

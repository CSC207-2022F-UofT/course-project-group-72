//Interface that all interactors who want to write reviews must implement

package ReviewInterfaces;

import ReviewScreens.ReviewResponseModel;
import ReviewInteractors.WriteReviewRequestModel;

public interface WriteReviewInputBoundary {

    /*
    Method to write a review that all interactors must implement
     */
    public ReviewResponseModel interact(WriteReviewRequestModel requestModel);

}

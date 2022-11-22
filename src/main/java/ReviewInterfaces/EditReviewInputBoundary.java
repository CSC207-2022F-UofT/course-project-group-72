//Interface that all interactors who want to edit reviews must implement

package ReviewInterfaces;

import ReviewInteractors.EditReviewRequestModel;
import ReviewScreens.ReviewResponseModel;

public interface EditReviewInputBoundary {

    /*
    Method for editing reviews that all interactors must implement
     */
    public ReviewResponseModel interact(EditReviewRequestModel editReviewRequestModel);
}

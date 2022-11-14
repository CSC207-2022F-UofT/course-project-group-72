//Class responsible for holding a reference to a review and initiating the edit use case for that review

package ReviewScreens;

import ReviewControllers.EditReviewController;
import ReviewInterfaces.ReviewGatewayInterface;
import entities.Review;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditActionListener implements ActionListener {

    //Attributes we need to pass to EditReviewScreen
    private final EditReviewController editReviewController;
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;

    /*
    Constructor
     */
    public EditActionListener(EditReviewController editReviewController, ReviewGatewayInterface reviewGateway,
                              Review review){
        this.editReviewController = editReviewController;
        this.reviewGateway = reviewGateway;
        this.review = review;
    }

    /*
    Create a new EditReview Screen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        EditReviewScreen editScreen = new EditReviewScreen(this.editReviewController, this.reviewGateway, this.review);
    }
}

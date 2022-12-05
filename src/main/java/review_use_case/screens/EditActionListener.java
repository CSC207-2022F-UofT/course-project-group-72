//Class responsible for holding a reference to a review and initiating the edit use case for that review

package review_use_case.screens;

import review_use_case.controllers.EditReviewController;
import review_use_case.interfaces.ReviewGatewayInterface;
import entities.Review;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditActionListener implements ActionListener {

    //Attributes we need to pass to EditReviewScreen
    private final RestaurantView restaurantView;
    private final EditReviewController editReviewController;
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;

    /*
    Constructor
     */
    public EditActionListener(RestaurantView restaurantView, EditReviewController editReviewController,
                              ReviewGatewayInterface reviewGateway, Review review){
        this.restaurantView = restaurantView;
        this.editReviewController = editReviewController;
        this.reviewGateway = reviewGateway;
        this.review = review;
    }

    /*
    Create a new EditReview Screen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new EditReviewScreen(this.restaurantView, this.editReviewController, this.reviewGateway, this.review);
    }
}

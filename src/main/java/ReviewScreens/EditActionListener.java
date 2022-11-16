//Class responsible for holding a reference to a review and initiating the edit use case for that review

package ReviewScreens;

import ReviewControllers.EditReviewController;
import ReviewInterfaces.ReviewGatewayInterface;
import entities.Restaurant;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditActionListener implements ActionListener {

    //Attributes we need to pass to EditReviewScreen
    private final JFrame owner;
    private final EditReviewController editReviewController;
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;
    private final User user;
    private final Restaurant restaurant;

    /*
    Constructor
     */
    public EditActionListener(JFrame owner, EditReviewController editReviewController, ReviewGatewayInterface reviewGateway,
                              Review review, User user, Restaurant restaurant){
        this.owner = owner;
        this.editReviewController = editReviewController;
        this.reviewGateway = reviewGateway;
        this.review = review;
        this.user = user;
        this.restaurant = restaurant;
    }

    /*
    Create a new EditReview Screen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        EditReviewScreen editScreen = new EditReviewScreen(this.owner, this.editReviewController, this.reviewGateway,
                this.review, this.user, this.restaurant);
    }
}

//Class responsible for holding a reference to a review and user, as well as initiating the like use case

package review_use_case.screens;

import review_use_case.controllers.LikeReviewController;
import review_use_case.interfaces.ReviewGatewayInterface;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LikeActionListener implements ActionListener {

    //Attributes we need to pass to LikeReviewController
    private final JFrame parent;
    private final JButton button;
    private final LikeReviewController likeReviewController;
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;
    private final User user;

    /*
    Constructor
     */
    public LikeActionListener(JFrame parent, JButton button, LikeReviewController likeReviewController,
                              ReviewGatewayInterface reviewGateway, Review review, User user){
        this.parent = parent;
        this.button = button;
        this.likeReviewController = likeReviewController;
        this.reviewGateway = reviewGateway;
        this.review = review;
        this.user = user;
    }

    /*
    RestaurantView acts as "LikeReviewScreen." Run the like use case for the given review and change RestaurantView
    accordingly
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ReviewResponseModel responseModel = this.likeReviewController.interact(this.reviewGateway, this.review,
                this.user);

        if(responseModel.wasSuccessful() && this.button.getBackground().equals(Color.WHITE)){
            this.button.setBackground(Color.CYAN);
        }else if(responseModel.wasSuccessful() && this.button.getBackground().equals(Color.CYAN)){
            this.button.setBackground(Color.WHITE);
        }
        else{
            JOptionPane.showMessageDialog(this.parent, "Sorry, an error occurred. Please try again later.");
        }
    }
}

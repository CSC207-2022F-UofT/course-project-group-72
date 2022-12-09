//Class responsible for holding a reference to a review and initiating the reply use case

package review_feature.screens;

import review_feature.controllers.ReplyController;
import review_feature.interfaces.ReviewGatewayInterface;
import entities.Review;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyActionListener implements ActionListener {

    //Attributes we need to pass to ReplyScreen
    private final RestaurantView restaurantView;
    private final ReplyController replyController;
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;

    /*
    Constructor
     */
    public ReplyActionListener(RestaurantView restaurantView, ReplyController replyController,
                               ReviewGatewayInterface reviewGateway, Review review){
        this.restaurantView = restaurantView;
        this.replyController = replyController;
        this.reviewGateway = reviewGateway;
        this.review = review;
    }

    /*
    Create a new ReplyScreen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new ReplyScreen(this.restaurantView, this.replyController, this.reviewGateway, this.review);
    }
}

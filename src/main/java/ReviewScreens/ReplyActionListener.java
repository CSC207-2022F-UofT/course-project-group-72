//Class responsible for holding a reference to a review and initiating the reply use case

package ReviewScreens;

import ReviewControllers.ReplyController;
import ReviewInterfaces.ReviewGatewayInterface;
import entities.Restaurant;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyActionListener implements ActionListener {

    //Attributes we need to pass to ReplyScreen
    private final JFrame owner;
    private final ReplyController replyController;
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;
    private final User user;
    private final Restaurant restaurant;

    /*
    Constructor
     */
    public ReplyActionListener(JFrame ownewr, ReplyController replyController, ReviewGatewayInterface reviewGateway,
                              Review review, User user, Restaurant restaurant){
        this.owner = ownewr;
        this.replyController = replyController;
        this.reviewGateway = reviewGateway;
        this.review = review;
        this.user = user;
        this.restaurant = restaurant;
    }

    /*
    Create a new ReplyScreen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ReplyScreen replyScreen = new ReplyScreen(this.owner, this.replyController, this.reviewGateway, this.review,
                this.user, this.restaurant);
    }
}

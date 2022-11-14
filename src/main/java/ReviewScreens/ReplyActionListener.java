//Class responsible for holding a reference to a review and initiating the reply use case

package ReviewScreens;

import ReviewControllers.ReplyController;
import ReviewInterfaces.ReviewGatewayInterface;
import entities.Review;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyActionListener implements ActionListener {

    //Attributes we need to pass to ReplyScreen
    private final ReplyController replyController;
    private final ReviewGatewayInterface reviewGateway;
    private final Review review;

    /*
    Constructor
     */
    public ReplyActionListener(ReplyController replyController, ReviewGatewayInterface reviewGateway,
                              Review review){
        this.replyController = replyController;
        this.reviewGateway = reviewGateway;
        this.review = review;
    }

    /*
    Create a new ReplyScreen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ReplyScreen replyScreen = new ReplyScreen(this.replyController, this.reviewGateway, this.review);
    }
}

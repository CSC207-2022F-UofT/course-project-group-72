//Class responsible for holding a reference to a review and its user, as well as initiating the report use case

package review_feature.screens;

import report_feature.screens.ReportScreen;
import report_feature.screens.ReportController;
import entities.Review;
import entities.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportActionListener implements ActionListener {

    //Attributes we need to pass to ReportScreen
    private final RestaurantView previousFrame;
    private final ReportController reportController;
    private final Review review;
    private final User user;

    /*
    Constructor
     */
    public ReportActionListener(RestaurantView previousFrame, ReportController reportController, Review review, User user){
        this.previousFrame = previousFrame;
        this.reportController = reportController;
        this.review = review;
        this.user = user;
    }

    /*
    Create a new ReportScreen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ReportScreen reportScreen = new ReportScreen(this.previousFrame, this.reportController, this.review, this.user);
        reportScreen.setVisible(true);
    }
}

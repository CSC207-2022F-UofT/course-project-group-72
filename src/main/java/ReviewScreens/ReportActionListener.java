//Class responsible for holding a reference to a review and its user, as well as initiating the report use case

package ReviewScreens;

import report_screens.ReportScreen;
import report_screens.ReportController;
import entities.Review;
import entities.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportActionListener implements ActionListener {

    //Attributes we need to pass to ReportScreen
    private final ReportController reportController;
    private final Review review;
    private final User user;

    /*
    Constructor
     */
    public ReportActionListener(ReportController reportController, Review review, User user){
        this.reportController = reportController;
        this.review = review;
        this.user = user;
    }

    /*
    Create a new ReportScreen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ReportScreen reportScreen = new ReportScreen(this.reportController, this.review, this.user);
    }
}

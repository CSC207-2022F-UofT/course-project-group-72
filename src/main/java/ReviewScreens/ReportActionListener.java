//Class responsible for holding a reference to a review and its user, as well as initiating the report use case

package ReviewScreens;

import report_screens.ReportScreen;
import report_screens.ReportController;
import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportActionListener implements ActionListener {

    //Attributes we need to pass to ReportScreen
    private final ReportController reportController;
    private final Review review;
    private final User user;

    JFrame owner;

    /*
    Constructor
     */
    public ReportActionListener(JFrame owner, ReportController reportController, Review review, User user){
        this.reportController = reportController;
        this.review = review;
        this.user = user;
        this.owner = owner;
    }

    /*
    Create a new ReportScreen for the given review
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ReportScreen reportScreen = new ReportScreen(this.owner, this.reportController, this.review, this.user);
    }
}

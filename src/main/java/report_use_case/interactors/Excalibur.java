package report_use_case.interactors;

import entities.Review;
import entities.User;
import report_use_case.interfaces.BanningAlgorithm;

//Implement: BanningAlgorithm (Banning Strategy)

//This class is a concrete implementation of the BanningAlgorithm interface.
//It is used to ban a user and invisible a review from the system by checking the amount of reports received.
public class Excalibur implements BanningAlgorithm {

    //threshold for banning review
    private final int REVIEW_THRESHOLD = 10;

    //threshold for banning user
    private final int USER_THRESHOLD = 30;

    private User targetedUser;

    private Review targetedReview;

    /**
     *
     * @param targetedUser: User corresponding to the Review being reported
     * @param targetedReview: Review being reported
     */

    public Excalibur(User targetedUser, Review targetedReview) {
        this.targetedUser = targetedUser;
        this.targetedReview = targetedReview;
    }

    /**
     *
     * @return the manipulated review object
     *
     * This method check if review has more than 10 reports
     */
    public Review checkAndBanReview() {
        targetedReview.addReport();
        if (targetedReview.getReports() >= REVIEW_THRESHOLD) {
            targetedReview.setVisible(false);
        }

        return targetedReview;
    }

    /**
     *
     * @return the manipulated user object
     *
     * This method check if user has more than 30 reports
     */
    public User checkAndBanUser(){
        targetedUser.addReport();
        if (targetedUser.getReceived_reports() >= USER_THRESHOLD) {
            targetedUser.setBanned();
        }

        return targetedUser;
    }





}

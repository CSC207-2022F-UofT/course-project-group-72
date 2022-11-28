package report_use_case.interactors;

import entities.Review;
import entities.User;

public class Excalibur {

    private  User targetedUser;

    private  Review targetedReview;

    /**
     *
     * @param targetedUser
     * @param targetedReview
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
    public Review executeReview() {
        if (targetedReview.getReports() >= 10) {
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
    public User executeUser(){
        if (targetedUser.getReceived_reports() >= 30) {
            targetedUser.setBanned();
        }

        return targetedUser;
    }




}

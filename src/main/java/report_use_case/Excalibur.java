package report_use_case;

import entities.Review;
import entities.User;

public class Excalibur {

    private  User targeted_user;

    private  Review targeted_review;

    /**
     *
     * @param targeted_user
     * @param targeted_review
     */

    public Excalibur(User targeted_user, Review targeted_review) {
        this.targeted_user = targeted_user;
        this.targeted_review = targeted_review;
    }

    /**
     *
     * @return the manipulated review object
     *
     * This method check if review has more than 10 reports
     */
    public Review execute_review() {
        if (targeted_review.getReports() >= 10) {
            targeted_review.setVisible(false);
        }

        return targeted_review;
    }

    /**
     *
     * @return the manipulated user object
     *
     * This method check if user has more than 30 reports
     */
    public User execute_user(){
        if (targeted_user.getReceived_reports() >= 30) {
            targeted_user.setBanned();
        }

        return targeted_user;
    }




}

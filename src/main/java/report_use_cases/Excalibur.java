package report_use_cases;

import entities.Review;
import entities.User;

public class Excalibur {

    private final User targeted_user;

    private final Review targeted_review;

    public Excalibur(User targeted_user, Review targeted_review) {
        this.targeted_user = targeted_user;
        this.targeted_review = targeted_review;
    }

    public void execute() {
        if (targeted_review.getReports() >= 10) {
            targeted_review.setVisible(false);
        }

        if (targeted_user.getReceived_reports() >= 30) {
            targeted_user.setBanned();
        }
    }



}

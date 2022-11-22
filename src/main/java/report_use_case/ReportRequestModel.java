package report_use_case;

import entities.Review;
import entities.User;

public class ReportRequestModel {

    private final String reason;
    private final Review review;
    private final User reporter;

    public ReportRequestModel(String reason, Review review, User reporter) {
        this.reason = reason;
        this.review = review;
        this.reporter = reporter;
    }

    public String getReason() {
        return this.reason;
    }

    public Review getReview() {
        return this.review;
    }

    public User getReporter() {
        return this.reporter;
    }

}

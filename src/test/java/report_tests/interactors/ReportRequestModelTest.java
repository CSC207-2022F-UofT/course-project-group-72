package report_tests.interactors;

import entities.Review;
import entities.ReviewFactory;
import entities.User;
import org.junit.jupiter.api.Test;
import report_feature.interactors.ReportRequestModel;

class ReportRequestModelTest {

    User reporter = new User("TestInteractor reporter_username", "12345678910231073");
    Review review = new ReviewFactory().create("ID", 5, "content", "Reviwer", "location");

    ReportRequestModel requestModel = new ReportRequestModel("reason", review, reporter);
    @Test
    void getReason() {
        assert requestModel.getReason() == "reason";
    }

    @Test
    void getReview() {
        assert requestModel.getReview() ==  review;
    }

    @Test
    void getReporter() {
        assert requestModel.getReporter() == reporter;
    }
}
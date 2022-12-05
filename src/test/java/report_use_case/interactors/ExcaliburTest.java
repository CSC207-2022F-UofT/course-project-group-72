package report_use_case.interactors;

import entities.Review;
import entities.ReviewFactory;
import entities.User;
import org.junit.jupiter.api.Test;


class ExcaliburTest {

    @Test
    void execute_review() {

        User exUser = new User("TestUser", "1234567");
        Review exReview = new ReviewFactory().create("1", 5, "TestReview", "TestUser",
                "TestRestaruantAddr");


        //Review will be set to invisible if it has >= 10 reports
        for (int i = 0; i <=15; i++){
            exReview.addReport();
        }


        Excalibur excalibur = new Excalibur(exUser, exReview);
        Review afterReview = excalibur.checkAndBanReview();
        assert !afterReview.isVisible();
    }

    @Test
    void execute_user() {

        User exUser = new User("TestUser", "1234567");
        Review exReview = new ReviewFactory().create("1", 5, "TestReview", "TestUser",
                "TestRestaruantAddr");

        //User will be banned if it has >= 30 reports
        for (int i = 0; i <= 35; i++){
            exUser.addReport();
        }


        Excalibur excalibur = new Excalibur(exUser, exReview);
        User afterUser = excalibur.checkAndBanUser();
        assert afterUser.isBanned();
    }
}
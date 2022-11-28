package report_use_case.interactors;

import entities.Review;
import entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;

class ExcaliburTest {

    @Test
    void execute_review() {

        User exUser = new User("TestUser", "1234567");
        Review exReview = new Review("1", 5, "TestReview", "TestUser",
                "TestRestaruantAddr");


        //Review will be set to invisible if it has >= 10 reports
        for (int i = 0; i <=15; i++){
            exReview.addReport();
        }


        Excalibur excalibur = new Excalibur(exUser, exReview);
        Review afterReview = excalibur.executeReview();
        assert afterReview.isVisible() == false;
    }

    @Test
    void execute_user() {

        User exUser = new User("TestUser", "1234567");
        Review exReview = new Review("1", 5, "TestReview", "TestUser",
                "TestRestaruantAddr");

        //User will be banned if it has >= 30 reports
        for (int i = 0; i <= 35; i++){
            exUser.addReport();
        }


        Excalibur excalibur = new Excalibur(exUser, exReview);
        User afterUser = excalibur.executeUser();
        assert afterUser.isBanned() == true;
    }
}
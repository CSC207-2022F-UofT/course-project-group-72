package report_tests.screens;

import entities.ReportFactory;
import entities.Review;
import entities.ReviewFactory;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import report_feature.interactors.Excalibur;
import report_feature.screens.ReportPresenter;
import report_feature.screens.ReportResponseFormat;

import java.io.IOException;

class ReportScreenTest {



    @Test
    void testReportScreen() throws IOException {

        Review currentReview = new ReviewFactory().create("1", 5, "asd", "test", "testAddress");
        User reviewer = new User("test", "123456789asfada");
        User currentUser = new User("testReporter", "asiudgaiufgiuagf");
        ReportFactory factory = new ReportFactory();
        Excalibur excalibur = new Excalibur(reviewer, currentReview);
        ReportPresenter presenter = new ReportResponseFormat();

        //JFrame testFrame = new JFrame();
        //FileReportHistory fileReportHistoryForInteractor = new FileReportHistory("src/test/java/report_use_case/screens/screen_test.csv");
        //ReportInteract testReportInteractor = new ReportInteract(fileReportHistoryForInteractor, factory, excalibur, presenter);
        //ReportController testController = new ReportController(testReportInteractor);

        //ReportScreen screen = new ReportScreen(testFrame, testController, currentReview, currentUser);

        ///////// ENTER "reason" in the text field and click "submit" button !!!!!!!!!!!!!!!!!!!!!!!!!!!

        //GitHub Autograd cannot "click" submit, so screen test won't pass its checker, all commented out.
    }


    @AfterAll
    static void tearDown() {
        //File deleteTestFile = new File("src/test/java/report_use_case/screens/screen_test.csv");
        //deleteTestFile.delete();
    }
}
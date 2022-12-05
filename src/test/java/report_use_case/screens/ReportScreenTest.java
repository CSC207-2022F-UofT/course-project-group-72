package report_use_case.screens;

import entities.ReportFactory;
import entities.Review;
import entities.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import report_use_case.interactors.Excalibur;
import report_use_case.interactors.ReportInteract;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

class ReportScreenTest {



    @Test
    void testReportScreen() throws IOException {

        Review currentReview = new Review("1", 5, "asd", "test", "testAddress");
        User reviewer = new User("test", "123456789asfada");
        User currentUser = new User("testReporter", "asiudgaiufgiuagf");
        ReportFactory factory = new ReportFactory();
        Excalibur excalibur = new Excalibur(reviewer, currentReview);
        ReportPresenter presenter = new ReportResponseFormat();

        JFrame testFrame = new JFrame();
        FileReportHistory fileReportHistoryForInteractor = new FileReportHistory("src/test/java/report_use_case/screens/screen_test.csv");
        ReportInteract testReportInteractor = new ReportInteract(fileReportHistoryForInteractor, factory, excalibur, presenter);
        ReportController testController = new ReportController(testReportInteractor);

        ReportScreen screen = new ReportScreen(testFrame, testController, currentReview, currentUser);

        ///////// ENTER "reason" in the text field and click "submit" button !!!!!!!!!!!!!!!!!!!!!!!!!!!
    }


    @AfterAll
    static void tearDown() {
        File deleteTestFile = new File("src/test/java/report_use_case/screens/screen_test.csv");
        deleteTestFile.delete();
    }
}
package report_use_case.screens;

import entities.ReportFactory;
import entities.Review;
import entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import report_use_case.interactors.Excalibur;
import report_use_case.interactors.ReportInteract;
import review_use_case.gateways.ReviewGateway;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ReportControllerTest {

    static ReportFactory testFactory;
    static Excalibur testExcalibur;
    static ReportPresenter testPresenter;

    static ReviewGateway reviewGateway;

    static Review review;

    static User reporter;

    static User reviewer;

    @BeforeAll
    public static void setUp() {


        File deleteTestFile = new File("src/test/java/report_use_case/screens/Controller_test.csv");
        deleteTestFile.delete();

        review = new Review("TEST_ID", 5, "TEST_CONTENT", "TEST_REVIEWER_ID",
                "TEST_RESTAURANT_ID");

        reporter = new User("Test reporter_username", "1234567123123");

        reviewer = new User("TEST_REVIEWER_ID", "1234523123");

        testFactory = new ReportFactory();
        testExcalibur = new Excalibur(reviewer, review);
        testPresenter = new ReportResponseFormat();
        reviewGateway = new ReviewGateway();
    }



    @Test
    void create() throws Exception {

        FileReportHistory fileReportHistoryForInteractor =
                new FileReportHistory("src/test/java/report_use_case/screens/Controller_test.csv");

        ReportInteract testReportInteractor = new ReportInteract(fileReportHistoryForInteractor, testFactory, testExcalibur, testPresenter);

        ReportController testReportController = new ReportController(testReportInteractor);

        try {
            testReportController.create("TEST_REASON", review, reporter);
            fail("Should have thrown an exception because NO User database");
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    void checkDataSavedCorrectly() throws FileNotFoundException {
        File fileToCheck = new File("src/test/java/report_use_case/screens/Controller_test.csv");
        assertTrue(fileToCheck.exists());
        Scanner scanner = new Scanner(fileToCheck);
        String line;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] lineArray = line.split(",");
            assertEquals("TEST_REASON", lineArray[2]);
            assertEquals("TEST_ID", lineArray[0]);
            assertEquals("Test reporter_username", lineArray[1]);
            assertEquals("TEST_CONTENT", lineArray[3]);
            assertNotNull(lineArray[4]);
        }

    }
}
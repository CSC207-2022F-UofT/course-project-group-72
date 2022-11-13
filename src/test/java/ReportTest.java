import entities.Report;
import entities.ReportFactory;
import entities.Review;
import entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import report_screens.FileReportHistory;
import report_screens.ReportResponseFormat;
import report_use_cases.*;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class ReportTest {

    Review testReviewObject;
    User testUserObject;
    Report testReport;
    String testReporterUsername;

    ReportDsRequestModel testReportDsModel;

    ReportFactory testFactory;

    FileReportHistory fileReportHistory;

    reportInputBoundary testReportInteractor;

    Excalibur testExcalibur;

    ReportPresenter testPresenter;




    @Before
    public void setUp() throws IOException {
        Review testReviewObject = new Review("1", 5, "TestReview", "TestUser",
                "TestRestaruantAddr");

        testUserObject = new User("TestUser", "1234567");

        testFactory = new ReportFactory();

        testReporterUsername = "TestReporter";

        testReport = testFactory.create("testReason", testReviewObject, testReporterUsername);

        testReportDsModel = new ReportDsRequestModel("Test ReportDsRequestModel_reason",
                "TestDs content", "TestDs Id", "TestDs usernation", "TestDs creationtime");


        fileReportHistory =  new FileReportHistory("Report_test.csv");

        testPresenter = new ReportResponseFormat();


    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testReportObject(){

        //Test getReview
        Review expectedReview = testReviewObject;
        Review actuaReview = testReport.getReview();
        assertEquals(expectedReview, actuaReview);

        //Test getReason
        String expectedReason = "testReason";
        String actualReason = testReport.getReason();
        assertEquals(expectedReason, actualReason);

        //Test getReporterUsername
        String expectedReporterUsername = "TestReporter";
        String actualReporterUsername = testReport.getReporter_username();
        assertEquals(expectedReporterUsername, actualReporterUsername);

        //Test getReportedUser
        String expectedReportedUser = testReviewObject.getUser();
        String actuaReportedUser = testReport.getReporter_username();
        assertEquals(expectedReportedUser, actuaReportedUser);

        //Test getReviewContent
        String expectedReviewContent = testReviewObject.getText();
        String actualReviewContent = testReport.getReviewContent();
        assertEquals(expectedReviewContent, actualReviewContent);



    }

    @Test
    public void testFileReportHistory() {

        fileReportHistory.save(testReportDsModel);

        assertTrue(fileReportHistory.getSave_reports().containsKey(testReport.getReview_id()));
        assertTrue(fileReportHistory.getCheck_reports().containsKey(testReport.getReview_id()));

        //test object is the same object in the hashmap

        assertEquals(testReport, fileReportHistory.getSave_reports().get(testReport.getReview_id()));

        assertEquals(testReport.getReporter_username(), fileReportHistory.getCheck_reports().get(testReport.getReview_id()));
    }

    @Test
    public void testExcalibur_banned_properly(){

        User exUser = new User("TestUser", "1234567");
        Review exReview = new Review("1", 5, "TestReview", "TestUser",
                "TestRestaruantAddr");

        //Review will be set to invisible if it has >= 10 reports
        for (int i = 0; i < 10; i++){
            exReview.addReport();
        }

        //User will be banned if it has >= 30 reports
        for (int i = 0; i < 5; i++){
            exUser.addReport();
        }


        testExcalibur = new Excalibur(exUser, exReview);
        User afterUser = testExcalibur.execute_user();
        Review afterReview = testExcalibur.execute_review();
        assertEquals(afterUser.isBanned(), false);
        assertEquals(afterReview.isVisible(), true);

    }

    @Test
    public void testPresenter_successView() {

        ReportResponseModel testResponseModel = new ReportResponseModel("TestUser", "1", "2020-01-01");
        testPresenter = new ReportResponseFormat();
        ReportResponseModel actual = testPresenter.prepareSuccessView(testResponseModel);

        //successView doesn't change the object
        assertEquals(testResponseModel, actual);
    }

    @Test
    public void testPresenter_failureView() {

        ReportResponseModel testResponseModel = new ReportResponseModel("TestUser", "1", "2020-01-01");

        try{
            testPresenter.prepareFailView("Report already exists.");

            fail("ReportCreationFailure should be thrown");

        } catch (Exception e){
            assertTrue(e instanceof RuntimeException);
        }
    }

    @Test
    public void testInteractor_create() throws IOException {

            ReportDsRequestModel testInteractorDsModel = new ReportDsRequestModel("Test Interactor_reason",
                "TestInteractor content", "TestInteractor reviewID", "TestInteractor reporter_username", "TestInteractor creationtime");

            Review identicalReview = new Review("TestInteractor reviewID", 5, "TestInteractor content", "TestInteractor reviewerID",
                "123");

            User identicalReporter = new User("TestInteractor reporter_username", "1234567");

            User differentReporter = new User("TestInteractor reporter_username2", "1234567888");

            //duplicate report (same review same reporter)
            ReportRequestModel testInteractorModelwithDuplicatedReport = new ReportRequestModel("Test Interactor_reason2",
                identicalReview, identicalReporter);

            //new report (same review different reporter)
            ReportRequestModel testInteractorModelwithNewReport = new ReportRequestModel("Test Interactor_reason3",
                identicalReview, differentReporter);

            FileReportHistory fileReportHistoryForInteractor =  new FileReportHistory("Interactor_test.csv");

            //save one to the file first
            fileReportHistoryForInteractor.save(testInteractorDsModel);

            testReportInteractor = new ReportInteract(fileReportHistoryForInteractor, testFactory, testExcalibur, testPresenter);

            //test if Interactor throw exception when duplicated report is created
            try{
                testReportInteractor.create(testInteractorModelwithDuplicatedReport);
                fail("ReportCreationFailure should be thrown");
            } catch (Exception e){
                assertTrue(e instanceof RuntimeException);
            }

            //test if Interactor create new report when new report is created
            try{
                testReportInteractor.create(testInteractorModelwithNewReport);

            } catch (Exception e){
                fail("ReportCreationFailure should not be thrown");
            }

            //reset the file after test
            File resetTestFile = new File("Interactor_test.csv");
            resetTestFile.delete();

    }

    @Test
    public void testInteractorReturnResponseModel_not_mutate_model() throws IOException {

        FileReportHistory fileReportHistoryForInteractor =  new FileReportHistory("Interactor_test.csv");
        testReportInteractor = new ReportInteract(fileReportHistoryForInteractor, testFactory, testExcalibur, testPresenter);

        Review review = new Review("TestInteractor reviewID", 5, "TestInteractor content", "TestInteractor reviewerID",
                "123");

        User reporter = new User("TestInteractor reporter_username", "1234567");



        //duplicate report (same review same reporter)
        ReportRequestModel testInteractorModel = new ReportRequestModel("Test Interactor_reason",review
                , reporter);

        LocalDateTime ldt = LocalDateTime.now();
        ReportResponseModel expected = new ReportResponseModel("TestInteractor reporter_username", "TestInteractor reviewID", ldt.toString());
        ReportResponseModel actual = testReportInteractor.create(testInteractorModel);
        assertEquals(expected, actual);


    }




}

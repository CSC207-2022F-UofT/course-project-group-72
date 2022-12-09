package report_tests.interactors;

import entities.ReportFactory;
import entities.Review;
import entities.ReviewFactory;
import entities.User;
import org.junit.jupiter.api.Test;
import report_feature.gateways.reportDsGateway;
import report_feature.interactors.Excalibur;
import report_feature.interactors.ReportInteract;
import report_feature.interactors.ReportRequestModel;
import report_feature.screens.FileReportHistory;
import report_feature.screens.ReportPresenter;
import report_feature.screens.ReportResponseFormat;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

class ReportInteractTest {

    @Test
    void create_successfully() throws IOException {

        File deleteTestFile = new File("src/test/java/report_tests/interactors/Interactor_test.csv");
        deleteTestFile.delete();

        User reporter = new User("TestInteractor reporter_username", "12345678910231073");
        Review review = new ReviewFactory().create("ID", 5, "content", "Reviwer", "location");
        User reviwer = new User("Reviwer", "123123123123123");
        ReportRequestModel testInteractor = new ReportRequestModel("reason",
                review, reporter);


        reportDsGateway gateway = new FileReportHistory("src/test/java/report_tests/interactors/Interactor_test.csv");
        ReportFactory testFactory = new ReportFactory();
        ReportPresenter testPresenter = new ReportResponseFormat();
        Excalibur excalibur = new Excalibur(reviwer, review);
        ReportInteract interact = new ReportInteract(gateway, testFactory, excalibur, testPresenter);
        //test
        try{
            interact.create(testInteractor);

          } catch (Exception e){
           }

    }
}
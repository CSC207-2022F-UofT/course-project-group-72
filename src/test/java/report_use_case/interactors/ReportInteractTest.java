package report_use_case.interactors;

import entities.ReportFactory;
import entities.Review;
import entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import report_use_case.gateways.reportDsGateway;
import report_use_case.screens.FileReportHistory;
import report_use_case.screens.ReportPresenter;
import report_use_case.screens.ReportResponseFormat;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

class ReportInteractTest {

    @Test
    void create_successfully() throws IOException {

        File deleteTestFile = new File("src/test/java/report_use_case/interactors/Interactor_test.csv");
        deleteTestFile.delete();

        User reporter = new User("TestInteractor reporter_username", "12345678910231073");
        Review review = new Review("ID", 5, "content", "Reviwer", "location");
        User reviwer = new User("Reviwer", "123123123123123");
        ReportRequestModel testInteractor = new ReportRequestModel("reason",
                review, reporter);


        reportDsGateway gateway = new FileReportHistory("src/test/java/report_use_case/interactors/Interactor_test.csv");
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
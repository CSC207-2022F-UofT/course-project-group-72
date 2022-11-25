package report_use_case.screens;

import org.junit.jupiter.api.*;
import report_use_case.interactors.ReportDsRequestModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileReportHistoryTest {

    static FileReportHistory testGateway;

    @BeforeAll
    static void setUp() throws IOException {

        File resetFile = new File("src/test/java/report_use_case/screens/testgateway1");
        resetFile.delete();

        testGateway = new FileReportHistory("src/test/java/report_use_case/screens/testgateway1");
    }


    @Test
    void save() throws IOException {
        ReportDsRequestModel testDsMOdel = new ReportDsRequestModel("Testreason",
                "Testcontent", "TestreviewID",
                "Testreporter_username1", "Testcreationtime");
        testGateway.save(testDsMOdel);

        ReportDsRequestModel testDsMOdel2 = new ReportDsRequestModel("Testreason2",
                "Testcontent2", "TestreviewID",
                "Testreporter_username2", "Testcreationtime2");
        testGateway.save(testDsMOdel2);
    }

    @Test
    void existsReportByReporterAndReview() throws FileNotFoundException {
        assert !testGateway.existsReportByReporterAndReview(
                "Testreporter_username0",
                "TestreviewID2");

       assert testGateway.existsReportByReporterAndReview(
               "Testreporter_username2",
              "TestreviewID");
    }


    @AfterAll
    static void tearDown() {
        //File resetFile = new File("src/test/java/report_use_case/screens/testgateway1");
        //resetFile.delete();

    }
}
package report_tests.screens;

import org.junit.jupiter.api.*;
import report_feature.interactors.ReportDsRequestModel;
import report_feature.screens.FileReportHistory;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileReportHistoryTest {

    static FileReportHistory testGateway;

    @BeforeAll
    static void setUp() throws IOException {
       File resetFile = new File("src/test/java/report_tests/screens/testgateway1");
        resetFile.delete();

        testGateway = new FileReportHistory("src/test/java/report_tests/screens/testgateway1");
    }


    @Test
    void save() {
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
    void existsReportByReporterAndReview() throws IOException {

        FileReportHistory testGateway2 = new FileReportHistory("src/test/java/report_tests/screens/testgateway2");
        assertTrue(testGateway2.existsReportByReporterAndReview("Testreporter_username1", "TestreviewID"));
        assertTrue(testGateway2.existsReportByReporterAndReview("Testreporter_username2", "TestreviewID"));
        assertFalse(testGateway2.existsReportByReporterAndReview("Testreporter_username3", "TestreviewID"));
    }


    @AfterAll
    static void tearDown() {
    }
}
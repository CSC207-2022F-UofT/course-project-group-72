package report_tests.screens;

import org.junit.jupiter.api.Test;
import report_feature.screens.ReportCreationFailure;

import static org.junit.jupiter.api.Assertions.*;

class ReportCreationFailureTest {

    @Test
    void testReportCreationFailure() {
        ReportCreationFailure reportCreationFailure = new ReportCreationFailure("Error");
        assertEquals("Error", reportCreationFailure.getMessage());
    }

}
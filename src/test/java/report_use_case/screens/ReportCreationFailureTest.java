package report_use_case.screens;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportCreationFailureTest {

    @Test
    void testReportCreationFailure() {
        ReportCreationFailure reportCreationFailure = new ReportCreationFailure("Error");
        assertEquals("Error", reportCreationFailure.getMessage());
    }

}
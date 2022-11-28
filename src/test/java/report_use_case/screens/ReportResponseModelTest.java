package report_use_case.screens;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportResponseModelTest {

    @Test
    void getReporter_name() {
        ReportResponseModel reportResponseModel = new ReportResponseModel("reporter_name", "review_id", "creation_time");
        assertEquals("reporter_name", reportResponseModel.getReporter_name());
    }

    @Test
    void getReview_id() {
        ReportResponseModel reportResponseModel = new ReportResponseModel("reporter_name", "review_id", "creation_time");
        assertEquals("review_id", reportResponseModel.getReview_id());
    }

    @Test
    void getCreation_time() {
        ReportResponseModel reportResponseModel = new ReportResponseModel("reporter_name", "review_id", "creation_time");
        assertEquals("creation_time", reportResponseModel.getCreation_time());
    }
}
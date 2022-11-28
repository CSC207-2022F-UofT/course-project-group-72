package report_use_case.interactors;


import org.junit.jupiter.api.Test;


import java.util.Objects;


class ReportDsRequestModelTest {

    ReportDsRequestModel testDsMOdel = new ReportDsRequestModel("Test reason",
            "Testr content", "Test reviewID",
            "Test reporter_username", "Test creationtime");

    @Test
    void getReason() {
        assert Objects.equals(testDsMOdel.getReason(), "Test reason");
    }

    @Test
    void getContent() {
        assert Objects.equals(testDsMOdel.getContent(), "Testr content");
    }

    @Test
    void getReview_id() {
        assert Objects.equals(testDsMOdel.getReview_id(), "Test reviewID");
    }

    @Test
    void getReporter_username() {
        assert Objects.equals(testDsMOdel.getReporter_username(), "Test reporter_username");
    }

    @Test
    void getCreation_time() {
        assert Objects.equals(testDsMOdel.getCreation_time(), "Test creationtime");
    }
}
package report_use_case.screens;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ReportResponseFormatTest {

    @Test
    void prepareSuccessView() {
        ReportResponseModel testResponseModel = new ReportResponseModel("TestUser", "1", "2020-01-01");

        ReportResponseFormat testPresenter = new ReportResponseFormat();

        ReportResponseModel actual = testPresenter.prepareSuccessView(testResponseModel);

        //successView doesn't change the object
        assertEquals(testResponseModel, actual);
    }

    @Test
    void prepareFailView() {
        String testError = "TestError";

        ReportResponseFormat testPresenter = new ReportResponseFormat();

        assertThrows(ReportCreationFailure.class, () -> testPresenter.prepareFailView(testError));
    }
}
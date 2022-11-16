package report_screens;

import entities.Review;
import entities.User;
import report_use_cases.ReportRequestModel;
import report_use_cases.ReportResponseModel;
import report_use_cases.reportInputBoundary;

public class ReportController {

    final reportInputBoundary input;

    public ReportController(reportInputBoundary input) {
        this.input = input;
    }

    public ReportResponseModel create(String reason, Review review, User reporter) throws Exception {

        ReportRequestModel reportRequestModel = new ReportRequestModel(reason, review, reporter);
        return input.create(reportRequestModel);
    }
}

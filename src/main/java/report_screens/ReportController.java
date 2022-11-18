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

        // create a ReportRequestModel object
        ReportRequestModel reportRequestModel = new ReportRequestModel(reason, review, reporter);
        // pass reportrequestmodel to input.create method which returns a reportreponsemodel
        return input.create(reportRequestModel);
    }
}

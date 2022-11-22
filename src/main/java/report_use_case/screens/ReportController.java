package report_use_case.screens;

import entities.Review;
import entities.User;
import report_use_case.interactors.ReportRequestModel;
import report_use_case.interfaces.reportInputBoundary;

public class ReportController {

    final reportInputBoundary input;

    public ReportController(reportInputBoundary input) {
        this.input = input;
    }

    /**
     *
     * @param reason
     * @param review
     * @param reporter
     * @return ReportResponseModel
     * @throws Exception
     */
    public ReportResponseModel create(String reason, Review review, User reporter) throws Exception {

        // create a ReportRequestModel object
        ReportRequestModel reportRequestModel = new ReportRequestModel(reason, review, reporter);
        // pass reportrequestmodel to input.create method which returns a reportreponsemodel
        return input.create(reportRequestModel);
    }
}
